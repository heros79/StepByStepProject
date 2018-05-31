package am.lavshuka.lad.controller;

import am.lavshuka.lad.dto.ProductBrandDTO;
import am.lavshuka.lad.dto.ProductCategoryDTO;
import am.lavshuka.lad.dto.ProductDTO;
import am.lavshuka.lad.dto.ProductTypeDTO;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.service.product.ProductBrandService;
import am.lavshuka.lad.service.product.ProductCategoryService;
import am.lavshuka.lad.service.product.ProductService;
import am.lavshuka.lad.service.product.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 5/27/2018.
 */
@RestController
@RequestMapping("/")
public class RestMainController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductBrandService productBrandService;

    @CrossOrigin
    @RequestMapping(value = "/index", method = RequestMethod.GET, produces = "application/json")
    public Map<String, Object> index(@RequestParam(value = "category", required = false) String category) {
        Map<String, Object> map = new Hashtable<>();

        List<ProductCategoryDTO> categoryList = productCategoryService.findAllProductCategory();
        List<ProductBrandDTO> brandList = productBrandService.findAllProductBrand();
        List<ProductTypeDTO> typeList;
        List<ProductDTO> productList;
        System.out.println("test " + category);
        if (null == category || (category.equals("--||--") && category.equals(""))) {
            typeList = productTypeService.findAllProductType();
            productList = productService.findAllProducts();
        } else {
            ProductCategory productCategory = productCategoryService.findProductCategoryByName(category);
            typeList = productTypeService.findProductTypeByCategory(productCategory);
            productList = productService.findProductsByCategory(productCategory);
        }

        map.put("productList", productList);
        map.put("categoryList", categoryList);
        map.put("typeList", typeList);
        map.put("brandList", brandList);

        return map;
    }
}
