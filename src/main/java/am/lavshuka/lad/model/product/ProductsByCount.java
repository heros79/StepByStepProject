package am.lavshuka.lad.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "totalproducts")
public class ProductsByCount {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "productname")
    private String productName;
    @Id
    @Column(name = "vendorcode")
    private String vendorCode;

    @Column(name = "count")
    private Double count;
}