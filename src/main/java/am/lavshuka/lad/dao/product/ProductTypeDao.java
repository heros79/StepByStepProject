package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductType;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class ProductTypeDao extends AbstractMainProduct<ProductType> {

    public ProductType findByTypeName(String typeName) {
        String s = "from ProductType where productTypeName";
        return find(s, typeName);
    }
}