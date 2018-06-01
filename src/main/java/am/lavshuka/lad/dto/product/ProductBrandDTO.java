package am.lavshuka.lad.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by David on 5/27/2018.
 */
@Getter
@Setter
@NoArgsConstructor
public class ProductBrandDTO {

    private Long id;

    private String productBrandName;

    public ProductBrandDTO setID(Long id) {
        this.id = id;
        return this;
    }

    public ProductBrandDTO setName(String name) {
        this.productBrandName = name;
        return this;
    }
}
