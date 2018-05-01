package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.BuySellActionProduct;
import am.lavshuka.lad.model.product.ProductModel;
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

public class BuySellActionProductDao {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction tx;

    public BuySellActionProductDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void buyProduct(ProductModel productModel, int count, java.util.Date buyDate) throws SQLException {

        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        BuySellActionProduct product = new BuySellActionProduct();
        product.setProductModel(productModel);
        product.setCount(count);
        product.setProductBuyDate(buyDate);

        session.save(product);

        tx.commit();
        session.close();
    }

    public void sellProduct(ProductModel productModel, int count, java.util.Date sellDate) throws SQLException {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        BuySellActionProduct product = new BuySellActionProduct();
        product.setProductModel(productModel);
        product.setCount(count);
        product.setProductSellDate(sellDate);

        session.save(product);

        tx.commit();
        session.close();
    }

    public List<BuySellActionProduct> findBuyProductByDate(java.util.Date date) throws SQLException {

        List<BuySellActionProduct> list;

        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        Query query = session.createQuery("from BuySellActionProduct where productBuyDate = :date");
        query.setParameter("date", date);
        list = query.list();

        tx.commit();
        session.close();

        return list;
    }

    public List<BuySellActionProduct> findSellProductByDate(java.util.Date date) throws SQLException {
        List<BuySellActionProduct> list;

        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        Query query = session.createQuery("from BuySellActionProduct where productSellDate = :date");
        query.setParameter("date", date);
        list = query.list();

        tx.commit();
        session.close();

        return list;
    }
}