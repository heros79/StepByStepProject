package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.product.ProductCategory;
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
public class ProductCategoryDaoTest {

    private PreparedStatement statement = null;
    private ProductCategory productCategory = new ProductCategory();

    @Before
    public void addProductCategotyTest() {

        productCategory.setProductCategoryName("test");

        try {
            new ProductCategoryDao().addProductCategoty(productCategory);
        } catch (SQLException e) {
            fail("Any SQL Exeption addProductCategoty method");

        }
    }

    @Test
    public void findByCategoryNameTest() {

        try {
            productCategory = new ProductCategoryDao().findByCategoryName("test");
        } catch (SQLException e) {
            fail("Any SQL Exeption findByCategoryName method");
        }
        assertEquals(productCategory.getProductCategoryName(), "test");
    }

    @Test
    public void findAllProductCategoryTest() {

        List<ProductCategory> list = null;
        int a = 0;

        try {
            list = new ProductCategoryDao().findAllProductCategory();
            statement = DBconn.getInstance().connection().prepareStatement("SELECT COUNT(*) FROM prodcategory");
            ResultSet result = statement.executeQuery();
            result.next();
            a = result.getInt(1);
        } catch (SQLException e) {
            fail("Any SQL Exeption findAllProductCategory method");
        }
        assertEquals(list.size(), a);
    }

    @After
    public void destroi() {
        try {
            statement = DBconn.getInstance().connection().prepareStatement("DELETE FROM prodcategory " +
                    "WHERE categoryname = ?");
            statement.setString(1, "test");
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            fail("Any SQL Exeption destroi method");
        }
        productCategory = null;
        statement = null;
    }
}
