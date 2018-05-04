package am.lavshuka.lad.service;

import am.lavshuka.lad.dao.user.UserDao;
import am.lavshuka.lad.model.user.UserModel;

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
                userModel.getFirstName() == null || userModel.getLastName() == null){
            throw new IllegalArgumentException();
        } else userModel.setPassHash(codingPass(userModel.getPassHash()));

        userModel.setRole(UserModel.Role.ROLE_USER);

        new UserDao().addUser(userModel);
    }

    public boolean loginUser (String login, String pass) {
        userModel = new UserDao().findByLogin(login);

        if (userModel == null) {
            return false;
        } else if (!userModel.getPassHash().equals(pass)) {
            return false;
        }

        return true;
    }

    public void changeUserData (UserModel userModel, String pass, String email) {

        if (pass != null)
            pass = codingPass(pass);

        new UserDao().changeUserData(userModel, pass, email);
    }

    public void unRegisterUser(UserModel userModel) {
        new UserDao().removeUser(userModel.getLogin());
    }

    private String codingPass(String s) {
        StringBuilder builder = new StringBuilder(s);
        builder.reverse();
        String rs = new String(builder);
        return rs;
    }
}
