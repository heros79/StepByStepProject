package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductType;
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
public class ProductTypeDaoTest {

    private ProductType productType = new ProductType();

    @Before
    public void addProductTypeTest() {

        try {
            productType.setProductCategoryId(new ProductCategoryDao().findByCategoryName("household").getId());
            productType.setProductTypeName("test");
            new ProductTypeDao().addProductType(productType);
        } catch (SQLException e) {
            fail("Any SQL Exeption in addProductType method");
        }
    }

    @Test
    public void findByTypeNameTest() {

        try {
            productType = new ProductTypeDao().findByTypeName("test");
        } catch (SQLException e) {
            fail("Any SQL Exeption in findByTypeName method");
        }

        assertEquals(productType.getProductTypeName(), "test");
    }

    @Test
    public void findAllProductTypeTest() {
        PreparedStatement statement;
        List<ProductType> list = null;
        int a = 0;

        try {
            list = new ProductTypeDao().findAllProductType();
            statement = DBconn.getInstance().connection().prepareStatement("SELECT COUNT(*) FROM prodtype");
            ResultSet result = statement.executeQuery();
            result.next();
            a = result.getInt(1);
        } catch (SQLException e) {
            fail("Any SQL Exeption in findAllProductType method");
        }

        assertEquals(list.size(), a);
    }

    @Test
    public void findAllByProductCategoryTest() {
        PreparedStatement statement;
        List<ProductType> list = null;
        ProductCategory productCategory = null;
        int a = 0;

        try {
            productCategory = new ProductCategoryDao().findByCategoryName("household");
            list = new ProductTypeDao().findAllByProductCategory(productCategory);
            statement = DBconn.getInstance().connection().prepareStatement("SELECT COUNT(*) FROM prodtype " +
                    "WHERE category_id = ?");
            statement.setLong(1, productCategory.getId());
            ResultSet result = statement.executeQuery();
            result.next();
            a = result.getInt(1);
        } catch (SQLException e) {
            fail("Any SQL Exeption in findAllByProductCategory method");
        }

        assertEquals(list.size(), a);
    }

    @After
    public void distroi() {
        PreparedStatement statement;
        try {
            statement = DBconn.getInstance().connection().prepareStatement("DELETE FROM prodtype " +
                    "WHERE typename = ?");
            statement.setString(1, "test");
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            fail("Any SQL Exeption destroi method");
        }
        productType = null;
    }
}
