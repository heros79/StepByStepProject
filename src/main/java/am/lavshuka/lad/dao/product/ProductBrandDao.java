package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductBrand;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class ProductBrandDao {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction tx;

    public ProductBrandDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addProductBrand(ProductBrand productBrand) throws SQLException {

        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(productBrand);
        tx.commit();
        session.close();
    }

    public ProductBrand findByProductBrandName(String brandName) throws SQLException {

        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("from ProductBrand where productBrandName = :name");
        query.setParameter("name", brandName);
        ProductBrand productBrand = (ProductBrand)query.uniqueResult();

        tx.commit();
        session.close();

        return productBrand;
    }

    public List<ProductBrand> findAllProductBrand() throws SQLException {

        List<ProductBrand> list;

        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("from ProductBrand ");
        list = query.list();
        tx.commit();
        session.close();

        return list;
    }
}