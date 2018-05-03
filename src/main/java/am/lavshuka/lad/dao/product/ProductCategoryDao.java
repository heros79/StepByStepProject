package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductCategory;

import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class ProductCategoryDao extends AbstractMainProduct <ProductCategory>{

    public void addProductCategory(ProductCategory category) {
        super.add(category);
    }

    public ProductCategory findByCategoryName(String categoryName) {
        String s = "from ProductCategory where productCategoryName";
        return super.find(s, categoryName);
    }

    public List<ProductCategory> findAllProductCategory() {
        return super.findAll(ProductCategory.class);
    }
}