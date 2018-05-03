package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductsByCount;
import org.hibernate.HibernateException;
import org.junit.Test;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class ProductsByCountDaoTest {

    @Test
    public void findProductWithCountTest() {

        ProductModel product = null;
        ProductsByCount productsByCount = null;

        try {
            product = new ProductDao().findByVendorCode("soap-1");
            productsByCount = new ProductsByCountDao().findProductWithCount(product);
        } catch (HibernateException e) {
            fail("Any SQL exeption in findProductWithCount method");
        }

        assertEquals(product.getProductName(), productsByCount.getProductName());
    }

    @Test
    public void findAllProductssWithCountTest() {

        List<ProductsByCount> list = null;
        int a = 0;

        try {
            list = new ProductsByCountDao().findAllProductssWithCount();
            PreparedStatement statement = DBconn.getInstance().connection().prepareStatement(
                    "SELECT COUNT(*) FROM totalproducts");

            ResultSet result = statement.executeQuery();

            result.next();

            a = result.getInt(1);
        } catch (SQLException e) {
            fail("Any SQL exeption in findAllProductssWithCount method");
        }

        assertEquals(list.size(), a);
    }
}