package Dao.Impl;

import Dao.OrdersDao;
import Models.Books;
import Models.Customers;
import Models.Orders;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao {
    public void create(Orders order) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(order);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("OrdersCreate Exception thrown: " + e.getMessage());
        }
    }

    public void update(Orders order) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(order);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("OrdersUpdate Exception thrown: " + e.getMessage());
        }
    }

    public void delete(Orders order) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(order);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("OrdersDelete Exception thrown: " + e.getMessage());
        }
    }

    @Override
    public Orders readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Orders order = session.get(Orders.class, id);
        session.close();
        return order;
    }

    @Override
    public List<Orders> readOrdersById(Customers customer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Orders> query = session.createQuery("FROM Orders WHERE customer_id = :param", Orders.class)
                .setParameter("param", customer);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<Orders> readOrders() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Orders> criteria = session.getCriteriaBuilder().createQuery(Orders.class);
        criteria.from(Orders.class);
        List<Orders> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }
}
