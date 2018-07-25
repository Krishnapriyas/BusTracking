package com.st.db;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

public class Manager {

    protected Db connection = new Db();
    public static int ERROR = 0;
    public static int SUCCESS = 1;
    public static int EXISTS = -1;

    public int save(Object object) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        try {
            session = connection.getSession();
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
            result = 1;
        } catch (ConstraintViolationException ex) {
            if (ex.getErrorCode() == 1062) {
                result = -1;
            }
            transaction.rollback();

        } finally {
            connection.closeSession(session);
        }
        return result;
    }

    final public Object getExists(Class className, String[] feilds, String[] values) throws ManagerException {

        Session session = null;
        Object object = null;
        Transaction transaction = null;
        try {
            session = connection.getSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(className);
            if (feilds == null || values == null) {
                throw new ManagerException("The fields contains null values");
            }
            if (feilds.length != values.length) {

                throw new ManagerException("The fields values must be equal size");
            }
            for (int i = 0; i < feilds.length; i++) {
                criteria.add(Restrictions.eq(feilds[i], values[i]));
            }
            object = criteria.uniqueResult();
            transaction.commit();
        } catch (NullPointerException e) {
            transaction.rollback();
        } finally {
            connection.closeSession(session);
        }
        return object;
    }

    public List listbykeyValue(Class className, String... keyvalue) {
        Session session = null;
        List object = null;
        try {
            session = connection.getSession();
            Criteria crite = session.createCriteria(className);
            System.out.println(keyvalue.length);
            if (keyvalue != null && keyvalue.length % 2 == 0) {
                for (int i = 0; i < keyvalue.length; i += 2) {
                    System.out.println("vale = " + keyvalue[i]);
                    if (keyvalue[i].trim().contains("id")) {
                        int o = Integer.valueOf(keyvalue[i + 1]).intValue();
                        crite.add(Restrictions.eq(keyvalue[i], o));
                        System.out.println("vale = " + o);
                    } else {

                        crite.add(Restrictions.eq(keyvalue[i], keyvalue[i + 1]));
                    }
                }
            }
            object = crite.list();

        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            connection.closeSession(session);
        }
        return object;
    }

    @SuppressWarnings("rawtypes")
    public List list(Class className) {
        List objects = null;
        Session session = null;
        try {
            session = connection.getSession();
            Criteria criteria = session.createCriteria(className);
            objects = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeSession(session);
        }
        return objects;
    }

    final public List list(Class className, String fieldName, Object value) {
        List objects = null;
        Session session = null;
        try {
            session = connection.getSession();
            Criteria criteria = session.createCriteria(className).add(Restrictions.eq(fieldName, value));
            objects = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeSession(session);
        }
        return objects;
    }

    final public List listNotEqual(Class className, String fieldName, Object value) {
        List objects = null;
        Session session = null;
        try {
            session = connection.getSession();
            Criteria criteria = session.createCriteria(className).add(Restrictions.ne(fieldName, value));
            objects = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeSession(session);
        }
        return objects;
    }

    final public List listDesc(Class className, String fieldName, Object value) {
        List objects = null;
        Session session = null;
        try {
            session = connection.getSession();
            Criteria criteria = session.createCriteria(className).add(Restrictions.eq(fieldName, value));
            Criteria addOrder = criteria.addOrder(Order.desc("id"));
            objects = addOrder.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeSession(session);
        }
        return objects;
    }

    public List listLike(Class className, String fieldName, Object value) {
        List objects = null;
        Session session = null;
        try {
            session = connection.getSession();
            Criteria criteria = session.createCriteria(className).add(Restrictions.like(fieldName, value + "%"));
            Criteria addOrder = criteria.addOrder(Order.desc("id"));
            objects = addOrder.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeSession(session);
        }
        return objects;
    }

    final public Object getBean(Class className, String fieldName, Object value) {
        Object objects = null;
        Session session = null;
        try {
            session = connection.getSession();
            Criteria criteria = session.createCriteria(className).add(Restrictions.eq(fieldName, value));
            Criteria addOrder = criteria.addOrder(Order.desc("id"));
            List list = addOrder.list();
            for (Object object : list) {
                objects = object;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeSession(session);
        }
        return objects;
    }

    final public boolean delete(Object object) {
        boolean result = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = connection.getSession();
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            connection.closeSession(session);
        }
        return result;
    }

    public boolean update(Object object) {
        boolean result = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = connection.getSession();
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();

        } finally {
            connection.closeSession(session);
        }
        return result;
    }

    public boolean update(Object obj, String column, Object value) {
        boolean result = false;
        Session session = null;
        Transaction transaction = null;

        try {
            session = connection.getSession();
            transaction = session.beginTransaction();
            Field[] fileds = obj.getClass().getDeclaredFields();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE " + obj.getClass().getSimpleName());
            sb.append(" SET ");
            for (Field field : fileds) {
                field.setAccessible(true);
                 boolean isTransient = Modifier.isTransient(field.getModifiers());
                if (!isTransient &&  field.get(obj) != null && !field.get(obj).equals("")) {
                    {
                        sb.append(",");
                        sb.append(field.getName());
                        sb.append("='");
                        sb.append(field.get(obj));
                        sb.append("'");
                    }
                }
            }
            sb.append(" WHERE ");
            sb.append(column);
            sb.append("='");
            sb.append(String.valueOf(value));
            sb.append("'");
            String qery = sb.toString().replaceFirst(",", "");
            Query createQuery = session.createQuery(qery);
            int executeUpdate = createQuery.executeUpdate();
            transaction.commit();
            if (executeUpdate > 0) {
                result = true;
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            connection.closeSession(session);
        }
        return result;

    }

    public boolean update(Object obj, Object conditionalObj, Object operator) {
        boolean result = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = connection.getSession();
            transaction = session.beginTransaction();
            Field[] fileds = obj.getClass().getDeclaredFields();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE " + obj.getClass().getSimpleName());
            sb.append(" SET ");
            for (Field field : fileds) {
                field.setAccessible(true);
                boolean isTransient = Modifier.isTransient(field.getModifiers());
                if (!isTransient && field.get(obj) != null && !field.get(obj).equals("")) {

                    if (field.getName().trim().equals("id") && (field.getDouble(obj)) == 0.0) {
                    } else {
                        sb.append(",");
                        sb.append(field.getName());
                        sb.append("='");
                        sb.append(field.get(obj));
                        sb.append("'");
                    }
                }
            }
            sb.append(" WHERE ");
            //===========conditional===============
            StringBuilder sbc = new StringBuilder();
            Field[] confileds = conditionalObj.getClass().getDeclaredFields();
            for (Field field : confileds) {
                field.setAccessible(true);
                 boolean isTransient = Modifier.isTransient(field.getModifiers());
                if (!isTransient && field.get(conditionalObj) != null && !field.get(conditionalObj).equals("")) {
                    if (field.getName().trim().equals("id") && (field.getDouble(conditionalObj)) == 0.0) {
                    } else {

                        sbc.append(" " + operator + " ");
                        sbc.append(field.getName());
                        sbc.append("='");
                        sbc.append(field.get(conditionalObj));
                        sbc.append("'");
                    }
                }
            }
            String clause = sbc.toString().replaceFirst("AND", "");
            sb.append(clause);
            String qery = sb.toString().replaceFirst(",", "");
            Query createQuery = session.createQuery(qery);
            int executeUpdate = createQuery.executeUpdate();
            transaction.commit();
            System.out.println("query :" + qery);
            if (executeUpdate > 0) {
                result = true;
                System.out.println("status" + result);
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();

        } finally {
            connection.closeSession(session);
        }
        return result;

    }

    final public Object getObject(Class clas, String field, String id, String field1, String id1) {
        Session session = null;
        Object object = null;
        try {
            session = connection.getSession();
            Criteria criteria = session.createCriteria(clas);
            criteria.add(Restrictions.eq(field, id));
            criteria.add(Restrictions.eq(field1, id1));
            object = criteria.uniqueResult();

        } catch (Exception e) {
        } finally {
            connection.closeSession(session);
        }

        return object;
    }
    public List join(String sql) {
        Session session = null;
        session = connection.getSession();
        SQLQuery query = session.createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        return list;
    }

    public static void main(String[] args) {

    }
}
