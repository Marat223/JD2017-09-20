package by.it.shelkovich.jd3_03.entities;

import by.it.shelkovich.jd3_03.annotations.Entity;

@Entity(tableName="roles")
public class Role {
    private int id;
    private String role;

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        if (id != role1.id) return false;
        return role.equals(role1.role);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
