package am.lavshuka.lad.service.product;

import am.lavshuka.lad.dao.product.ProductDao;
import am.lavshuka.lad.model.product.ProductBrand;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductType;

import java.util.List;

/**
 * Created by David on 5/5/2018.
 */

public class ProductService {

    public void addProduct(ProductModel productModel) {
        new ProductDao().add(productModel);
    }

    public ProductModel findProductByVendorCode(String vendorCode) {
        return new ProductDao().findByVendorCode(vendorCode);
    }

    public List<ProductModel> findAllProducts() {
        return new ProductDao().findAll(ProductModel.class);
    }

    public List<ProductModel> findProductsByCategory(ProductCategory productCategory) {
        return productCategory.getProductModelSet();
    }

    public List<ProductModel> findProductsByType(ProductType productType) {
        return productType.getProductModelSet();
    }

    public List<ProductModel> findProductsByBrand(ProductBrand productBrand) {
        return productBrand.getProductModelSet();
    }

    public void changeProductData(ProductModel productModel, Double price, String description) {

        if (productModel == null || (price == null && description == null))
            throw new IllegalArgumentException();
        if (price <= 0)
            throw new IllegalArgumentException();

        if (price != null)
            productModel.setPrice(price);

        if (description !=null)
            productModel.setDescription(description);

        new ProductDao().changeProductData(productModel);
    }

    public void removeProduct(ProductModel productModel) {
        new ProductDao().remove(productModel);
    }
}