package am.lavshuka.lad.model.product;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class ProductBrand {

    private Long id;
    private String productBrandName;

    public ProductBrand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductBrandName() {
        return productBrandName;
    }

    public void setProductBrandName(String productBrandName) {
        this.productBrandName = productBrandName;
    }
}
