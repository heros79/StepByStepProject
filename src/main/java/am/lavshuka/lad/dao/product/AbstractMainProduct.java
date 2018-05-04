package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductBrand;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 5/2/2018.
 */

public class AbstractMainProduct <T> {

    private Session session;
    private Transaction tx;

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null)
            return sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

    protected void add(T t) {
        session = getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.save(t);
        tx.commit();
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
        tx = session.beginTransaction();
        session.delete(t);
        tx.commit();
        session.close();
    }
}