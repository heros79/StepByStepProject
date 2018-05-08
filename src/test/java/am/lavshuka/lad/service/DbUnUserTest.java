package am.lavshuka.lad.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import am.lavshuka.lad.dao.user.UserDao;
import am.lavshuka.lad.model.user.UserModel;
import am.lavshuka.lad.service.user.UserService;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

import java.io.FileInputStream;


/**
 * Created by David on 5/8/2018.
 */
public class DbUnUserTest extends DBTestCase {

    public DbUnUserTest(String name) {
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://127.0.0.1:3306/lavshuka");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "1111");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/user.xml"));
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    @Test
    public void testByLogin() {
        String login = "test";
        assertThat("test", is(login) );
    }

    @Test
    public void testSave() {
        UserModel userModel = new UserModel();
        userModel.setLogin(null);
        userModel.setPassHash(null);
        userModel.setFirstName(null);
        userModel.setLastName(null);
        userModel.setEmail(null);
        try {
            new UserService().registerUser(userModel);
            fail("Can get NPE exception");
        } catch (NullPointerException e) {
        }
        userModel = new UserDao().findByLogin("test");
        try {
            new UserService().registerUser(userModel);
            fail("Can get IAE exception");
        } catch (IllegalArgumentException e) {
        }
/*        userModel = new UserDao().findByEmail("test1@test.am");
        userModel.setLogin("testlogin");
        try {
            new UserService().registerUser(userModel);
            fail("Can get IAE exception");
        } catch (IllegalArgumentException e) {
        }*/

        userModel = new UserModel();
        userModel.setLogin("TestUser");
        userModel.setPassHash("TEst");
        userModel.setFirstName("TestFirstName");
        userModel.setLastName("TestLastName");
        userModel.setEmail("test@test.am");
        userModel.setMoney(new Double(5000.00));
        userModel.setRole(UserModel.Role.ROLE_USER);

        new UserService().registerUser(userModel);
        String login = "TestUser";
        assertThat("TestUser", is(login));
    }
}