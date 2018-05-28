package am.lavshuka.lad.model.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;
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
@Table(name = "prodbrand")
@JsonIgnoreProperties(ignoreUnknown = false)
@Proxy(lazy = false)
@Component
public class ProductBrand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brandname")
    private String productBrandName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productBrand")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProductModel> productModelSet;
}