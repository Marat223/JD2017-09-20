package by.it.mustaphin.project.java.bean;

import java.util.ArrayList;
import java.util.List;

public class UserHasRoles {
    private int id_role;
    private int id_user;
    private List<Integer> roles = new ArrayList<>();
    private List<Integer> users = new ArrayList<>();

    public UserHasRoles(int id_role, int id_user) {
        this.id_role = id_role;
        this.id_user = id_user;
    }

    public UserHasRoles() {

    }

    public void addId_user(int id_user) {
        users.add(id_user);
    }

    public void addId_role(int id_role) {
        roles.add(id_role);
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserHasRoles{" +
                "id_role=" + id_role +
                ", id_user=" + id_user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserHasRoles)) return false;

        UserHasRoles that = (UserHasRoles) o;

        if (id_role != that.id_role) return false;
        return id_user == that.id_user;
    }

    @Override
    public int hashCode() {
        int result = id_role;
        result = 31 * result + id_user;
        return result;
    }

    public int getId_role() {

        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
