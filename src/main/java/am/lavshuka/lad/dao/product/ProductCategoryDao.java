package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.product.ProductCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class ProductCategoryDao {

    private PreparedStatement statement = null;

    public void addProductCategoty(ProductCategory category) throws SQLException {

        statement = DBconn.getInstance().connection().prepareStatement("INSERT INTO prodcategory (categoryname)" +
                "VALUE (?)");

        statement.setString(1, category.getProductCategoryName());

        statement.executeUpdate();
    }

    public ProductCategory findByCategoryName(String categoryName) throws SQLException {

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM prodcategory " +
                "WHERE categoryname = ?");

        statement.setString(1, categoryName);

        ResultSet result = statement.executeQuery();

        result.next();

        ProductCategory productCategory = new ProductCategory();

        productCategory.setId(result.getLong(1));
        productCategory.setProductCategoryName(result.getString(2));

        return productCategory;
    }

    public List<ProductCategory> findAllProductCategory() throws SQLException {

        List<ProductCategory> list = new ArrayList<>();

        ProductCategory productCategory;

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM prodcategory");

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            productCategory = new ProductCategory();
            productCategory.setId(result.getLong(1));
            productCategory.setProductCategoryName(result.getString(2));
            list.add(productCategory);
        }
        return list;
    }
}
