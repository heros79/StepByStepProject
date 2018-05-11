package am.lavshuka.lad.service.product;

import am.lavshuka.lad.dao.product.ProductBrandDao;
import am.lavshuka.lad.model.product.ProductBrand;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by David on 5/5/2018.
 */

@Service
public class ProductBrandService {

    public void addProductBrand(ProductBrand productBrand) {
        new ProductBrandDao().add(productBrand);
    }

    public ProductBrand findProductBrandByName(String brandName) {
        return new ProductBrandDao().findByProductBrandName(brandName);
    }

    public List<ProductBrand> findAllProductBrand() {
        return new ProductBrandDao().findAll(ProductBrand.class);
    }
}