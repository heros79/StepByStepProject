package am.lavshuka.lad.service.product;

import am.lavshuka.lad.dao.product.ProductCategoryDao;
import am.lavshuka.lad.dto.ProductCategoryDTO;
import am.lavshuka.lad.model.product.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 5/5/2018.
 */

@Service
public class ProductCategoryService {

    public void addProductCategory(ProductCategory productCategory) {
        new ProductCategoryDao().add(productCategory);
    }

    public ProductCategory findProductCategoryByName(String categoryName) {
        return new ProductCategoryDao().findByCategoryName(categoryName);
    }

    public List <ProductCategoryDTO> findAllProductCategory() {

        List<ProductCategoryDTO> list = new ArrayList<>();
        List<ProductCategory> categoryList = new ProductCategoryDao().findAll(ProductCategory.class);
        for (ProductCategory b: categoryList) {
            list.add(new ProductCategoryDTO()
                    .setID(b.getId())
                    .setName(b.getProductCategoryName()));
        }
        return list;
    }
}