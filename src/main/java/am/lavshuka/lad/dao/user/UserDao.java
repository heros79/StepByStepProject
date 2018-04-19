package am.lavshuka.lad.dao.user;

import am.lavshuka.lad.dao.DBconn;
import am.lavshuka.lad.model.user.UserModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class UserDao {

    private PreparedStatement statement = null;

    public void addUser(UserModel userModel) throws SQLException {

        statement = DBconn.getInstance().connection().prepareStatement("INSERT INTO users (login, passhash, firstname, lastname, email, role)VALUE (?, ?, ?, ?, ?, ?)");
        statement.setString(1, userModel.getLogin());
        statement.setString(2, userModel.getPassHash());
        statement.setString(3, userModel.getFirstName());
        statement.setString(4, userModel.getLastName());
        statement.setString(5, userModel.getEmail());
        statement.setInt(6, userModel.getRole().ordinal());

        statement.executeUpdate();

    }

    public UserModel findByLogin(String login) throws SQLException {

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM " +
                "users WHERE login = ?");

        statement.setString(1, login);

        ResultSet result = statement.executeQuery();

        result.next();

        UserModel userModel = new UserModel();

        userModel.setId(result.getLong(1));
        userModel.setLogin(result.getString(2));
        userModel.setPassHash(result.getString(3));
        userModel.setFirstName(result.getString(4));
        userModel.setLastName(result.getString(5));
        userModel.setEmail(result.getString(6));

        UserModel.Role role = null;
        switch (result.getInt(7)) {
            case 0: {
                role = UserModel.Role.ROLE_USER;
                break;
            }
            case 1: {
                role = UserModel.Role.ROLE_ADMIN;
                break;
            }
        }

        userModel.setRole(role);

        return userModel;
    }

    public List<UserModel> findAll() throws SQLException {

        List<UserModel> list = new ArrayList<UserModel>();

        statement = DBconn.getInstance().connection().prepareStatement("SELECT * FROM users");

        ResultSet result = statement.executeQuery();

        UserModel userModel;

        while (result.next()) {
            userModel = new UserModel();
            userModel.setId(result.getLong(1));
            userModel.setLogin(result.getString(2));
            userModel.setPassHash(result.getString(3));
            userModel.setFirstName(result.getString(4));
            userModel.setLastName(result.getString(5));
            userModel.setEmail(result.getString(6));

            UserModel.Role role = null;
            switch (result.getInt(7)) {
                case 0: {
                    role = UserModel.Role.ROLE_USER;
                    break;
                }
                case 1: {
                    role = UserModel.Role.ROLE_ADMIN;
                    break;
                }
            }

            userModel.setRole(role);

            list.add(userModel);
        }

        return list;
    }

    public void changeUserData(UserModel userModel, String password, String email) throws SQLException {

        if (userModel == null || (password == null && email == null)) {
            throw new IllegalArgumentException();
        }

        if (password != null) {
            statement = DBconn.getInstance().connection().prepareStatement("UPDATE users SET passhash = ? WHERE login = ?");

            statement.setString(1, password);
            statement.setString(2, userModel.getLogin());

            statement.executeUpdate();
        }

        if (email != null) {
            statement = DBconn.getInstance().connection().prepareStatement("UPDATE users SET email = ? WHERE login = ?");

            statement.setString(1, email);
            statement.setString(2, userModel.getEmail());

            statement.executeUpdate();
        }
    }

    public void removeUser(String login) throws SQLException {

        statement = DBconn.getInstance().connection().prepareStatement("DELETE FROM users " +
                "WHERE login = ?");

        statement.setString(1, login);

        statement.executeUpdate();
    }
}
