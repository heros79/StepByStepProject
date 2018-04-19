package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.product.ProductBrand;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductModel;
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
public class ProductDaoTest {

    private ProductModel productModel = new ProductModel();

    @Before
    public void addProductTest() {

        try {
            productModel.setCategoryId(new ProductCategoryDao().findByCategoryName("household").getId());
            productModel.setTypeId(new ProductTypeDao().findByTypeName("soap").getId());
            productModel.setBrendId(new ProductBrandDao().findByProductBrandName("IKEA").getId());
            productModel.setVendorCode("test");
            productModel.setProductName("test");
            productModel.setPrice(100.00);
            productModel.setDescription("test");
            productModel.setProductImageFilePath("test");

            new ProductDao().addProduct(productModel);
        } catch (SQLException e) {
            fail("Any SQL exeption addProduct method");
        }
    }

    @Test
    public void findByVendorCodeTest() {

        try {
            productModel = new ProductDao().findByVendorCode("test");
        } catch (SQLException e) {
            fail("Any SQL exeption findByVendorCode method");
        }

        assertEquals(productModel.getPrice(), new Double(100));
        assertEquals(productModel.getDescription(), "test");
    }

    @Test
    public void findAllProductsTest() {

        List<ProductModel> list = null;
        int a = 0;
        PreparedStatement statement;

        try {

            statement = DBconn.getInstance().connection().prepareStatement("SELECT COUNT(*) FROM products");

            ResultSet result = statement.executeQuery();
            result.next();
            a = result.getInt(1);

            list = new ProductDao().findAllProducts();

        } catch (SQLException e) {
            fail("Any SQL exeption findAllProducts method");
        }

        assertEquals(list.size(), a);
    }

    @Test
    public void findProductsByCategoryTest() {

        List<ProductModel> list = null;
        int a = 0;
        PreparedStatement statement;

        try {

            ProductCategory productCategory = new ProductCategoryDao().findByCategoryName("household");
            statement = DBconn.getInstance().connection().prepareStatement("SELECT COUNT(*) FROM products WHERE category_id = ?");
            statement.setLong(1, productCategory.getId());

            ResultSet result = statement.executeQuery();
            result.next();
            a = result.getInt(1);

            list = new ProductDao().findProductsByCategory(productCategory);

        } catch (SQLException e) {
            fail("Any SQL exeption findProductsByCategory method");
        }

        assertEquals(list.size(), a);
    }

    @Test
    public void findProductsByTypeTest() {

        List<ProductModel> list = null;
        int a = 0;
        PreparedStatement statement;

        try {

            ProductType productType = new ProductTypeDao().findByTypeName("soap");
            statement = DBconn.getInstance().connection().prepareStatement("SELECT COUNT(*) FROM products WHERE type_id = ?");
            statement.setLong(1, productType.getId());

            ResultSet result = statement.executeQuery();
            result.next();
            a = result.getInt(1);

            list = new ProductDao().findProductsByType(productType);

        } catch (SQLException e) {
            fail("Any SQL exeption findProductsByType method");
        }

        assertEquals(list.size(), a);
    }

    @Test
    public void findProductsByBrandTest() {

        List<ProductModel> list = null;
        int a = 0;
        PreparedStatement statement;

        try {

            ProductBrand productBrand = new ProductBrandDao().findByProductBrandName("IKEA");
            statement = DBconn.getInstance().connection().prepareStatement("SELECT COUNT(*) FROM products WHERE brand_id = ?");
            statement.setLong(1, productBrand.getId());

            ResultSet result = statement.executeQuery();
            result.next();
            a = result.getInt(1);

            list = new ProductDao().findProductsByBrand(productBrand);

        } catch (SQLException e) {
            fail("Any SQL exeption findProductsByType method");
        }

        assertEquals(list.size(), a);
    }

    @Test
    public void changeProductDataTest() {

        try {
            new ProductDao().changeProductData(null, null, "not null");
            productModel = new ProductDao().findByVendorCode("test");
            new ProductDao().changeProductData(productModel, null, null);
            fail("Or Product object is null or price and description param is null");
        } catch (IllegalArgumentException e) {
        } catch (SQLException e) {
            fail("Any SQL exeption");
        }

        Double price = 200.00;

        try {
            new ProductDao().changeProductData(productModel, price, null);
            productModel = new ProductDao().findByVendorCode("test");
        } catch (SQLException e) {
            fail("Any SQL exeption changeProductData method with price param");
        }

        assertEquals(productModel.getPrice(), price);

        String description = "changetest";

        try {
            new ProductDao().changeProductData(productModel, null, description);
            productModel = new ProductDao().findByVendorCode("test");
        } catch (SQLException e) {
            fail("Any SQL exeption changeProductData method with description param");
        }

        assertEquals(productModel.getDescription(), description);
    }

    @After
    public void removeProductTest() {
        try {
            new ProductDao().removeProduct("test");
        } catch (SQLException e) {
            fail("Any SQL exeption removeProduct method");
        }
        productModel = null;
    }
}
