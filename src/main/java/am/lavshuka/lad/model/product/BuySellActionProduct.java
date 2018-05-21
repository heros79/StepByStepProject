package am.lavshuka.lad.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "productbuysell")
@Component
public class BuySellActionProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductModel productModel;

    @Column(name = "count")
    private Integer count;

    @Column(name = "buydate")
    @Temporal(TemporalType.DATE)
    private Date productBuyDate;

    @Column(name = "selldate")
    @Temporal(TemporalType.DATE)
    private Date productSellDate;
}