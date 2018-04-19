package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.product.ProductBrand;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class ProductDao {

    private PreparedStatement statement = null;

    public void addProduct(ProductModel productModel) throws SQLException {

        statement = DBconn.getInstance().connection().prepareStatement("INSERT INTO products " +
                "(category_id, type_id, brand_id, vendorcode, productname, price, description, filepath) " +
                "VALUE (?, ?, ?, ?, ?, ?, ?, ?)");

        statement.setLong(1, productModel.getCategoryId());
        statement.setLong(2, productModel.getTypeId());
        statement.setLong(3, productModel.getBrendId());
        statement.setString(4, productModel.getVendorCode());
        statement.setString(5, productModel.getProductName());
        statement.setDouble(6, productModel.getPrice());
        statement.setString(7, productModel.getDescription());
        statement.setString(8, productModel.getProductImageFilePath());

        statement.executeUpdate();
    }

    public ProductModel findByVendorCode(String vendorCode) throws SQLException {

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM products " +
                "WHERE vendorcode = ?");
        statement.setString(1, vendorCode);

        ResultSet result = statement.executeQuery();

        result.next();

        ProductModel productModel = setProductModelData(result, new ProductModel());

        return productModel;
    }

    public List<ProductModel> findAllProducts() throws SQLException {

        List<ProductModel> list = new ArrayList<>();
        ProductModel productModel;

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM  products");

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            productModel = setProductModelData(result, new ProductModel());
            list.add(productModel);
        }

        return list;
    }

    public List<ProductModel> findProductsByCategory(ProductCategory productCategory) throws SQLException {

        List<ProductModel> list = new ArrayList<>();
        ProductModel productModel;

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM products WHERE category_id = ?");

        statement.setLong(1, productCategory.getId());

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            productModel = setProductModelData(result, new ProductModel());
            list.add(productModel);
        }
        return list;
    }

    public List<ProductModel> findProductsByType(ProductType productType) throws SQLException {

        List<ProductModel> list = new ArrayList<>();
        ProductModel productModel;

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM products WHERE type_id = ?");

        statement.setLong(1, productType.getId());

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            productModel = setProductModelData(result, new ProductModel());
            list.add(productModel);
        }
        return list;
    }

    public List<ProductModel> findProductsByBrand(ProductBrand productBrand) throws SQLException {

        List<ProductModel> list = new ArrayList<>();
        ProductModel productModel;

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM products WHERE brand_id = ?");

        statement.setLong(1, productBrand.getId());

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            productModel = setProductModelData(result, new ProductModel());
            list.add(productModel);
        }

        return list;
    }

    public void changeProductData(ProductModel productModel, Double price, String description) throws SQLException {

        if (productModel == null || (price == null && description == null)) {
            throw new IllegalArgumentException();
        }

        if (price != null) {

            statement = DBconn.getInstance().connection().prepareStatement("UPDATE products SET price = ? " +
                    "WHERE vendorcode = ?");

            statement.setDouble(1, price);
            statement.setString(2, productModel.getVendorCode());

            statement.executeUpdate();
        }

        if (description != null) {

            statement = DBconn.getInstance().connection().prepareStatement("UPDATE products SET description = ? " +
                    "WHERE vendorcode = ?");

            statement.setString(1, description);
            statement.setString(2, productModel.getVendorCode());

            statement.executeUpdate();
        }

    }

    public void removeProduct(String vendorCode) throws SQLException {

        statement = DBconn.getInstance().connection().prepareStatement("DELETE FROM products WHERE vendorcode = ?");

        statement.setString(1, vendorCode);

        statement.executeUpdate();
    }

    private ProductModel setProductModelData(ResultSet result, ProductModel productModel) throws SQLException {

        productModel.setId(result.getLong(1));
        productModel.setCategoryId(result.getLong(2));
        productModel.setTypeId(result.getLong(3));
        productModel.setBrendId(result.getLong(4));
        productModel.setVendorCode(result.getString(5));
        productModel.setProductName(result.getString(6));
        productModel.setPrice(result.getDouble(7));
        productModel.setDescription(result.getString(8));
        productModel.setProductImageFilePath(result.getString(9));

        return productModel;
    }

}
