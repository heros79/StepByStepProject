package am.lavshuka.lad.service.product;

import am.lavshuka.lad.dao.product.ProductDao;
import am.lavshuka.lad.dto.ProductDTO;
import am.lavshuka.lad.model.product.ProductBrand;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @Author David Karchikyan
 * this class present Product actions service
 * and uses {@link am.lavshuka.lad.model.product.ProductModel},
 * {@link am.lavshuka.lad.dao.product.ProductDao},
 * {@link am.lavshuka.lad.dto.ProductDTO} classes
 */

@Service
public class ProductService {

    /**
     * add Product object into DB
     *
     * @param productModel
     */
    public void addProduct(ProductModel productModel) {
        new ProductDao().add(productModel);
    }

    public ProductModel findProductByVendorCode(String vendorCode) {
        return new ProductDao().findByVendorCode(vendorCode);
    }

    /**
     * read all Product from DB and
     *
     * @return {@link am.lavshuka.lad.dto.ProductDTO} list
     */
    public List<ProductDTO> findAllProducts() {
        List<ProductDTO> list = new ArrayList<>();
        List<ProductModel> productsList = new ProductDao().findAll(ProductModel.class);

        for (ProductModel b : productsList) {
            list.add(new ProductDTO()
                    .setDtoProductID(b.getId())
                    .setDtoProductName(b.getProductName())
                    .setDtoProductVendorCode(b.getVendorCode())
                    .setDtoProductDescription(b.getDescription())
                    .setDtoProductImageFilePath(b.getProductImageFilePath())
                    .setDtoProductPrice(b.getPrice()));
        }
        return list;
    }

    /**
     * Read all Product from DB where Category @param productCategory
     * and @return {@link am.lavshuka.lad.dto.ProductDTO} list
     */
    public List<ProductDTO> findProductsByCategory(ProductCategory productCategory) {
        List<ProductDTO> list = new ArrayList<>();
        List<ProductModel> productList = productCategory.getProductModelSet();

        for (ProductModel b : productList) {
            list.add(new ProductDTO()
                    .setDtoProductID(b.getId())
                    .setDtoProductName(b.getProductName())
                    .setDtoProductVendorCode(b.getVendorCode())
                    .setDtoProductDescription(b.getDescription())
                    .setDtoProductImageFilePath(b.getProductImageFilePath())
                    .setDtoProductPrice(b.getPrice()));
        }
        return list;
    }

    public List<ProductModel> findProductsByType(ProductType productType) {
        return productType.getProductModelSet();
    }

    public List<ProductModel> findProductsByBrand(ProductBrand productBrand) {
        return productBrand.getProductModelSet();
    }

    public void changeProductData(ProductModel productModel, Double price, String description) {

        if (productModel == null || (price == null && description == null))
            throw new IllegalArgumentException();
        if (price <= 0)
            throw new IllegalArgumentException();

        if (price != null)
            productModel.setPrice(price);

        if (description != null)
            productModel.setDescription(description);

        new ProductDao().changeProductData(productModel);
    }

    public void removeProduct(ProductModel productModel) {
        new ProductDao().remove(productModel);
    }

    private String setVendorCode(ProductModel productModel) {
        return new String(String.valueOf(productModel.getProductBrand().getProductBrandName().charAt(0))
                .concat("-").concat(String.valueOf(productModel.getProductType().getProductTypeName().charAt(0)))
                .concat("-").concat(String.valueOf(productModel.getProductBrand().getProductBrandName().charAt(0)))
                .concat(String.valueOf("000001")));
    }
}