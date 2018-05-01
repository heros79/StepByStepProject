package am.lavshuka.lad.model.product;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Entity
@Table(name = "prodcategory")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "categoryname")
    private String productCategoryName;

    @OneToMany (fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "productCategory")
    private Set<ProductType> productTypeSet;

    @OneToMany (fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "productCategory")
    private Set<ProductModel> productModelSet;

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

    public Set<ProductType> getProductTypeSet() {
        return productTypeSet;
    }

    public void setProductTypeSet(Set<ProductType> productTypeSet) {
        this.productTypeSet = productTypeSet;
    }

    public Set<ProductModel> getProductModelSet() {
        return productModelSet;
    }

    public void setProductModelSet(Set<ProductModel> productModelSet) {
        this.productModelSet = productModelSet;
    }
}