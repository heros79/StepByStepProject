package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductBrand;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class ProductDao extends AbstractMainProduct<ProductModel>{

    private Session session;
    private Transaction tx;

    public void addProduct(ProductModel productModel) {
        super.add(productModel);
    }

    public ProductModel findByVendorCode(String vendorCode) {
        String s = "from ProductModel where vendorCode";
        return super.find(s, vendorCode);
    }

    public List<ProductModel> findAllProducts() {
        return super.findAll(ProductModel.class);
    }

    public List<ProductModel> findProductsByCategory(ProductCategory productCategory) {
        return super.findAllByProductCategory(ProductModel.class, productCategory);
    }

    public List<ProductModel> findProductsByType(ProductType productType) {
        return super.findAllByProductType(ProductModel.class, productType);
    }

    public List<ProductModel> findProductsByBrand(ProductBrand productBrand) {
        return super.findAllByProductBrand(ProductModel.class, productBrand);
    }

    public void changeProductData(ProductModel productModel, Double price, String description) {
        session = super.getSessionFactory().openSession();
        tx = session.beginTransaction();
        if (productModel == null || (price == null && description == null)) {
            throw new IllegalArgumentException();
        }

        if (price != null) {
            productModel.setPrice(price);
        }

        if (description != null) {
            productModel.setDescription(description);
        }

        session.merge(productModel);

        tx.commit();
        session.close();
    }

    public void removeProduct(ProductModel productModel) {
        super.remove(productModel);
    }
}