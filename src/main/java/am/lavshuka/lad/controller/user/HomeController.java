package am.lavshuka.lad.controller.user;

import am.lavshuka.lad.model.product.ProductBrand;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductType;
import am.lavshuka.lad.service.product.ProductBrandService;
import am.lavshuka.lad.service.product.ProductCategoryService;
import am.lavshuka.lad.service.product.ProductService;
import am.lavshuka.lad.service.product.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

/**
 * Created by David on 5/11/2018.
 */
@Controller("/search")
public class HomeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductBrandService productBrandService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(value = "category") String category,
                               @RequestParam(value = "type") String type,
                               @RequestParam(value = "brand") String brand) {
        ModelAndView modelAndView = new ModelAndView("search");
        List<ProductModel> list;
        if (!brand.equals("--||--") ) {
            ProductBrand productBrand = productBrandService.findProductBrandByName(brand);
            list = productService.findProductsByBrand(productBrand);
            Collections.shuffle(list);
            modelAndView.addObject("products", list);
            return modelAndView;
        } else if (!type.equals("--||--")) {
            ProductType productType = productTypeService.findProductTypeByName(type);
            list = productService.findProductsByType(productType);
            modelAndView.addObject("products", list);
            return modelAndView;
        } else if (!category.equals("--||--")) {
            ProductCategory productCategory = productCategoryService.findProductCategoryByName(category);
            list = productService.findProductsByCategory(productCategory);
            System.out.println(list.toString());
            modelAndView.addObject("products", list);
            return modelAndView;
        } else {
            return modelAndView = new ModelAndView("redirect:/home");
        }
    }
}
