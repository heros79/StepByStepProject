package am.lavshuka.lad.service;

import am.lavshuka.lad.dao.product.ProductDao;
import am.lavshuka.lad.dao.user.UserDao;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.user.UserModel;
import am.lavshuka.lad.service.user.UserService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by David on 5/5/2018.
 */

public class UserModelTest {

    private UserModel testUser;
    private UserModel userModel;
    private UserModel mockUser;

    public UserModelTest() {
        userModel = new UserModel();
        userModel.setLogin("TestUser");
        userModel.setPassHash("TEst");
        userModel.setFirstName("TestFirstName");
        userModel.setLastName("TestLastName");
        userModel.setEmail("test@test.am");
        userModel.setMoney(new Double(5000.00));
        userModel.setRole(UserModel.Role.ROLE_USER);
        mockUser = mock(UserModel.class);
    }

    @Before
    public void addUserTest() {
        testUser = new UserModel();
        testUser.setLogin("test");
        try {
            new UserService().registerUser(testUser);
            fail("Can get IAE exception");
        } catch (IllegalArgumentException e) {
        }
        testUser.setLogin(null);
        testUser.setEmail("test");
        try {
            new UserService().registerUser(testUser);
            fail("Can get IAE exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            new UserService().registerUser(null);
            fail("Can get NPE exceptions");
        } catch (NullPointerException e) {
        }
        new UserService().registerUser(userModel);
    }

    @Test
    public void loginUserTest() {
        assertFalse(new UserService().loginUser("faillogin", "failpass"));
        assertFalse(new UserService().loginUser("test", "failpass"));
        assertTrue(new UserService().loginUser("TestUser", "tsET"));
    }

    @Test
    public void changeUserDataTest() {
        try {
            new UserService().changeUserData(null, "test", "test");
            fail("Can get NPE exception");
        } catch (NullPointerException e) {
        }
        try {
            new UserService().changeUserData(testUser, null, null);
            fail("Can get NPE exception");
        } catch (NullPointerException e) {
        }
        testUser = new UserDao().findByLogin("test");
        new UserService().changeUserData(testUser, "testtest", null);
        assertTrue(new UserService().loginUser("test", "tsettset"));
        new UserService().changeUserData(testUser, null, "new@mail");
        assertNotNull(new UserDao().findByEmail("new@mail"));
    }

    @Test
    public void addMoneyTest() {
        testUser = new UserDao().findByLogin("test");
        try {
            new UserService().addMoney(null,5.0);
            fail("Can get NPE exception");
        } catch (NullPointerException e) {
            try {
                new UserService().addMoney(testUser,-1.9);
                fail("Can get IAE exception");
            } catch (IllegalArgumentException e1) {
            }
        }
        new UserService().addMoney(testUser, 550.44);
        assertEquals(new Double(1551.00), testUser.getMoney());
    }

    @Test
    public void buyProductTest() {
        ProductModel productModel = new ProductDao().findByVendorCode("soap-1");

        try {
            new UserService().buyProduct(null, productModel,10, new Date());
            fail("Can get NPE exception");
        } catch (NullPointerException e) {
            try {
                new UserService().buyProduct(userModel,null, 10, new Date());
                fail("Can get NPE exception");
            } catch (NullPointerException e1) {
                try {
                    new UserService().buyProduct(userModel, productModel, -1, new Date());
                    fail("Can get IAE exception");
                } catch (IllegalArgumentException e2) {
                    try {
                        new UserService().buyProduct(userModel, productModel, 10, new Date());
                        fail("Can get NPE exception");
                    } catch (IllegalArgumentException e3) {
                    }
                }
            }
        }

        new UserService().buyProduct(userModel, productModel, 2, new Date());
        assertEquals(userModel.getMoney(), new Double(3000.00));
    }

    @After
    public void removeUserTest() {
        new UserService().unRegisterUser(userModel);
        userModel = null;
    }
}
