package am.lavshuka.lad.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by David on 5/17/2018.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/admin")
    public ModelAndView adminPage() {
        return new ModelAndView("admin");
    }
}
