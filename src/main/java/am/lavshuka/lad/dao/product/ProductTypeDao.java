package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class ProductTypeDao {

    private PreparedStatement statement = null;

    public void addProductType(ProductType productType) throws SQLException {

        statement = DBconn.getInstance().connection().prepareStatement("INSERT INTO prodtype (category_id, typename) VALUE (?, ?)");

        statement.setLong(1, productType.getProductCategoryId());
        statement.setString(2, productType.getProductTypeName());

        statement.executeUpdate();
    }

    public ProductType findByTypeName(String typeName) throws SQLException {

        ProductType productType = new ProductType();

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM prodtype WHERE typename = ?");

        statement.setString(1, typeName);

        ResultSet result = statement.executeQuery();

        result.next();

        productType.setId(result.getLong(1));
        productType.setProductCategoryId(result.getLong(2));
        productType.setProductTypeName(result.getString(3));

        return productType;
    }

    public List<ProductType> findAllProductType() throws SQLException {

        List<ProductType> list = new ArrayList<>();
        ProductType productType;

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM prodtype");

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            productType = new ProductType();
            productType.setId(result.getLong(1));
            productType.setProductCategoryId(result.getLong(2));
            productType.setProductTypeName(result.getString(3));

            list.add(productType);
        }

        return list;
    }

    public List<ProductType> findAllByProductCategory(ProductCategory productCategory) throws SQLException {

        List<ProductType> list = new ArrayList<>();
        ProductType productType;

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM prodtype WHERE category_id = ?");

        statement.setLong(1, productCategory.getId());

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            productType = new ProductType();
            productType.setId(result.getLong(1));
            productType.setProductCategoryId(result.getLong(2));
            productType.setProductTypeName(result.getString(3));

            list.add(productType);
        }

        return list;
    }
}
