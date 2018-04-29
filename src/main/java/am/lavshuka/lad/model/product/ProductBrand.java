package am.lavshuka.lad.model.product;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Entity
@Table(name = "prodbrand")
public class ProductBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brandname")
    private String productBrandName;

    @OneToMany(mappedBy = "productBrand")
    private Set<ProductModel> productModelSet;

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

    public Set<ProductModel> getProductModelSet() {
        return productModelSet;
    }

    public void setProductModelSet(Set<ProductModel> productModelSet) {
        this.productModelSet = productModelSet;
    }
}