package am.lavshuka.lad.model.user;



import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "users")
public class UserModel implements Serializable {

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "passhash")
    private String passHash;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "money")
    private Double money;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        if (!login.equals(userModel.login))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 31 * login.hashCode() + email.hashCode();
        return result;
    }
}