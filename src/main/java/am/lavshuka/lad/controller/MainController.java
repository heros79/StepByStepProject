package am.lavshuka.lad.controller;

import am.lavshuka.lad.model.product.ProductBrand;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductType;
import am.lavshuka.lad.service.product.ProductBrandService;
import am.lavshuka.lad.service.product.ProductCategoryService;
import am.lavshuka.lad.service.product.ProductService;
import am.lavshuka.lad.service.product.ProductTypeService;
import am.lavshuka.lad.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private UserService userService;

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
        String login = userService.getAuthenticatedUserData();
        if ("anonymousUser" != login) {
            modelAndView.addObject("money", userService.getUserByLogin(login).getMoney());
        }

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

    @RequestMapping("/admin")
    public ModelAndView admin() {
        return new ModelAndView("admin");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView logout(String logout) {
        return new ModelAndView("redirect:/index");
    }
}