package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductBrand;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class ProductBrandDao extends AbstractMainProduct <ProductBrand>{

    public void addProductBrand(ProductBrand productBrand) {
        super.add(productBrand);
    }

    public ProductBrand findByProductBrandName(String brandName) {
        String s = "from ProductBrand where productBrandName";;
        return super.find(s, brandName);
    }

    public List<ProductBrand> findAllProductBrand() {
        return super.findAll(ProductBrand.class);
    }
}