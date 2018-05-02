package am.lavshuka.lad.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Getter
@Setter
@NoArgsConstructor
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
}