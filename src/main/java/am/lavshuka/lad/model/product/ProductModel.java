package am.lavshuka.lad.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
@Component
public class ProductModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private ProductType productType;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private ProductBrand productBrand;

    @Column(name = "vendorcode")
    private String vendorCode;

    @Column(name = "productname")
    private String productName;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "filepath")
    private String productImageFilePath;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productModel")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<BuySellActionProduct> buySellActionProductList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductModel productModel = (ProductModel) o;

        if (!vendorCode.equals(productModel.vendorCode))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 31 * vendorCode.hashCode();
    }
}