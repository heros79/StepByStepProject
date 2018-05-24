package am.lavshuka.lad.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "totalproducts")
@Component
public class ProductsByCount implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "productname")
    private String productName;
    @Id
    @Column(name = "vendorcode")
    private String vendorCode;

    @Column(name = "count")
    private Integer count;
}