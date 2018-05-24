package am.lavshuka.lad.controller.user;

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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by David on 5/18/2018.
 */

@Controller
public class AdministratorController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserModel userModel;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductBrandService productBrandService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/remove")
    public ModelAndView removeUser(@RequestParam(value = "userLogin") String login) {

        ModelAndView modelAndView = new ModelAndView("admin");
        userModel = userService.getUserByLogin(login);

        if (null == userModel) {
            modelAndView.addObject("massage", "With this login no user");
            return modelAndView;
        }
        userService.unRegisterUser(userModel);

        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin(@RequestParam(value = "categoryMassage", required = false)String categoryMassage,
                              @RequestParam(value = "typeMassage", required = false) String typeMassage) {

        ModelAndView modelAndView = new ModelAndView("admin");

        List<ProductCategory> listCategory = productCategoryService.findAllProductCategory();
        modelAndView.addObject("categoryList", listCategory);
        modelAndView.addObject("categoryListForProduct", listCategory);
        List<ProductType> listType = productTypeService.findAllProductType();
        modelAndView.addObject("typeList", listType);
        List<ProductBrand> listBrand = productBrandService.findAllProductBrand();
        modelAndView.addObject("brandList", listBrand);
        List<ProductModel> listModel = productService.findAllProducts();
        modelAndView.addObject("productList", listModel);

        if (null != categoryMassage) {
            if (categoryMassage.equals("have")) {
                modelAndView.addObject("categoryMassage", "need write category");
            } else if (categoryMassage.equals("null")) {
                modelAndView.addObject("categoryMassage", "You have this category");
            }
        }

        if (null != typeMassage) {
            if (typeMassage.equals("null")) {
                modelAndView.addObject("typeMassage", "need write type");
            } else if (typeMassage.equals("have")) {
                modelAndView.addObject("typeMassage", "You have this type");
            } else if (typeMassage.equals("select")) {
                modelAndView.addObject("typeMassage", "need choice category");
            }
        }

        return modelAndView;
    }
}