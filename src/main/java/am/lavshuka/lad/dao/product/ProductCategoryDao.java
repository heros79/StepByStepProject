package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductCategory;
import org.springframework.stereotype.Repository;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Repository
public class ProductCategoryDao extends AbstractMainProduct<ProductCategory> {

    public ProductCategory findByCategoryName(String categoryName) {
        String s = "from ProductCategory where productCategoryName";
        return find(s, categoryName);
    }
}