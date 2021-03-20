package Dao.Impl;

import Dao.AdminDao;
import Models.Admin;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

//import javax.persistence.TypedQuery;

public class AdminDaoImpl implements AdminDao {
    @Override
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

    @Override
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

    @Override
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

    @Override
    public Admin readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Admin admin = session.get(Admin.class, id);
        session.close();
        return admin;
    }

    @Override
    public Admin readByLogin(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Admin> query = session.createQuery("FROM Admin WHERE admin_login = :param", Admin.class)
                .setParameter("param", login);
        return query.getResultList().get(0);
    }
}
