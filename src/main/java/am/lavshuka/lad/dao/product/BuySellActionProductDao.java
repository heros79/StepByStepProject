package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.BuySellActionProduct;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class BuySellActionProductDao extends AbstractMainProduct<BuySellActionProduct>{

    private Session session;

    public void buySellProduct(BuySellActionProduct product) {
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
        Query query = session.createQuery("from BuySellActionProduct where productSellDate = :date");
        query.setParameter("date", date);
        list = query.list();
        session.close();
        return list;
    }
}