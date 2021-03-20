package Dao.Impl;

import Dao.BooksDao;
import Models.Admin;
import Models.Books;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BooksDaoImpl implements BooksDao {
    @Override
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

    @Override
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

    @Override
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

    @Override
    public Books readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Books book = session.get(Books.class, id);
        session.close();
        return book;
    }

    @Override
    public List<Books> readListByGenre(String genre) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE genre = :param", Books.class)
                .setParameter("param", genre);
        return query.getResultList();
    }

    @Override
    public List<Books> readListByAuthor(String author) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE author = :param", Books.class)
                .setParameter("param", author);
        return query.getResultList();
    }

    @Override
    public List<Books> readListByPubHouse(String pub_house) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE pub_house = :param", Books.class)
                .setParameter("param", pub_house);
        return query.getResultList();
    }

    @Override
    public int bookAmount(Books book) {
        return book.getAmount();
    }

    @Override
    public double bookPrice(Books book) {
        return book.getPrice();
    }






}
