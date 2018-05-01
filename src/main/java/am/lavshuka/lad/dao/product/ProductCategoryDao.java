package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class ProductCategoryDao {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction tx;

    public ProductCategoryDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addProductCategoty(ProductCategory category) throws SQLException {

        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(category);
        tx.commit();
        session.close();
    }

    public ProductCategory findByCategoryName(String categoryName) throws SQLException {

        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("from ProductCategory where productCategoryName = :name");
        query.setParameter("name", categoryName);

        ProductCategory productCategory = (ProductCategory) query.uniqueResult();
        tx.commit();
        session.close();

        return productCategory;
    }

    public List<ProductCategory> findAllProductCategory() throws SQLException {

        List<ProductCategory> list = new ArrayList<>();

        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("from ProductCategory ");
        list = query.list();
        tx.commit();
        session.close();

        return list;
    }
}