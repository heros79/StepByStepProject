package am.lavshuka.lad.controller.product;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by David on 5/17/2018.
 */

@Controller
public class SearchController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductBrandService productBrandService;

    @RequestMapping(value = "/searchByCategory", method = RequestMethod.POST)
    public ModelAndView searchByCategory(@RequestParam(value = "categoryChoice") String category) {
        ModelAndView modelAndView = new ModelAndView("home");
        List<ProductModel> productsList = null;
        List<ProductType> typesList = null;
        List<ProductCategory> categoryList = productCategoryService.findAllProductCategory();
        List<ProductBrand> brandList = productBrandService.findAllProductBrand();
        if (!category.equals("choiceAllCategory") && !category.equals("--||--")) {
            ProductCategory productCategory = productCategoryService.findProductCategoryByName(category);
            productsList = productService.findProductsByCategory(productCategory);
            typesList = productTypeService.findProductTypeByCategory(productCategory);
        } else if (category.equals("choiceAllCategory")) {
            productsList = productService.findAllProducts();
            typesList = productTypeService.findAllProductType();
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

        if (!type.equals("choiceAllType") && !type.equals("--||--")) {
            ProductType productType = productTypeService.findProductTypeByName(type);
            productsList = productService.findProductsByType(productType);
        } else if (type.equals("choiceAllType")){
            productsList = productService.findAllProducts();
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

        if (!brand.equals("choiceAllBrand") && !brand.equals("--||--")) {
            ProductBrand productBrand = productBrandService.findProductBrandByName(brand);
            productsList = productService.findProductsByBrand(productBrand);
        } else if (brand.equals("choiceAllBrand")){
            productsList = productService.findAllProducts();
        }

        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("typeList", typeList);
        modelAndView.addObject("brandList", brandList);
        modelAndView.addObject("products", productsList);

        return modelAndView;
    }
}