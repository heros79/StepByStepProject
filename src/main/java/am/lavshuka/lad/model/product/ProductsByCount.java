package am.lavshuka.lad.model.product;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class ProductsByCount {

    private Long productId;
    private String productName;
    private String vendorCode;
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
