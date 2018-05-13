package am.lavshuka.lad.controller;

import am.lavshuka.lad.model.product.ProductBrand;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductType;
import am.lavshuka.lad.model.user.UserModel;
import am.lavshuka.lad.service.product.ProductBrandService;
import am.lavshuka.lad.service.product.ProductCategoryService;
import am.lavshuka.lad.service.product.ProductService;
import am.lavshuka.lad.service.product.ProductTypeService;
import am.lavshuka.lad.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by David on 5/9/2018.
 */

@Controller("/")
public class MainController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserModel userModel;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductBrandService productBrandService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        List<ProductModel> productList = productService.findAllProducts();
        modelAndView.addObject("products", productList);
        return modelAndView;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "username") String login,
                              @RequestParam(value = "password") String pass) {
        ModelAndView modelAndView = new ModelAndView("/login");
/*        if (new UserService().loginUser(login, pass)) {
            userModel = new UserService().getUserByLogin(login);
            if (userModel.getRole() == UserModel.Role.ROLE_USER) {
                modelAndView = new ModelAndView("home");
                List<ProductCategory> categoryList = productCategoryService.findAllProductCategory();
                modelAndView.addObject("category", categoryList);
                List<ProductType> typeList = productTypeService.findAllProductType();
                modelAndView.addObject("type", typeList);
                List<ProductBrand> brandList = productBrandService.findAllProductBrand();
                modelAndView.addObject("brand", brandList);
            } else if (userModel.getRole() == UserModel.Role.ROLE_ADMIN) {
                modelAndView = new ModelAndView("admin");
            }
        } else {
            modelAndView =  new ModelAndView("redirect:/");
            return modelAndView;
        }*/
        return modelAndView;
    }

    @RequestMapping(value = "/register")
    @RequestScope
    public ModelAndView regpage() {
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @SessionScope
    public ModelAndView register(@RequestParam(value = "login") String login,
                                 @RequestParam(value = "firstName") String firstName,
                                 @RequestParam(value = "lastName") String lastName,
                                 @RequestParam(value = "email") String email,
                                 @RequestParam(value = "pass") String pass,
                                 @RequestParam(value = "confirmPass") String confirmPass) {

        ModelAndView modelAndView = null;
        if (!pass.equals(confirmPass))
            modelAndView = new ModelAndView("redirect:/register");
        userModel = new UserModel();
        userModel.setLogin(login);
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPassHash(pass);

        new UserService().registerUser(userModel);

        modelAndView = new ModelAndView("redirect:/");

        return modelAndView;
    }

}