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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 5/9/2018.
 */

@Controller
@RequestMapping("/")
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
        ModelAndView modelAndView = new ModelAndView("redirect:/index");
        return modelAndView;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("home");
        List<ProductModel> productList = productService.findAllProducts();
        modelAndView.addObject("products", productList);
        List<ProductCategory> categoryList = productCategoryService.findAllProductCategory();
        modelAndView.addObject("categoryList", categoryList);
        List<ProductType> typeList = productTypeService.findAllProductType();
        modelAndView.addObject("typeList", typeList);
        List<ProductBrand> brandList = productBrandService.findAllProductBrand();
        modelAndView.addObject("brandList", brandList);
        return modelAndView;
    }

    @RequestMapping(value = "/searchByCategory", method = RequestMethod.POST)
    public ModelAndView searchByCategory(@RequestParam(value = "categoryChoice") String category) {
        ModelAndView modelAndView = new ModelAndView("home");
        List<ProductModel> productsList = null;
        List<ProductType> typesList = null;
        List<ProductCategory> categoryList = productCategoryService.findAllProductCategory();
        List<ProductBrand> brandList = productBrandService.findAllProductBrand();
        if (!category.equals("--||--")) {
            ProductCategory productCategory = productCategoryService.findProductCategoryByName(category);
            productsList = productService.findProductsByCategory(productCategory);
            typesList = productTypeService.findProductTypeByCategory(productCategory);
        }
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("typeList", typesList);
        modelAndView.addObject("brandList", brandList);
        modelAndView.addObject("products", productsList);

        return modelAndView;
    }

    @RequestMapping(value = "/searchByType", method = RequestMethod.POST)
    public ModelAndView searchByType(@RequestParam(value = "typeChoice") String type) {
        ModelAndView modelAndView = new ModelAndView("home");
        List<ProductCategory> categoryList = productCategoryService.findAllProductCategory();
        List<ProductType> typeList = productTypeService.findAllProductType();
        List<ProductBrand> brandList = productBrandService.findAllProductBrand();
        List<ProductModel> productsList = null;

        if (!type.equals("--||--")) {
            ProductType productType = productTypeService.findProductTypeByName(type);
            productsList = productService.findProductsByType(productType);
        }

        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("typeList", typeList);
        modelAndView.addObject("brandList", brandList);
        modelAndView.addObject("products", productsList);

        return modelAndView;
    }

    @RequestMapping(value = "searchByBrand", method = RequestMethod.POST)
    public ModelAndView searchByBrand(@RequestParam(value = "brandChoice") String brand) {
        ModelAndView modelAndView = new ModelAndView("home");
        List<ProductCategory> categoryList = productCategoryService.findAllProductCategory();
        List<ProductType> typeList = productTypeService.findAllProductType();
        List<ProductBrand> brandList = productBrandService.findAllProductBrand();
        List<ProductModel> productsList = null;

        if (!brand.equals("--||--")) {
            ProductBrand productBrand = productBrandService.findProductBrandByName(brand);
            productsList = productService.findProductsByBrand(productBrand);
        }

        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("typeList", typeList);
        modelAndView.addObject("brandList", brandList);
        modelAndView.addObject("products", productsList);

        return modelAndView;
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.POST)
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView("loginPage");
        return modelAndView;
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView loginPage1() {
        ModelAndView modelAndView = new ModelAndView("loginPage");
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