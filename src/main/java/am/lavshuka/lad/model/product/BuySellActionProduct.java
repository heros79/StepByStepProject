package am.lavshuka.lad.model.product;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Entity
@Table(name = "productbuysell")
public class BuySellActionProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductModel productModel;

    @Column(name = "count")
    private Integer count;

    @Column(name = "buydate")
    @Temporal(TemporalType.DATE)
    private Date productBuyDate;

    @Column(name = "selldate")
    @Temporal(TemporalType.DATE)
    private Date productSellDate;

    public BuySellActionProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
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