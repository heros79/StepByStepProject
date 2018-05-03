package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductType;

import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class ProductTypeDao extends AbstractMainProduct<ProductType> {

    public void addProductType(ProductType productType) {
        super.add(productType);
    }

    public ProductType findByTypeName(String typeName) {
        String s = "from ProductType where productTypeName";
        return super.find(s, typeName);
    }

    public List<ProductType> findAllProductType() {
        return super.findAll(ProductType.class);
    }

    public List<ProductType> findAllTypesByProductCategory(ProductCategory productCategory) {
        return super.findAllByProductCategory(ProductType.class, productCategory);
    }
}