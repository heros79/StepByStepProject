package am.lavshuka.lad.service.product;

import am.lavshuka.lad.dao.product.ProductBrandDao;
import am.lavshuka.lad.dto.ProductBrandDTO;
import am.lavshuka.lad.model.product.ProductBrand;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<ProductBrandDTO> findAllProductBrand() {

        List<ProductBrandDTO> list = new ArrayList<>();
        List<ProductBrand> brandList = new ProductBrandDao().findAll(ProductBrand.class);

        for (ProductBrand b : brandList) {
            list.add(new ProductBrandDTO()
                    .setID(b.getId())
                    .setName(b.getProductBrandName()));
        }
        return list;
    }
}