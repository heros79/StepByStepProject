package am.lavshuka.lad.model.product;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Entity
@Table(name = "prodtype")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @Column(name = "typename")
    private String productTypeName;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "productType")
    private Set<ProductModel> productModelSet;

    public ProductType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public Set<ProductModel> getProductModelSet() {
        return productModelSet;
    }

    public void setProductModelSet(Set<ProductModel> productModelSet) {
        this.productModelSet = productModelSet;
    }
}