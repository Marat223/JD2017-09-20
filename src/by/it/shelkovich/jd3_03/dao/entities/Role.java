package by.it.shelkovich.jd3_03.dao.entities;

import by.it.shelkovich.jd3_03.dao.annotations.Entity;

import javax.xml.bind.annotation.*;

@Entity(tableName="roles")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="role", propOrder = {
        "id",
        "role"
})
public class Role {
    @XmlElement(required = true)
    private Long id;
    @XmlElement(required = true)
    private String role;

    public Role() {
    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        if (!id.equals(role1.id)) return false;
        return role.equals(role1.role);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
