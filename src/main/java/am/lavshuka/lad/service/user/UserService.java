package am.lavshuka.lad.service.user;

import am.lavshuka.lad.dao.user.UserDao;
import am.lavshuka.lad.model.product.BuySellActionProduct;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductsByCount;
import am.lavshuka.lad.model.user.UserModel;
import am.lavshuka.lad.service.product.ProductByCountService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by David on 5/4/2018.
 */

@Service
public class UserService {

    private UserModel userModel = null;

    public void registerUser(UserModel userModel) {

        if (userModel.getLogin() == null || userModel.getPassHash() == null ||
                userModel.getFirstName() == null || userModel.getLastName() == null ||
                userModel.getEmail() == null) {
            throw new NullPointerException();
        }

        this.userModel = new UserDao().findByLogin(userModel.getLogin());

        if (this.userModel != null && userModel.getLogin().equalsIgnoreCase(this.userModel.getLogin())) {
            throw new IllegalArgumentException();
        }

        this.userModel = new UserDao().findByEmail(userModel.getEmail());

        if (this.userModel != null && userModel.getEmail().equalsIgnoreCase(this.userModel.getEmail())) {
            throw new IllegalArgumentException();
        }

        userModel.setPassHash(codingPass(userModel.getPassHash()));

        userModel.setRole(UserModel.Role.ROLE_USER);

        new UserDao().addUser(userModel);
    }

    public boolean loginUser(String login, String pass) {
        this.userModel = new UserDao().findByLogin(login);

        if (userModel == null) {
            return false;
        }

        if (!userModel.getPassHash().equals(codingPass(pass))) {
            return false;
        }

        return true;
    }

    public UserModel getUserByLogin(String login) {
        return new UserDao().findByLogin(login);
    }

    public void changeUserData(UserModel userModel, String pass, String email) {

        if (userModel == null || (pass == null && email == null))
            throw new NullPointerException();

        if (pass != null) {
            pass = codingPass(pass);
            userModel.setPassHash(pass);
        }

        if (email != null)
            userModel.setEmail(email);

        new UserDao().changeUserData(userModel);
    }

    public void addMoney(UserModel userModel, double money) {

       if (userModel == null) {
           throw new NullPointerException();
       } else if (money <= 0)
           throw new IllegalArgumentException();

       userModel.setMoney(userModel.getMoney() + money);
       new UserDao().changeUserData(userModel);
    }

    public void buyProduct(UserModel userModel, ProductModel productModel,
                           int count, Date date) {

        if (userModel == null || productModel == null){
            throw new NullPointerException();
        } else if (count <= 0) {
            throw new IllegalArgumentException();
        }

        ProductsByCount productsByCount = new ProductByCountService().findProductWithTotalCount(productModel);

        if (count > productsByCount.getCount())
            throw new IllegalArgumentException();

        double producteSum = productModel.getPrice() * count;

        if (userModel.getMoney() < producteSum)
            throw new IllegalArgumentException();

        BuySellActionProduct product = new BuySellActionProduct();
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