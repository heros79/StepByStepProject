package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductsByCount;
import org.hibernate.Session;
import org.hibernate.query.Query;


/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class ProductsByCountDao extends AbstractMainProduct<ProductsByCount> {

    public ProductsByCount findProductWithCount(ProductModel productModel) {
        Session session = getSessionFactory().openSession();
        Query<ProductsByCount> query = session.createQuery("from ProductsByCount where vendorCode = :code",
                ProductsByCount.class);
        query.setParameter("code", productModel.getVendorCode());
        ProductsByCount product = query.uniqueResult();
        session.close();
        return product;
    }
}