package am.lavshuka.lad.service;

import am.lavshuka.lad.dao.user.UserDao;
import am.lavshuka.lad.model.product.BuySellActionProduct;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.user.UserModel;

import java.util.Date;

/**
 * Created by David on 5/4/2018.
 */

public class UserService {

    private UserModel userModel;

    public void registerUser(UserModel userModel) {
        this.userModel = new UserDao().findByLogin(userModel.getLogin());

        if (this.userModel != null || this.userModel.getEmail().equals(userModel.getEmail())) {
            throw new IllegalArgumentException();
        } else if (userModel.getLogin() == null || userModel.getPassHash() == null ||
                userModel.getFirstName() == null || userModel.getLastName() == null) {
            throw new IllegalArgumentException();
        } else userModel.setPassHash(codingPass(userModel.getPassHash()));

        userModel.setRole(UserModel.Role.ROLE_USER);

        new UserDao().addUser(userModel);
    }

    public boolean loginUser(String login, String pass) {
        this.userModel = new UserDao().findByLogin(login);

        if (userModel == null) {
            return false;
        } else if (!userModel.getPassHash().equals(pass)) {
            return false;
        }

        return true;
    }

    public void changeUserData(UserModel userModel, String pass, String email) {

        if (userModel == null || (pass == null && email == null))
            throw new IllegalArgumentException();

        if (pass != null) {
            pass = codingPass(pass);
            userModel.setPassHash(pass);
        }

        if (email != null)
            userModel.setEmail(email);

        new UserDao().changeUserData(userModel);
    }

    public void addMoney(UserModel userModel, double money) {

       if (userModel == null || money <= 0)
           throw new IllegalArgumentException();
    }

    public void buyProduct(UserModel userModel, ProductModel productModel,
                           BuySellActionProduct product, int count, Date date) {

        if (userModel == null || productModel == null || count <= 0)
            throw new IllegalArgumentException();

        double producteSum = productModel.getPrice() * count;

        if (userModel.getMoney() < producteSum)
            throw new IllegalArgumentException();

        product.setProductModel(productModel);
        product.setProductSellDate(date);
        product.setCount(-count);

        userModel.setMoney(userModel.getMoney() - producteSum);
        new UserDao().BuyProduct(userModel, product);
    }

    public void unRegisterUser(UserModel userModel) {
        new UserDao().removeUser(userModel);
    }

    private String codingPass(String s) {
        StringBuilder builder = new StringBuilder(s);
        builder.reverse();
        String rs = new String(builder);
        return rs;
    }
}
