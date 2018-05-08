package am.lavshuka.lad.dao.user;

import am.lavshuka.lad.dao.product.AbstractMainProduct;
import am.lavshuka.lad.dao.product.BuySellActionProductDao;
import am.lavshuka.lad.model.product.BuySellActionProduct;
import am.lavshuka.lad.model.user.UserModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class UserDao {

    private Session session;
    private Transaction tx;

    public void addUser(UserModel userModel) {
        session = AbstractMainProduct.getSessionFactory().openSession();
        session.save(userModel);
        session.close();
    }

    public UserModel findByLogin(String login) {
        session = AbstractMainProduct.getSessionFactory().openSession();
        Query query = session.createQuery("from UserModel where login = :login");
        query.setParameter("login", login);
        UserModel userModel = (UserModel) query.uniqueResult();
        session.close();
        return userModel;
    }

    public UserModel findByEmail(String email) {
        session = AbstractMainProduct.getSessionFactory().openSession();
        Query query = session.createQuery("from UserModel where email = :email");
        query.setParameter("email", email);
        UserModel userModel = (UserModel) query.uniqueResult();
        session.close();
        return userModel;
    }

    public List<UserModel> findAll() {
        List<UserModel> list;
        session = AbstractMainProduct.getSessionFactory().openSession();
        Query query = session.createQuery("from UserModel");
        list = query.list();
        session.close();
        return list;
    }

    public void changeUserData(UserModel userModel) {
        session = AbstractMainProduct.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.merge(userModel);
        tx.commit();
        session.close();
    }

    public void BuyProduct(UserModel userModel, BuySellActionProduct productModel) {
        session = AbstractMainProduct.getSessionFactory().openSession();
        tx = session.beginTransaction();
        new BuySellActionProductDao().add(productModel);
        changeUserData(userModel);
        session.close();
    }

    public void removeUser(UserModel userModel) {
        session = AbstractMainProduct.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.remove(userModel);
        tx.commit();
        session.close();
    }
}