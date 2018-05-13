package am.lavshuka.lad.service;

import am.lavshuka.lad.service.user.UserDetailServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by David on 5/14/2018.
 */

@Service
public class SecurityService {

    public String findLoggedByUserName() {
        Object userDetalis = SecurityContextHolder.getContext().getAuthentication().getDetails();

        if (userDetalis instanceof UserDetailServiceImpl) {
            return ((UserDetails) userDetalis).getUsername();
        }
        return null;
    }
}
