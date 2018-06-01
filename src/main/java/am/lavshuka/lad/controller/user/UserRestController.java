package am.lavshuka.lad.controller.user;

import am.lavshuka.lad.model.user.UserModel;
import am.lavshuka.lad.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by David on 5/31/2018.
 */

@RestController
@RequestMapping("/register")
public class UserRestController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = {"http://localhost:8080/register"}, maxAge = 3600, methods = {RequestMethod.POST, RequestMethod.OPTIONS},  allowCredentials = "false")
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity registration(@RequestParam(value = "firstName", required = false) String firstName,
                             @RequestParam(value = "lastName", required = false) String lastName,
                             @RequestParam(value = "login", required = false) String login,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "pass", required = false) String pass) {

        HttpHeaders httpHeaders = new HttpHeaders();
       // httpHeaders.setAccessControlAllowOrigin("Access-Control-Allow-Origin");
        UserModel userModel = new UserModel();

        userModel.setLogin(login);
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPassHash(pass);
        userModel.setMoney(new Double(0.0));

        try {
            userService.registerUser(userModel);
        } catch (NullPointerException e) {
            return new ResponseEntity("Any problem in server said", HttpStatus.FORBIDDEN);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("input illegal parametrs", HttpStatus.BAD_REQUEST);
        }


        httpHeaders.setLocation(URI.create("http://localhost:8080/index"));
        return new ResponseEntity(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }
}
