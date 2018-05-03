package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.BuySellActionProduct;
import am.lavshuka.lad.model.product.ProductModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class BuySellActionProductDao extends AbstractMainProduct<BuySellActionProduct>{

    private Session session;
    private Transaction tx;

    public void buyProduct(ProductModel productModel, int count, java.util.Date buyDate) {
        BuySellActionProduct product = new BuySellActionProduct();
        product.setProductModel(productModel);
        product.setCount(count);
        product.setProductBuyDate(buyDate);
        super.add(product);
    }

    public void sellProduct(ProductModel productModel, int count, java.util.Date sellDate) {
        BuySellActionProduct product = new BuySellActionProduct();
        product.setProductModel(productModel);
        product.setCount(-count);
        product.setProductSellDate(sellDate);
        super.add(product);
    }

    public List<BuySellActionProduct> findBuyProductByDate(java.util.Date date) {

        List<BuySellActionProduct> list;
        session = super.getSessionFactory().openSession();
        Query query = session.createQuery("from BuySellActionProduct where productBuyDate = :date");
        query.setParameter("date", date);
        list = query.list();
        session.close();
        return list;
    }

    public List<BuySellActionProduct> findSellProductByDate(java.util.Date date) {
        List<BuySellActionProduct> list;
        session = super.getSessionFactory().openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("from BuySellActionProduct where productSellDate = :date");
        query.setParameter("date", date);
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }
}