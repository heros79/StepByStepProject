package am.lavshuka.lad.dao.user;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.user.UserModel;
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

public class UserDaoTest {

    private PreparedStatement statement = null;
    private UserModel userModel = new UserModel();

    @Before
    public void addUserTest() {

        userModel.setLogin("test");
        userModel.setPassHash("test");
        userModel.setFirstName("test");
        userModel.setLastName("test");
        userModel.setEmail("test");
        userModel.setRole(UserModel.Role.ROLE_USER);

        try {
            new UserDao().addUser(userModel);
        } catch (SQLException e) {
            fail("Any SQL Exeption addUser method");
        }
    }

    @Test
    public void findByLoginTest() {
        try {
            userModel = new UserDao().findByLogin("test");
        } catch (SQLException e) {
            fail("Any SQL Exeption findByLogin method");
        }
        assertEquals(userModel.getLastName(), "test");
    }

    @Test
    public void findAllTest() {

        List<UserModel> list = null;
        PreparedStatement statement;
        int a = 0;

        try {
            list = new UserDao().findAll();
            statement = DBconn.getInstance().connection().prepareStatement("SELECT COUNT(*) FROM users");
            ResultSet result = statement.executeQuery();
            result.next();
            a = result.getInt(1);
        } catch (SQLException e) {
            fail("Any SQL Exeption findAll method");
        }
        assertEquals(list.size(), a);
    }

    @Test
    public void changeUserDataTest() {

        try {
            new UserDao().changeUserData(null, "not null", null);
            userModel = new UserDao().findByLogin("test");
            new UserDao().changeUserData(userModel, null, null);
            fail("Or User object is null or both String param null");
        } catch (IllegalArgumentException e) {
        } catch (SQLException e) {
            fail("Any SQL Exeption changeUserData method");
        }

        String password = "testpass";
        try {
            new UserDao().changeUserData(userModel, password, null);
            userModel = new UserDao().findByLogin("test");
        } catch (SQLException e) {
            fail("Any SQL Exeption changeUserData method");
        }

        assertEquals(userModel.getPassHash(), password);

        String email = "testemail";

        try {
            new UserDao().changeUserData(userModel, null, email);
            userModel = new UserDao().findByLogin("test");
        } catch (SQLException e) {
            fail("Any SQL Exeption changeUserData method");
        }

        assertEquals(userModel.getEmail(), email);
    }

    @After
    public void removeUserTest() {

        try {
            new UserDao().removeUser("test");
        } catch (SQLException e) {
            fail("Any SQL Exeption removeUser method");
        }

        statement = null;
        userModel = null;
    }
}