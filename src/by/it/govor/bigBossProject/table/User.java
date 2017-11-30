package by.it.govor.bigBossProject.table;


public class User {  private int id;
    private String name;
    private String nick;
    private String login;
    private String password;
    private int telephone;
    private int role_ID;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getNick() {
        return nick;
    }

    public int getTelephone() {
        return telephone;
    }

    public int getRole_ID() {
        return role_ID;
    }

    public User(int id, String name, String nick, String login, String password, int telephone, int role_ID) {
        this.id = id;
        this.name = name;
        this.nick = nick;
        this.login = login;
        this.password = password;
        this.telephone = telephone;
        this.role_ID = role_ID;
    }

    public User(String login, String password, String name, String nick, int telephone, int role_ID) {
        this.id = 0;
        this.name = name;
        this.nick = nick;
        this.login = login;
        this.password = password;
        this.telephone = telephone;
        this.role_ID = role_ID;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", telephone=" + telephone +
                ", role_ID=" + role_ID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (telephone != user.telephone) return false;
        if (role_ID != user.role_ID) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (nick != null ? !nick.equals(user.nick) : user.nick != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nick != null ? nick.hashCode() : 0);
        result = 31 * result + telephone;
        result = 31 * result + role_ID;
        return result;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setTelephone(int telephone) {
        this.telephone=telephone;
    }

    public void setRole_ID(int role_ID) {
        this.role_ID = role_ID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
