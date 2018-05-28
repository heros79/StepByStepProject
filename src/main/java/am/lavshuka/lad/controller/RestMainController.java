package am.lavshuka.lad.controller;

import am.lavshuka.lad.dto.ProductBrandDTO;
import am.lavshuka.lad.dto.ProductCategoryDTO;
import am.lavshuka.lad.dto.ProductDTO;
import am.lavshuka.lad.dto.ProductTypeDTO;
import am.lavshuka.lad.model.product.ProductBrand;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductType;
import am.lavshuka.lad.service.product.ProductBrandService;
import am.lavshuka.lad.service.product.ProductCategoryService;
import am.lavshuka.lad.service.product.ProductService;
import am.lavshuka.lad.service.product.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
    public Map<String, Object> index() {
        Map<String, Object> map = new Hashtable<>();

        List<ProductDTO> productList = productService.findAllProducts();
        List<ProductCategoryDTO> categoryList = productCategoryService.findAllProductCategory();
        List<ProductTypeDTO> typeList = productTypeService.findAllProductType();
        List<ProductBrandDTO> brandList = productBrandService.findAllProductBrand();

        map.put("productList", productList);
        map.put("categoryList", categoryList);
        map.put("typeList", typeList);
        map.put("brandList", brandList);

        return map;
    }
}
