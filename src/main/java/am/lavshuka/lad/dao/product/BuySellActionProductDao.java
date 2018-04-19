package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.product.BuySellActionProduct;
import am.lavshuka.lad.model.product.ProductModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class BuySellActionProductDao {

    private PreparedStatement statement = null;

    public void buyProduct(ProductModel productModel, int count, Date buyDate) throws SQLException {

        statement = DBconn.getInstance().connection().prepareStatement("INSERT INTO productbuysell (product_id, count, buydate) VALUE (?, ?, ?)");
        statement.setLong(1, productModel.getId());
        statement.setInt(2, count);
        statement.setDate(3, convertUtilDateToSqlDate(buyDate));

        statement.executeUpdate();
    }

    public void sellProduct(ProductModel productModel, int count, Date sellDate) throws SQLException {
        statement = DBconn.getInstance().connection().prepareStatement("INSERT INTO productbuysell (product_id, count, selldate) VALUE (?, ?, ?)");
        statement.setLong(1, productModel.getId());
        statement.setInt(2, (count * -1));
        statement.setDate(3, convertUtilDateToSqlDate(sellDate));

        statement.executeUpdate();
    }

    public List<BuySellActionProduct> findBuyProductByDate(java.util.Date date) throws SQLException {
        List<BuySellActionProduct> list = new ArrayList<>();
        BuySellActionProduct product;

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM productbuysell WHERE buydate = ?");
        statement.setDate(1, convertUtilDateToSqlDate(date));

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            product = new BuySellActionProduct();

            product.setId(result.getLong(1));
            product.setProduct_id(result.getLong(2));
            product.setCount(result.getInt(3));
            product.setProductBuyDate(convertSqlDateToUtilDate(result.getDate(4)));
            product.setProductSellDate(result.getDate(5));

            list.add(product);
        }

        return list;
    }

    public List<BuySellActionProduct> findSellProductByDate(java.util.Date date) throws SQLException {
        List<BuySellActionProduct> list = new ArrayList<>();
        BuySellActionProduct product;

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM productbuysell WHERE selldate = ?");
        statement.setDate(1, convertUtilDateToSqlDate(date));

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            product = new BuySellActionProduct();

            product.setId(result.getLong(1));
            product.setProduct_id(result.getLong(2));
            product.setCount(result.getInt(3));
            product.setProductBuyDate(result.getDate(4));
            product.setProductSellDate(convertSqlDateToUtilDate(result.getDate(5)));

            list.add(product);
        }

        return list;
    }

    private java.sql.Date convertUtilDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    private java.util.Date convertSqlDateToUtilDate(java.sql.Date date) {
        return new java.util.Date(date.getTime());
    }
}
