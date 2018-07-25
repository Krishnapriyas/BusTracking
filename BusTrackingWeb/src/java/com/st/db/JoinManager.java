package com.st.db;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JoinManager {

    Connection con = null;

    public JoinManager() {

    }

    public List list(Class<? extends Object> className1, Class<? extends Object> className2, String on, Object whereClause, String operator) throws SQLException {
        List list = new ArrayList();

        Field[] declaredFields = className1.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        Annotation[] annotations = className1.getAnnotations();
        sb.append("SELECT * FROM ");
        sb.append(getTable(className1.getAnnotations()).append(" lt LEFT JOIN "));
        sb.append(getTable(className2.getAnnotations()).append(" rt ON "));
        sb.append(" lt.");
        sb.append(on);
        sb.append("=rt.");
        sb.append(on);
        sb.append(" WHERE ");
        sb.append(getWhere(whereClause, operator, "lt."));
        sb.append(joinGroupBy(className2, "rt"));
        //sb.append(",");
        // sb.append(joinGroupBy(className2, "rt").toString().replaceFirst("GROUP BY", ""));
        System.out.println(sb);
        ResultSet rs = query(sb.toString());
        int i = 1;
        int columnCount = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            Object[] arry = new Object[columnCount];
            for (int j = 1; j <= columnCount; j++) {
                arry[(j - 1)] = rs.getObject(j);
            }
            list.add(arry);
        }

        return list;
    }

    private int add(String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);
        return ps.executeUpdate();
    }

    private ResultSet query(String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);
        return ps.executeQuery();
    }

    private String getWhere(Object whereClause, String operator) {
        Field[] whereFields = whereClause.getClass().getDeclaredFields();
        StringBuilder values = new StringBuilder();

        for (Field declaredField : whereFields) {
            declaredField.setAccessible(true);
            try {
                Object get = declaredField.get(whereClause);
                if (get != null && !get.equals("")) {
                    values.append(operator);
                    values.append(" ");
                    values.append(declaredField.getName());
                    values.append("='");
                    values.append(get);
                    values.append("' ");
                }

            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return values.toString().replaceFirst(operator, " ");
    }

    private String joinGroupBy(Class<? extends Object> groups, String alias) {
        Field[] whereFields = groups.getDeclaredFields();
        StringBuilder values = new StringBuilder();
        values.append(" GROUP BY ");
        for (Field declaredField : whereFields) {
            declaredField.setAccessible(true);
            try {
                values.append(",");
                values.append(alias);
                values.append(".");
                values.append(declaredField.getName());
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return values.toString().replaceFirst(",", " ");
    }

    private String groupBy(Class<? extends Object> groups) {
        Field[] whereFields = groups.getDeclaredFields();
        StringBuilder values = new StringBuilder();
        values.append(" GROUP BY ");
        for (Field declaredField : whereFields) {
            declaredField.setAccessible(true);
            try {

                values.append(",");
                values.append(".");
                values.append(declaredField.getName());

            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return values.toString().replaceFirst(",", " ");
    }

    private String getWhere(Object whereClause, String operator, String join) {
        Field[] whereFields = whereClause.getClass().getDeclaredFields();
        StringBuilder values = new StringBuilder();

        for (Field declaredField : whereFields) {
            declaredField.setAccessible(true);
            try {
                Object get = declaredField.get(whereClause);
                if (get != null && !get.equals("")) {
                    values.append(operator);
                    values.append(join);
                    values.append(declaredField.getName());
                    values.append("='");
                    values.append(get);
                    values.append("' ");
                }

            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return values.toString().replaceFirst(operator, " ");
    }

    private StringBuilder getTable(Annotation[] annotations) {
        StringBuilder table = new StringBuilder();
        for (Annotation annotation : annotations) {
            String keyvalue = annotation.toString();
            if (keyvalue.contains("name")) {
                table.append(keyvalue.substring(keyvalue.lastIndexOf("=") + 1, keyvalue.length() - 1));
            }
        }
        return table;
    }

}
