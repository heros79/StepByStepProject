package am.lavshuka.lad.dto.product;

import am.lavshuka.lad.model.product.BuySellActionProduct;
import am.lavshuka.lad.model.product.ProductBrand;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by @Author David Karchikyan
 * this class present {@link am.lavshuka.lad.model.product.ProductModel} DTO (Data Transfer Object),
 * use lombok API to create fields getters, setters and constructor.
 */

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private Long productID;
    private String productName;
    private String productImageFilePath;
    private String productVendorCode;
    private Double productPrice;
    private String productDescription;
    private ProductCategory productCategoryDto;
    private ProductType productTypeDto;
    private ProductBrand productBrandDto;
    private List<BuySellActionProduct> productsActionDto;

    public ProductDTO setDtoProductID(Long id) {
        this.productID = id;
        return this;
    }

    public ProductDTO setDtoProductName(String name) {
        this.productName = name;
        return this;
    }

    public ProductDTO setDtoProductImageFilePath(String filePath) {
        this.productImageFilePath = filePath;
        return this;
    }

    public ProductDTO setDtoProductVendorCode(String vendorCode) {
        this.productVendorCode = vendorCode;
        return this;
    }

    public ProductDTO setDtoProductPrice(Double price) {
        this.productPrice = price;
        return this;
    }

    public ProductDTO setDtoProductDescription(String description) {
        this.productDescription = description;
        return this;
    }

    public ProductDTO setDtoProductCategory(ProductCategory productCategory) {
        this.productCategoryDto = productCategory;
        return this;
    }

    public ProductDTO setDtoProductType(ProductType productType) {
        this.productTypeDto = productType;
        return this;
    }

    public ProductDTO setDtoProductBrand(ProductBrand productBrand) {
        this.productBrandDto = productBrand;
        return this;
    }

    public ProductDTO setDtoProductAction(List<BuySellActionProduct> actionsList) {
        this.productsActionDto = actionsList;
        return this;
    }
}