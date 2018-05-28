package am.lavshuka.lad.controller.product;

import am.lavshuka.lad.model.product.ProductBrand;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductType;
import am.lavshuka.lad.service.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by David on 5/18/2018.
 */


public class AddProductController {

    /*@Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductBrandService productBrandService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategory productCategory;

    @Autowired
    private ProductType productType;

    @Autowired
    private ProductBrand productBrand;

    @Autowired
    private ProductModel productModel;

    @Autowired
    private BuySellActionProductService productAction;

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public ModelAndView addNewCategory(@RequestParam(value = "addCategory") String category) {

        ModelAndView modelAndView = new ModelAndView("redirect:/admin");

        productCategory = productCategoryService.findProductCategoryByName(category);

        if (null != productCategory) {
            modelAndView.addObject("categoryMassage", "null");
            return modelAndView;
        } else if (category.equals("")){
            modelAndView.addObject("categoryMassage", "have");
            return modelAndView;
        }

        productCategory = new ProductCategory();
        productCategory.setProductCategoryName(category);
        productCategoryService.addProductCategory(productCategory);

        return modelAndView;
    }

    @RequestMapping("/addType")
    public ModelAndView addNewType(@RequestParam(value = "addType") String type,
                                   @RequestParam(value = "categoryChoice") String category) {

        ModelAndView modelAndView = new ModelAndView("redirect:/admin");

        productType = productTypeService.findProductTypeByName(type);

        if (null != productType) {
            modelAndView.addObject("typeMassage", "have");
            return modelAndView;
        }

        if (type.equals("")) {
            modelAndView.addObject("typeMassage", "null");
            return modelAndView;
        }

        if (category.equals("--||--")) {
            modelAndView.addObject("typeMassage", "select");
            return modelAndView;
        }

        productCategory = productCategoryService.findProductCategoryByName(category);

        productType = new ProductType();
        productType.setProductCategory(productCategory);
        productType.setProductTypeName(type);
        productTypeService.addProductType(productType);

        return modelAndView;
    }

    @RequestMapping("/addBrand")
    public ModelAndView addNewBrand(@RequestParam(value = "addBrand") String brand) {

        ModelAndView modelAndView = new ModelAndView("redirect:/admin");

        productBrand = productBrandService.findProductBrandByName(brand);

        if (null != productBrand) {
            modelAndView.addObject("brandMassage", "you have this " + brand + " brand");
            return modelAndView;
        }

        productBrand = new ProductBrand();
        productBrand.setProductBrandName(brand);
        productBrandService.addProductBrand(productBrand);

        return modelAndView;
    }

    @RequestMapping("/addNewProduct")
    public ModelAndView addNewProduct(@RequestParam(value = "categoryChoice", required = false) String category,
                                      @RequestParam(value = "typeChoice", required = false) String type,
                                      @RequestParam(value = "brandChoice", required = false) String brand,
                                      @RequestParam(value = "productName") String productName,
                                      @RequestParam(value = "productPrice") Double price,
                                      @RequestParam(value = "description") String description,
                                      @RequestParam(value = "vendorCode") String vendorCode,
                                      @RequestParam(value = "filePath") String filePath) {

        ModelAndView modelAndView = new ModelAndView("redirect:/admin");

        productCategory = productCategoryService.findProductCategoryByName(category);
        productType = productTypeService.findProductTypeByName(type);
        productBrand = productBrandService.findProductBrandByName(brand);

        if(productName.equals("") || price.equals("") || description.equals("") || vendorCode.equals("") || filePath.equals("")){
            modelAndView.addObject("productMassage", "fill all data please");
            return modelAndView;
        }

        productModel = productService.findProductByVendorCode(vendorCode);

        if (null != productModel) {
            modelAndView.addObject("productMassage", "you have this " + vendorCode + " vendore code");
            return modelAndView;
        }

        if (new Double(price) < 0) {
            modelAndView.addObject("productMassage", "you fill wrong price");
            return modelAndView;
        }

        productModel = new ProductModel();
        productModel.setProductCategory(productCategory);
        productModel.setProductType(productType);
        productModel.setProductBrand(productBrand);
        productModel.setProductName(productName);
        productModel.setPrice(new Double(price));
        productModel.setVendorCode(vendorCode);
        productModel.setDescription(description);
        productModel.setProductImageFilePath(filePath);

        productService.addProduct(productModel);

        return modelAndView;
    }

    @RequestMapping("/addProductCount")
    public ModelAndView addProductCount(@RequestParam(value = "productChoice", required = false) String productData,
                                @RequestParam(value = "productCount", required = false) Integer count) {

        ModelAndView modelAndView = new ModelAndView("redirect:/admin");

        productModel = productService.findProductByVendorCode(productData);

        if (null == productModel) {
            return modelAndView;
        }

        productAction.buyProduct(productModel, count, new Date());

        return modelAndView;
    }*/
}