package am.lavshuka.lad.model.product;

import java.util.Date;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class BuySellActionProduct {

    private Long id;
    private Long product_id;
    private Integer count;
    private Date productBuyDate;
    private Date productSellDate;

    public BuySellActionProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getProductBuyDate() {
        return productBuyDate;
    }

    public void setProductBuyDate(Date productBuyDate) {
        this.productBuyDate = productBuyDate;
    }

    public Date getProductSellDate() {
        return productSellDate;
    }

    public void setProductSellDate(Date productSellDate) {
        this.productSellDate = productSellDate;
    }
}
