package am.lavshuka.lad.service.product;

import am.lavshuka.lad.dao.product.ProductTypeDao;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductType;

import java.util.List;

/**
 * Created by David on 5/5/2018.
 */

public class ProductTypeService {

    public void addProductType(ProductType productType) {
        new ProductTypeDao().add(productType);
    }

    public ProductType findProductTypeByName(String typeName) {
        return new ProductTypeDao().findByTypeName(typeName);
    }

    public List<ProductType> findAllProductType() {
        return new ProductTypeDao().findAll(ProductType.class);
    }

    public List<ProductType> findProductTypeByCategory(ProductCategory productCategory) {
        return (List<ProductType>) productCategory.getProductTypeSet();
    }
}