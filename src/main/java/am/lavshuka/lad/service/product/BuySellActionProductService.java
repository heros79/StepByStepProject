package am.lavshuka.lad.service.product;

import am.lavshuka.lad.dao.product.BuySellActionProductDao;
import am.lavshuka.lad.model.product.BuySellActionProduct;
import am.lavshuka.lad.model.product.ProductModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by David on 5/5/2018.
 */

@Service
public class BuySellActionProductService {

    public void buyProduct(ProductModel productModel, int count, java.util.Date buyDate) {
        BuySellActionProduct product = new BuySellActionProduct();
        product.setProductModel(productModel);
        product.setCount(count);
        product.setProductBuyDate(buyDate);

        new BuySellActionProductDao().add(product);
    }

    public List<BuySellActionProduct> findBuyProductsByDate(java.util.Date date) {
        return new BuySellActionProductDao().findBuyProductByDate(date);
    }

    public List<BuySellActionProduct> findSellProductsByDate(java.util.Date date) {
        return new BuySellActionProductDao().findSellProductByDate(date);
    }
}