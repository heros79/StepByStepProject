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
public class ProductCategoryDTO {

    private Long id;

    private String categoryName;

    public ProductCategoryDTO setID(Long id) {
        this.id = id;
        return this;
    }

    public ProductCategoryDTO setName(String name) {
        this.categoryName = name;
        return this;
    }
}
