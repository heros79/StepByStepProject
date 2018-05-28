package am.lavshuka.lad.dto;

import am.lavshuka.lad.model.product.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by David on 5/27/2018.
 */

@Getter
@Setter
@NoArgsConstructor
public class ProductTypeDTO {

    private Long id;

    private String productTypeName;

    public ProductTypeDTO setID(Long id) {
        this.id = id;
        return this;
    }

    public ProductTypeDTO setName(String name) {
        this.productTypeName = name;
        return this;
    }
}
