package am.lavshuka.lad.model.product;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class ProductCategory {

    private Long id;
    private String productCategoryName;


    public ProductCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }
}
