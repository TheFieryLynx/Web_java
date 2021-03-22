package Dao.Impl;

import Dao.CustomersDao;
import Models.Customers;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    @Override
    public Customers readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Customers admin = session.get(Customers.class, id);
        session.close();
        return admin;
    }

    @Override
    public Customers readByLogin(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Customers> query = session.createQuery("FROM Customers WHERE customer_login = :param", Customers.class)
                .setParameter("param", login);
        return query.getResultList().get(0);
    }

    public void deleteAccount(Customers customer) {
        customer.setDeleted_account(true);
    }



}
