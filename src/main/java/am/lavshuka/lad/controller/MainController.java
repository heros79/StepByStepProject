package am.lavshuka.lad.controller;

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
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * Created by David on 5/9/2018.
 */

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductBrandService productBrandService;



    @RequestMapping(value = "/")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("redirect:/loginPage");
        return modelAndView;
    }

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("home");
        List<ProductModel> productList = productService.findAllProducts();
        modelAndView.addObject("products", productList);
        return modelAndView;
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView loginPage(/*@RequestParam(value = "category") String category*/) {
        ModelAndView modelAndView = new ModelAndView("loginPage");
        List<ProductModel> list = productService.findAllProducts();
        modelAndView.addObject("products", list);
        List<ProductCategory> categoryList = productCategoryService.findAllProductCategory();
        modelAndView.addObject("categoryList", categoryList);
        List<ProductType> typeList = productTypeService.findAllProductType();
        modelAndView.addObject("typeList", typeList);
        List<ProductBrand> brandList = productBrandService.findAllProductBrand();
        modelAndView.addObject("brandList", brandList);
        return modelAndView;
    }

    @RequestMapping(value = "/register")
    @RequestScope
    public ModelAndView regpage() {
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }

    @RequestMapping("/admin")
    public ModelAndView admin() {
        return new ModelAndView("admin");
    }

}
