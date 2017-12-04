package by.it.mustaphin.project.java.bean;

public class User {
    private int id_user;
    private String name;
    private String login;
    private String password;
    private int fk_question;

    public User(int id_user, String name, String login, String password, int fk_question) {
        this.id_user = id_user;
        this.name = name;
        this.login = login;
        this.password = password;
        this.fk_question = fk_question;
    }

    public User(String name, String login, String password, int fk_question) {

        this.name = name;
        this.login = login;
        this.password = password;
        this.fk_question = fk_question;
    }

    public User() {

    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getFk_question() {
        return fk_question;
    }

    public void setFk_question(int fk_question) {
        this.fk_question = fk_question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id_user != user.id_user) return false;
        if (fk_question != user.fk_question) return false;
        if (!name.equals(user.name)) return false;
        if (!login.equals(user.login)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = id_user;
        result = 31 * result + name.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + fk_question;
        return result;
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "id_user=" + id_user +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fk_question=" + fk_question +
                '}';
    }
}
