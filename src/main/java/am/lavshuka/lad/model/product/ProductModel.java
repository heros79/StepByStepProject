package am.lavshuka.lad.model.product;

import javax.persistence.*;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Entity
@Table(name = "products")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private ProductBrand productBrand;

    @Column(name = "vendorcode")
    private String vendorCode;

    @Column(name = "productname")
    private String productName;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "filepath")
    private String productImageFilePath;

    @OneToMany(mappedBy = "productModel")
    private List<BuySellActionProduct> buySellActionProductList;

    public ProductModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ProductBrand getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(ProductBrand productBrand) {
        this.productBrand = productBrand;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImageFilePath() {
        return productImageFilePath;
    }

    public void setProductImageFilePath(String productImageFilePath) {
        this.productImageFilePath = productImageFilePath;
    }

    public List<BuySellActionProduct> getBuySellActionProductList() {
        return buySellActionProductList;
    }

    public void setBuySellActionProductList(List<BuySellActionProduct> buySellActionProductList) {
        this.buySellActionProductList = buySellActionProductList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductModel productModel = (ProductModel) o;

        if (!vendorCode.equals(productModel.vendorCode))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 31 * vendorCode.hashCode();
    }
}