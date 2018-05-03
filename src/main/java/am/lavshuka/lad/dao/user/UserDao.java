package am.lavshuka.lad.dao.user;

import am.lavshuka.lad.dao.product.AbstractMainProduct;
import am.lavshuka.lad.model.user.UserModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class UserDao {

    private Session session;
    private Transaction tx;

    public void addUser(UserModel userModel) throws SQLException {

        session = AbstractMainProduct.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.save(userModel);
        tx.commit();
        session.close();
    }

    public UserModel findByLogin(String login) throws SQLException {

        session = AbstractMainProduct.getSessionFactory().openSession();

        Query query = session.createQuery("from UserModel where login = :login");
        query.setParameter("login", login);

        UserModel userModel = (UserModel) query.uniqueResult();

        session.close();

        return userModel;
    }

    public List<UserModel> findAll() throws SQLException {

        List<UserModel> list = new ArrayList<UserModel>();

        session = AbstractMainProduct.getSessionFactory().openSession();

        Query query = session.createQuery("from UserModel");
        list = query.list();

        session.close();

        return list;
    }

    public void changeUserData(UserModel userModel, String password, String email) throws SQLException {

        if (userModel == null || (password == null && email == null)) {
            throw new IllegalArgumentException();
        }

        session = AbstractMainProduct.getSessionFactory().openSession();
        tx = session.beginTransaction();

        if (password != null) {
            userModel.setPassHash(password);
        }

        if (email != null) {
            userModel.setEmail(email);
        }

        session.update(userModel);
        tx.commit();
        session.close();
    }

    public void removeUser(String login) throws SQLException {

        session = AbstractMainProduct.getSessionFactory().openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("from UserModel where login = :login");
        query.setParameter("login", login);

        UserModel userModel = (UserModel) query.uniqueResult();
        session.delete(userModel);
        tx.commit();
        session.close();
    }
}