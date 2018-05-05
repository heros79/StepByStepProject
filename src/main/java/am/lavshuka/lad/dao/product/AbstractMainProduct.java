package am.lavshuka.lad.dao.product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 5/2/2018.
 */

public class AbstractMainProduct <T> {

    private Session session;

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null)
            return sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

    protected void add(T t) {
        session = getSessionFactory().openSession();
        session.save(t);
        session.close();
    }

    protected T find(String sql, String searchParam) {
        session = getSessionFactory().openSession();
        Query <T> query = session.createQuery(sql + " = :name");
        query.setParameter("name", searchParam);
        T t = (T) query.uniqueResult();
        session.close();
        return t;
    }

    protected List<T> findAll(Class<T> t) {
        List<T> list = new ArrayList<>();
        session = getSessionFactory().openSession();
        Query query = session.createQuery("from " + t.getName());
        list = query.list();
        session.close();
        return list;
    }

    protected void remove(T t) {
        session = getSessionFactory().openSession();
        session.delete(t);
        session.close();
    }
}