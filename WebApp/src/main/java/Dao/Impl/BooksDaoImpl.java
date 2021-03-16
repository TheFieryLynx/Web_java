package Dao.Impl;

import Dao.BooksDao;
import Models.Books;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BooksDaoImpl implements BooksDao {
    public void create(Books book) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(book);
        tx1.commit();
        session.close();
    }

    public void update(Books book) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(book);
        tx1.commit();
        session.close();
    }

    public void delete(Books book) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(book);
        tx1.commit();
        session.close();
    }
}
