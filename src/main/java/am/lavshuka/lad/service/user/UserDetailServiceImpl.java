package am.lavshuka.lad.service.user;

import am.lavshuka.lad.dao.user.UserDao;
import am.lavshuka.lad.model.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by David on 5/14/2018.
 */

public class UserDetailServiceImpl /*implements UserDetailsService*/ {

/*    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserModel user = userDao.findByLogin(userName);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<UserModel.Role> roles = EnumSet.allOf(UserModel.Role.class);
        for (UserModel.Role role : roles) {
            if (user.getRole().equals(role))
            grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassHash(), grantedAuthorities);
    }*/
}