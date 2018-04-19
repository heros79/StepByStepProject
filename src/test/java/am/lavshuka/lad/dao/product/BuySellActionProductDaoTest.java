package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.product.BuySellActionProduct;
import am.lavshuka.lad.model.product.ProductModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class BuySellActionProductDaoTest {

    @Before
    public void init() {
        ProductModel product = null;
        try {
            product = new ProductDao().findByVendorCode("soap-1");
            new BuySellActionProductDao().buyProduct(product, 1000000, new Date());
            new BuySellActionProductDao().sellProduct(product, 1000000, new Date());
        } catch (SQLException e) {
            fail("SQL exeption in buyProduct or SellProduct methods");
        }
    }

    @Test
    public void findBuyProductByDateTest() {
        List<BuySellActionProduct> list = null;
        int a = 0;

        try {
            PreparedStatement statement = DBconn.getInstance().connection().prepareStatement("SELECT COUNT(*) FROM productbuysell " +
                    "WHERE buydate IS NOT NULL ");

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            a = resultSet.getInt(1);

            list = new BuySellActionProductDao().findBuyProductByDate(new Date());

        } catch (SQLException e) {
            fail("SQL exeption in findBuyProductByDate method");
        }

        assertEquals(list.size(), a);
    }

    @Test
    public void findSellProductByDateTest() {
        List<BuySellActionProduct> list = null;
        int a = 0;

        try {
            PreparedStatement statement = DBconn.getInstance().connection().prepareStatement("SELECT COUNT(*) FROM productbuysell " +
                    "WHERE selldate IS NOT NULL ");

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            a = resultSet.getInt(1);

            list = new BuySellActionProductDao().findSellProductByDate(new Date());

        } catch (SQLException e) {
            fail("SQL exeption in findSellProductByDate method");
        }

        assertEquals(list.size(), a);
    }

    @After
    public void destroi() {
        PreparedStatement statement;
        try {
            statement = DBconn.getInstance().connection().prepareStatement("DELETE FROM productbuysell WHERE count = 1000000");
            statement.executeUpdate();
            statement = DBconn.getInstance().connection().prepareStatement("DELETE FROM productbuysell WHERE count = -1000000");
            statement.executeUpdate();
        } catch (SQLException e) {
            fail("Any SQL exeptions");
        }
    }
}
