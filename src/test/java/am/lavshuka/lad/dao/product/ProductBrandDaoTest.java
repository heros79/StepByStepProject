package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.product.ProductBrand;
import org.junit.After;
import org.junit.Before;
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

public class ProductBrandDaoTest {

    private PreparedStatement statement = null;
    private ProductBrand productBrand = new ProductBrand();

    @Before
    public void addProductBrandTest() {

        productBrand.setProductBrandName("test");

        try {
            new ProductBrandDao().addProductBrand(productBrand);
        } catch (SQLException e) {
            fail("Any SQL Exeption addProductBrand method");
        }
    }

    @Test
    public void findByProductBrandNameTest() {

        try {
            productBrand = new ProductBrandDao().findByProductBrandName("test");
        } catch (SQLException e) {
            fail("Any SQL Exeption findByProductBrandName method");
        }

        assertEquals(productBrand.getProductBrandName(), "test");
    }

    @Test
    public void findAllProductBrandTest() {

        List<ProductBrand> list = null;
        int a = 0;

        try {
            list = new ProductBrandDao().findAllProductBrand();
            statement = DBconn.getInstance().connection().prepareStatement("SELECT COUNT(*) FROM prodbrand");
            ResultSet result = statement.executeQuery();
            result.next();
            a = result.getInt(1);
        } catch (SQLException e) {
            fail("Any SQL Exeption findAllProductBrand method");
        }

        assertEquals(list.size(), a);
    }

    @After
    public void destroi() {
        try {
            statement = DBconn.getInstance().connection().prepareStatement("DELETE FROM prodbrand WHERE brandname = ?");
            statement.setString(1, "test");
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            fail("Any SQL Exeption destroi method");
        }
        productBrand = null;
        statement = null;
    }
}