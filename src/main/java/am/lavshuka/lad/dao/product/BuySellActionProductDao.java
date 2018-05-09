package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.BuySellActionProduct;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class BuySellActionProductDao extends AbstractMainProduct<BuySellActionProduct> {

    public List<BuySellActionProduct> findBuyProductByDate(java.util.Date date) {
        List<BuySellActionProduct> list;
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from BuySellActionProduct where productBuyDate = :date");
        query.setParameter("date", date);
        list = query.list();
        session.close();
        return list;
    }

    public List<BuySellActionProduct> findSellProductByDate(java.util.Date date) {
        List<BuySellActionProduct> list;
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from BuySellActionProduct where productSellDate = :date");
        query.setParameter("date", date);
        list = query.list();
        session.close();
        return list;
    }
}