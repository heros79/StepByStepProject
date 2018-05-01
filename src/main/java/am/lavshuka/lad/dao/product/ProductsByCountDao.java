package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductsByCount;
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

public class ProductsByCountDao {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction tx;

    public ProductsByCountDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public ProductsByCount findProductWithCount(ProductModel productModel) throws SQLException {

        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        Query query = session.createQuery("from ProductsByCount where vendorCode = :code");
        query.setParameter("code", productModel.getVendorCode());

        ProductsByCount product = (ProductsByCount)query.uniqueResult();

        tx.commit();
        session.close();

        return product;
    }

    public List<ProductsByCount> findAllProductssWithCount() throws SQLException {

        List<ProductsByCount> list;
        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        Query query = session.createQuery("from ProductsByCount ");
        list = query.list();

        tx.commit();
        session.close();

        return list;
    }
}