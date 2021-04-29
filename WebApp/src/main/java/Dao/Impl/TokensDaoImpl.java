package Dao.Impl;

import Dao.OrdersDao;
import Dao.TokensDao;
import Models.Tokens;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class TokensDaoImpl implements TokensDao {
    public void create(Tokens token) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(token);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("TokenCreate Exception thrown: " + e.getMessage());
        }
    }

    public void update(Tokens token) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(token);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("TokenUpdate Exception thrown: " + e.getMessage());
        }
    }

    public void delete(Tokens token) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(token);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("TokenDelete Exception thrown: " + e.getMessage());
        }
    }

    @Override
    public Tokens readByToken(String token) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Tokens> query = session.createQuery("FROM Tokens WHERE token = :param", Tokens.class)
                .setParameter("param", token);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }
}
