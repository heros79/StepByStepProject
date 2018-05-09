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

public class AbstractMainProduct<T> {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null)
            return sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

    public void add(T t) {
        Session session = getSessionFactory().openSession();
        session.save(t);
        session.close();
    }

    public T find(String sql, String searchParam) {
        Session session = getSessionFactory().openSession();
        Query<T> query = session.createQuery(sql + " = :name");
        query.setParameter("name", searchParam);
        T t = (T) query.uniqueResult();
        session.close();
        return t;
    }

    public List<T> findAll(Class<T> t) {
        List<T> list = new ArrayList<>();
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from " + t.getName());
        list = query.list();
        session.close();
        return list;
    }

    public void remove(T t) {
        Session session = getSessionFactory().openSession();
        session.delete(t);
        session.close();
    }
}