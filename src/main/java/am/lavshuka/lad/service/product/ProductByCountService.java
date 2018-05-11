package am.lavshuka.lad.service.product;

import am.lavshuka.lad.dao.product.ProductsByCountDao;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductsByCount;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by David on 5/5/2018.
 */

@Service
public class ProductByCountService {

    public ProductsByCount findProductWithTotalCount(ProductModel productModel) {
        return new ProductsByCountDao().findProductWithCount(productModel);
    }

    public List<ProductsByCount> getAllProductsWithCount () {
        return new ProductsByCountDao().findAll(ProductsByCount.class);
    }
}