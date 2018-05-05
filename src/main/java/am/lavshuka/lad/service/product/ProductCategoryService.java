package am.lavshuka.lad.service.product;

import am.lavshuka.lad.dao.product.ProductCategoryDao;
import am.lavshuka.lad.model.product.ProductCategory;

import java.util.List;

/**
 * Created by David on 5/5/2018.
 */
public class ProductCategoryService {

    public void addProductCategory(ProductCategory productCategory) {
        new ProductCategoryDao().add(productCategory);
    }

    public ProductCategory findProductCategoryByName(String categoryName) {
        return new ProductCategoryDao().findByCategoryName(categoryName);
    }

    public List <ProductCategory> findAllProductCategory() {
        return new ProductCategoryDao().findAll(ProductCategory.class);
    }
}