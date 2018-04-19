package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductsByCount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class ProductsByCountDao {

    private PreparedStatement statement = null;

    public ProductsByCount findProductWithCount(ProductModel productModel) throws SQLException {

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM totalproducts WHERE vendorcode = ?");
        statement.setString(1, productModel.getVendorCode());

        ResultSet result = statement.executeQuery();

        ProductsByCount product = new ProductsByCount();

        result.next();

        product.setProductName(result.getString(1));
        product.setVendorCode(result.getString(2));
        product.setProductId(result.getLong(3));
        product.setCount(result.getDouble(4));

        return product;
    }

    public List<ProductsByCount> findAllProductssWithCount() throws SQLException {

        List<ProductsByCount> list = new ArrayList<>();
        ProductsByCount product;

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM totalproducts");

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            product = new ProductsByCount();
            product.setProductName(result.getString(1));
            product.setVendorCode(result.getString(2));
            product.setProductId(result.getLong(3));
            product.setCount(result.getDouble(4));

            list.add(product);
        }
        return list;
    }
}
