package Dao.Impl;

import Dao.BooksDao;
import Models.Books;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BooksDaoImpl implements BooksDao {
    public void create(Books book) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(book);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("BooksCreate Exception thrown: " + e.getMessage());
        }
    }

    public void update(Books book) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(book);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("BooksUpdate Exception thrown: " + e.getMessage());
        }
    }

    public void delete(Books book) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(book);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("BooksDelete Exception thrown: " + e.getMessage());
        }
    }
}
