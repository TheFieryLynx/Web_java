package Dao.Impl;

import Dao.BooksDao;
import Models.Books;
import Models.Customers;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaQuery;
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
        Query<Books> query = session.createQuery("FROM Books WHERE book_id = :param AND deleted_book = false", Books.class)
                .setParameter("param", id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<Books> readListByTitle(String title) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE title = :param AND deleted_book = false", Books.class)
                .setParameter("param", title);
        return query.getResultList();
    }

    @Override
    public List<Books> readListByGenre(String genre) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE genre = :param AND deleted_book = false", Books.class)
                .setParameter("param", genre);
        return query.getResultList();
    }

    @Override
    public void deleteBook(Books book) {
        book.setDeleted_book(true);
        update(book);
    }

    @Override
    public List<Books> readListByAuthor(String author) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE author = :param AND deleted_book = false", Books.class)
                .setParameter("param", author);
        return query.getResultList();
    }

    @Override
    public List<Books> readListByPubHouse(String pub_house) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE pub_house = :param AND deleted_book = false", Books.class)
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

    @Override
    public List<Books> AllBooks() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE deleted_book = false", Books.class);
        return query.getResultList();
    }





}
