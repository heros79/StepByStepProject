package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.product.ProductBrand;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class ProductBrandDao {

    private PreparedStatement statement = null;

    public void addProductBrand(ProductBrand productBrand) throws SQLException {

        statement = DBconn.getInstance().connection().prepareStatement("INSERT INTO prodbrand (brandname) VALUE (?)");

        statement.setString(1, productBrand.getProductBrandName());

        statement.executeUpdate();
    }

    public ProductBrand findByProductBrandName(String brandName) throws SQLException {

        ProductBrand productBrand = new ProductBrand();

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM prodbrand WHERE brandname = ?");

        statement.setString(1, brandName);

        ResultSet result = statement.executeQuery();

        result.next();

        productBrand.setId(result.getLong(1));
        productBrand.setProductBrandName(result.getString(2));

        return productBrand;
    }

    public List<ProductBrand> findAllProductBrand() throws SQLException {

        List<ProductBrand> list = new ArrayList<>();
        ProductBrand productBrand;

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM prodbrand");

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            productBrand = new ProductBrand();
            productBrand.setId(result.getLong(1));
            productBrand.setProductBrandName(result.getString(2));
            list.add(productBrand);
        }

        return list;
    }
}
