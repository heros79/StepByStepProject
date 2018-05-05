package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductModel;
import org.hibernate.Session;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class ProductDao extends AbstractMainProduct<ProductModel>{

    private Session session;

    public ProductModel findByVendorCode(String vendorCode) {
        String s = "from ProductModel where vendorCode";
        return super.find(s, vendorCode);
    }

    public void changeProductData(ProductModel productModel) {
        session = super.getSessionFactory().openSession();
        session.update(productModel);
        session.close();
    }
}