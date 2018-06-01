package am.lavshuka.lad.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by David on 6/1/2018.
 */

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    private Long id;
    private String login;
    private String passHash;
    private String firstName;
    private String lastName;
    private Double money;
    private String email;
    private Role role;

    public UserDto setDtoId(Long id) {
        this.id = id;
        return this;
    }

    public UserDto setDtoLogin(String login) {
        this.login = login;
        return this;
    }

    public UserDto setDtoPassHash(String passHash) {
        this.passHash = passHash;
        return this;
    }

    public UserDto setDtoFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDto setDtoLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDto setDtoMoney(Double money) {
        this.money = money;
        return this;
    }

    public UserDto setDtoEmail(String email) {
        this.email = email;
        return this;
    }

    public void setDtoRole(Role role) {
        this.role = role;
    }
}
