package am.lavshuka.lad.controller;

import am.lavshuka.lad.dto.product.ProductBrandDTO;
import am.lavshuka.lad.dto.product.ProductCategoryDTO;
import am.lavshuka.lad.dto.product.ProductDTO;
import am.lavshuka.lad.dto.product.ProductTypeDTO;
import am.lavshuka.lad.model.product.ProductBrand;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductType;
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
    public Map<String, Object> index(@RequestParam(value = "category", required = false) String category,
                                     @RequestParam(value = "type", required = false) String type,
                                     @RequestParam(value = "brand", required = false) String brand) {
        Map<String, Object> map = new Hashtable<>();

        List<ProductCategoryDTO> categoryList = productCategoryService.findAllProductCategory();
        List<ProductBrandDTO> brandList = productBrandService.findAllProductBrand();
        List<ProductTypeDTO> typeList = null;
        List<ProductDTO> productList = null;

        if (null != category && !category.equals("--||--")) {
            if (null == type && null == brand) {
                ProductCategory productCategory = productCategoryService.findProductCategoryByName(category);
                typeList = productTypeService.findProductTypeByCategory(productCategory);
                productList = productService.findProductsByCategory(productCategory);
            }
        }

        if (null != type && !type.equals("--||--")){
            if (null == brand) {
                ProductType productType = productTypeService.findProductTypeByName(type);
                productList = productService.findProductsByType(productType);
            }
        } else if (null != brand && !brand.equals("--||--")) {
            ProductBrand productBrand = productBrandService.findProductBrandByName(brand);
            productList = productService.findProductsByBrand(productBrand);
        } else {
            typeList = productTypeService.findAllProductType();
            productList = productService.findAllProducts();
        }


        map.put("productList", productList);
        map.put("categoryList", categoryList);
        map.put("typeList", typeList);
        map.put("brandList", brandList);

        return map;
    }
}
