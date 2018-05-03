package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductsByCount;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class ProductsByCountDao extends AbstractMainProduct<ProductsByCount>{

    private Session session;
    private Transaction tx;

    public ProductsByCount findProductWithCount(ProductModel productModel) {
        session = super.getSessionFactory().openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("from ProductsByCount where vendorCode = :code");
        query.setParameter("code", productModel.getVendorCode());
        ProductsByCount product = (ProductsByCount)query.uniqueResult();
        tx.commit();
        session.close();
        return product;
    }

    public List<ProductsByCount> findAllProductssWithCount() {
        return super.findAll(ProductsByCount.class);
    }
}