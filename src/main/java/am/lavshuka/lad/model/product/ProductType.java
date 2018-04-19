package am.lavshuka.lad.model.product;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class ProductType {

    private Long id;
    private Long productCategoryId;
    private String productTypeName;

    public ProductType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
}
