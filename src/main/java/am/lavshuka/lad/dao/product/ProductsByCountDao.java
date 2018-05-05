package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductsByCount;
import org.hibernate.Session;
import org.hibernate.query.Query;


/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class ProductsByCountDao extends AbstractMainProduct<ProductsByCount>{

    private Session session;

    public ProductsByCount findProductWithCount(ProductModel productModel) {
        session = super.getSessionFactory().openSession();
        Query query = session.createQuery("from ProductsByCount where vendorCode = :code");
        query.setParameter("code", productModel.getVendorCode());
        ProductsByCount product = (ProductsByCount)query.uniqueResult();
        session.close();
        return product;
    }
}