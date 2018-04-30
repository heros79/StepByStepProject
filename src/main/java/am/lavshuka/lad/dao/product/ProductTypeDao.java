package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class ProductTypeDao {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private Transaction tx;

    public void addProductType(ProductType productType) throws SQLException {

        Session session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(productType);
        tx.commit();
        session.close();
    }

    public ProductType findByTypeName(String typeName) throws SQLException {

        Session session = sessionFactory.openSession();
        tx = session.beginTransaction();;
        Query query = session.createQuery("from ProductType where productTypeName = ?");
        query.setParameter(0, typeName);

        ProductType productType = (ProductType)query.uniqueResult();

        tx.commit();
        session.close();

        return productType;
    }

    public List<ProductType> findAllProductType() throws SQLException {

        List<ProductType> list;
        Session session = sessionFactory.openSession();
        tx = session.beginTransaction();

        Query query = session.createQuery("FROM ProductType");

        list = query.list();

        return list;
    }

    public List<ProductType> findAllByProductCategory(ProductCategory productCategory) throws SQLException {

        List<ProductType> list;
        Session session = sessionFactory.openSession();
        tx = session.beginTransaction();

        Query query = session.createQuery("FROM ProductType WHERE productCategory = ?");
        query.setParameter(0, productCategory);

        list = query.list();

        return list;
    }
}
