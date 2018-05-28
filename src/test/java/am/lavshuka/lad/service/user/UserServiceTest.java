package am.lavshuka.lad.service.user;

import am.lavshuka.lad.dao.product.ProductDao;
import am.lavshuka.lad.dao.user.UserDao;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.user.UserModel;
import am.lavshuka.lad.service.InitDBunit;
import am.lavshuka.lad.service.user.UserService;
/*import org.dbunit.operation.DatabaseOperation;*/
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by David on 5/8/2018.
 */
public class UserServiceTest {

    /*public UserServiceTest(String name) {
        super(name);
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        registerUser();
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        unregisterUser();
        return DatabaseOperation.NONE;
    }


    private void registerUser() {

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

        userModel = new UserDao().findByEmail("test1@test.am");
        try {
            new UserService().registerUser(userModel);
            fail("Can get IAE exception");
        } catch (IllegalArgumentException e) {
        }

        userModel = new UserModel();
        userModel.setLogin("TestUser");
        userModel.setPassHash("TEst");
        userModel.setFirstName("TestFirstName");
        userModel.setLastName("TestLastName");
        userModel.setEmail("test3@test.am");
        userModel.setMoney(new Double(5000.00));
        userModel.setRole(UserModel.Role.ROLE_USER);

        new UserService().registerUser(userModel);
        String login = "TestUser";
        assertThat("TestUser", is(login));
    }

    @Test
    public void testLogin() {
        assertFalse(new UserService().loginUser("faillogin", "tsET"));
        assertFalse(new UserService().loginUser("test", "failpass"));
        assertTrue(new UserService().loginUser("TestUser", "tsET"));
    }

    @Test
    public void testChangeUserData() {
        UserModel userModel = new UserDao().findByLogin("TestUser");

        try {
            new UserService().changeUserData(null, "newpass", "newmail@test.am");
            fail("Can get NPE exception");
        } catch (NullPointerException e) {
            try {
                new UserService().changeUserData(userModel, null, null);
                fail("Can get NPE exception");
            } catch (NullPointerException e1) {
            }
        }

        new UserService().changeUserData(userModel, "newpass", null);
        assertTrue(new UserService().loginUser("TestUser", "ssapwen"));
        new UserService().changeUserData(userModel, null, "newmail@test.am");
        assertNotNull(new UserDao().findByEmail("newmail@test.am"));
    }

    @Test
    public void testAddMoney() {
        UserModel userModel = new UserDao().findByLogin("test");

        try {
            new UserService().addMoney(null, 1555.36);
            fail("Can get NPE exception");
        } catch (NullPointerException e) {
            try {
                new UserService().addMoney(userModel, -1);
                fail("Can get IAE exception");
            } catch (IllegalArgumentException e1) {
            }
        }

        new UserService().addMoney(userModel, 999.83);
        assertTrue(userModel.getMoney() == 2000.00);
    }

    @Test
    public void testBuyProduct() {
        UserModel userModel = new UserDao().findByLogin("TestUser");
        ProductModel productModel = new ProductDao().findByVendorCode("soap-1");

        try {
            new UserService().buyProduct(null, productModel, 10, new Date());
        } catch (NullPointerException e) {
            try {
                new UserService().buyProduct(userModel, null, 10, new Date());
            } catch (NullPointerException e1) {
            }
        }

        try {
            new UserService().buyProduct(userModel, productModel, 15, new Date());
        } catch (IllegalArgumentException e) {
            try {
                new UserService().buyProduct(userModel, productModel, 3, new Date());
            } catch (IllegalArgumentException e1) {
            }
        }

        new UserService().buyProduct(userModel, productModel, 1, new Date());
    }

    private void unregisterUser() {
        UserModel userModel = new UserDao().findByLogin("TestUser");
        new UserService().unRegisterUser(userModel);
    }*/
}