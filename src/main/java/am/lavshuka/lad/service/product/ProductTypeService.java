package am.lavshuka.lad.service.product;

import am.lavshuka.lad.dao.product.ProductTypeDao;
import am.lavshuka.lad.dto.product.ProductTypeDTO;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 5/5/2018.
 */

@Service
public class ProductTypeService {

    public void addProductType(ProductType productType) {
        new ProductTypeDao().add(productType);
    }

    public ProductType findProductTypeByName(String typeName) {
        return new ProductTypeDao().findByTypeName(typeName);
    }

    public List<ProductTypeDTO> findAllProductType() {
        List<ProductTypeDTO> list = new ArrayList<>();
        List<ProductType> typeList = new ProductTypeDao().findAll(ProductType.class);

        for (ProductType b : typeList) {
            list.add(new ProductTypeDTO()
                    .setID(b.getId())
                    .setName(b.getProductTypeName()));
        }

        return list;
    }

    public List<ProductTypeDTO> findProductTypeByCategory(ProductCategory productCategory) {
        List<ProductTypeDTO> list = new ArrayList<>();
        List<ProductType> typeList = productCategory.getProductTypeSet();

        for (ProductType b : typeList) {
            list.add(new ProductTypeDTO()
                    .setID(b.getId())
                    .setName(b.getProductTypeName()));
        }

        return list;
    }
}