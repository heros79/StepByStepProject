package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductBrand;
import org.springframework.stereotype.Repository;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Repository
public class ProductBrandDao extends AbstractMainProduct<ProductBrand> {

    public ProductBrand findByProductBrandName(String brandName) {
        String s = "from ProductBrand where productBrandName";
        return find(s, brandName);
    }
}