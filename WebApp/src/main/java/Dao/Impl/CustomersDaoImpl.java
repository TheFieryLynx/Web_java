package Dao.Impl;

import Dao.CustomersDao;
import Models.Books;
import Models.Customers;
import Models.Orders;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

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
        Query<Customers> query = session.createQuery("FROM Customers WHERE customer_id = :param AND deleted_account = false", Customers.class)
                .setParameter("param", id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public Customers readByLogin(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Customers> query = session.createQuery("FROM Customers WHERE customer_login = :param AND deleted_account = false", Customers.class)
                .setParameter("param", login);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    public void deleteAccount(Customers customer) {
        customer.setDeleted_account(true);
    }

    @Override
    public List<Customers> readCustomers() {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Customers> query = session.createQuery("FROM Customers WHERE deleted_account = false", Customers.class);
        return query.getResultList();
    }



}
