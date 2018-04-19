package am.lavshuka.lad.model.product;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class ProductModel {

    private Long id;
    private Long categoryId;
    private Long typeId;
    private Long brendId;
    private String vendorCode;
    private String productName;
    private Double price;
    private String description;
    private String productImageFilePath;

    public ProductModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getBrendId() {
        return brendId;
    }

    public void setBrendId(Long brendId) {
        this.brendId = brendId;
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
