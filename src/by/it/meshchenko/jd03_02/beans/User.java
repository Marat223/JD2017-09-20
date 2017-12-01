package by.it.meshchenko.jd03_02.beans;

import java.util.Optional;

public class User {

    private int id;
    private String name;
    private String password;
    private Optional<String> phoneNumber;
    private String email;
    private boolean emailConfirmed;
    private Optional<Integer> roleId;

    public User() {}

    public User(int id, String name, String password, Optional<String> phoneNumber, String email, boolean emailConfirmed, Optional<Integer> roleId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Optional<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Optional<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public Optional<Integer> getRoleId() {
        return roleId;
    }

    public void setRoleId(Optional<Integer> roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (emailConfirmed != user.emailConfirmed) return false;
        if (!name.equals(user.name)) return false;
        if (!password.equals(user.password)) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        if (!email.equals(user.email)) return false;
        return roleId != null ? roleId.equals(user.roleId) : user.roleId == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + email.hashCode();
        result = 31 * result + (emailConfirmed ? 1 : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", emailConfirmed=" + emailConfirmed +
                ", roleId=" + roleId +
                "}\n";
    }
}
