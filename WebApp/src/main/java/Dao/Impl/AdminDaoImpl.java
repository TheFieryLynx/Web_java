package Dao.Impl;

import Dao.AdminDao;
import Models.Admin;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdminDaoImpl implements AdminDao {
    public void create(Admin admin) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(admin);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("AdminCreate Exception thrown: " + e.getMessage());
        }
    }
    public void update(Admin admin) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(admin);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("AdminUpdate Exception thrown: " + e.getMessage());
        }
    }
    public void delete(Admin admin) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(admin);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("AdminDelete Exception thrown: " + e.getMessage());
        }
    }
}
