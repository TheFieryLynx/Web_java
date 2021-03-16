package Dao.Impl;

import Dao.OrdersDao;
import Models.Orders;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrdersDaoImpl implements OrdersDao {
    public void create(Orders order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(order);
        tx1.commit();
        session.close();
    }

    public void update(Orders order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(order);
        tx1.commit();
        session.close();
    }

    public void delete(Orders order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(order);
        tx1.commit();
        session.close();
    }
}
