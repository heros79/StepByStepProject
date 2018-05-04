package am.lavshuka.lad.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "prodcategory")
public class ProductCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "categoryname")
    private String productCategoryName;

    @OneToMany (fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "productCategory")
    private Set<ProductType> productTypeSet;

    @OneToMany (fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "productCategory")
    private Set<ProductModel> productModelSet;
}