package am.lavshuka.lad.dao.user;

import am.lavshuka.lad.model.user.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class UserDao {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void addUser(UserModel userModel) throws SQLException {

        Session session = sessionFactory.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        session.save(userModel);
        tx.commit();
        session.close();
    }

    public UserModel findByLogin(String login) throws SQLException {

        Session session = sessionFactory.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        Query query = session.createQuery("from UserModel where login = ?");
        query.setParameter(0, login);

        UserModel userModel = (UserModel) query.uniqueResult();
        tx.commit();
        session.close();

        return userModel;
    }

    public List<UserModel> findAll() throws SQLException {

        List<UserModel> list = new ArrayList<UserModel>();

        Session session = sessionFactory.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        Query query = session.createQuery("from UserModel");
        list = query.list();
        tx.commit();
        session.close();

        return list;
    }

    public void changeUserData(UserModel userModel, String password, String email) throws SQLException {

        if (userModel == null || (password == null && email == null)) {
            throw new IllegalArgumentException();
        }

        Session session = sessionFactory.openSession();
        Transaction tx;
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

        Session session = sessionFactory.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        Query query = session.createQuery("from UserModel where login = ?");
        query.setParameter(0, login);

        UserModel userModel = (UserModel) query.uniqueResult();
        session.delete(userModel);
        tx.commit();
        session.close();
    }
}
