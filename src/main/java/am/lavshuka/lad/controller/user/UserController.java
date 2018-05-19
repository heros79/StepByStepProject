package am.lavshuka.lad.controller.user;

import am.lavshuka.lad.model.user.UserModel;
import am.lavshuka.lad.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by David on 5/18/2018.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserModel userModel;

    @RequestMapping("/account/{principal.username}")
    public ModelAndView getAccount(@PathVariable("principal.username") String username) {
        ModelAndView modelAndView = new ModelAndView("PersonInfo");
        userModel = userService.getUserByLogin(username);
        modelAndView.addObject("firstName", userModel.getFirstName());
        modelAndView.addObject("lastName", userModel.getLastName());
        modelAndView.addObject("email", userModel.getEmail());
        return modelAndView;
    }

    @RequestMapping("account/{principal.username}/changedata")
    public ModelAndView changeUserData(@PathVariable("principal.username") String username,
                                       @RequestParam(value = "oldPassword") String oldPassword,
                                       @RequestParam(value = "newEmail") String email,
                                       @RequestParam(value = "newPassword") String newPassword,
                                       @RequestParam(value = "confirmNewPassword") String confirmPassword) {
        ModelAndView modelAndView = new ModelAndView("changeUserData");

        userModel = userService.getUserByLogin(username);

        if (!oldPassword.equals(userModel.getPassHash())) {
            return modelAndView;
        } else if (!newPassword.equals(confirmPassword)) {
            return modelAndView;
        }

        userService.changeUserData(userModel, newPassword, email);
        modelAndView = new ModelAndView("redirect:/index");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam(value = "login") String login,
                                 @RequestParam(value = "firstName") String firstName,
                                 @RequestParam(value = "lastName") String lastName,
                                 @RequestParam(value = "email") String email,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "confirmPassword") String confirmPassword) {

        ModelAndView modelAndView;

        if (login.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("") ||
                firstName.equals("") || lastName.equals("")) {
            modelAndView = new ModelAndView("register");
            modelAndView.addObject("massage", "You need write all data");
            return modelAndView;
        } else if (!password.equals(confirmPassword)) {
            modelAndView = new ModelAndView("register");
            modelAndView.addObject("massage", "Input Password and ConfirmPassword again");
            return modelAndView;
        }

        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setLogin(login);
        userModel.setEmail(email);
        userModel.setPassHash(password);

        try {
            userService.registerUser(userModel);
        } catch (IllegalArgumentException e) {
            modelAndView = new ModelAndView("register");
            modelAndView.addObject("massage", "email all login is busy");
            return modelAndView;
        }

        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value = "changeaccount", method = RequestMethod.GET)
    public ModelAndView getChangePage() {
        return new ModelAndView("changeUserData");
    }
}