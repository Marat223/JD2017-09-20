package by.it.akhmelev.project.java.bean;

public class User {

    private int id;
    private String login;
    private String password;
    private String email;
    private int fk_Roles;

    public User() {
    }

    public User(int id, String login, String password, String email, int fk_Roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.fk_Roles = fk_Roles;
    }

    public User(String login, String password, String email, int fk_Roles) {
        this.id = 0;
        this.login = login;
        this.password = password;
        this.email = email;
        this.fk_Roles = fk_Roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFk_Roles() {
        return fk_Roles;
    }

    public void setFk_Roles(int fk_Roles) {
        this.fk_Roles = fk_Roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (fk_Roles != user.fk_Roles) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + fk_Roles;
        return result;
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fk_Roles=" + fk_Roles +
                '}';
    }
}
