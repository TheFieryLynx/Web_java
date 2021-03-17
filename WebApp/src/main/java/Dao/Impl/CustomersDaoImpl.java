package Dao.Impl;

import Dao.CustomersDao;
import Models.Customers;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomersDaoImpl implements CustomersDao {
    public void create(Customers customer) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(customer);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("CustomersCreate Exception thrown: " + e.getMessage());
        }
    }

    public void update(Customers customer) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(customer);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("CustomersUpdate Exception thrown: " + e.getMessage());
        }
    }

    public void delete(Customers customer) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(customer);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("CustomerDelete Exception thrown: " + e.getMessage());
        }
    }
}
