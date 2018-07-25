package com.st.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Db {
    private static final SessionFactory sessionFactory;
    static{
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(ssrb.build());
//            configuration.setSessionFactoryObserver(
//                    new SessionFactoryObserver() {
//                @Override
//                public void sessionFactoryCreated(SessionFactory factory) {
//                }
//
//                @Override
//                public void sessionFactoryClosed(SessionFactory factory) {
//                    ((StandardServiceRegistryImpl) factory).destroy();
//                }
//            }
//            );
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }

    public void closeSession(Session session) {
        try {
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
