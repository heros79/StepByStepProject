package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Repository
public class ProductDao extends AbstractMainProduct<ProductModel> {

    public ProductModel findByVendorCode(String vendorCode) {
        String s = "from ProductModel where vendorCode";
        return find(s, vendorCode);
    }

    public void changeProductData(ProductModel productModel) {
        Session session = getSessionFactory().openSession();
        session.update(productModel);
        session.close();
    }
}