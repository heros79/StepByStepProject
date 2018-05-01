package am.lavshuka.lad.model.product;

import javax.persistence.*;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Entity
@Table(name = "totalproducts")
public class ProductsByCount {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "productname")
    private String productName;
    @Id
    @Column(name = "vendorcode")
    private String vendorCode;

    @Column(name = "count")
    private Double count;

    public ProductsByCount() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }
}