package am.lavshuka.lad.model.user;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class UserModel {

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    private Long id;
    private String login;
    private String passHash;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;

    public UserModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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

