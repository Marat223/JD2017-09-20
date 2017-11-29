package by.it.shelkovich.jd3_03.dao.entities;

import by.it.shelkovich.jd3_03.dao.annotations.Entity;

import javax.xml.bind.annotation.*;

@Entity(tableName="users")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="user", propOrder = {
        "id",
        "username",
        "passHash",
        "description",
        "role_id",
        "name",
        "surname",
        "email",
        "showPersonalData"

})
public class User {
    @XmlElement(required = true)
    private long id;

    @XmlElement(required = true)
    private String username;

    @XmlElement(required = true)
    private String passHash;

    @XmlElement(required = false)
    private String description;

    @XmlElement(required = true)
    private int role_id;

    @XmlElement(required = false)
    private String name;

    @XmlElement(required = false)
    private String surname;

    @XmlElement(required = true)
    private String email;

    @XmlElement(required = true)
    private boolean showPersonalData;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (role_id != user.role_id) return false;
        if (showPersonalData != user.showPersonalData) return false;
        if (!username.equals(user.username)) return false;
        if (!passHash.equals(user.passHash)) return false;
        if (description != null ? !description.equals(user.description) : user.description != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + username.hashCode();
        result = 31 * result + passHash.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + role_id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + email.hashCode();
        result = 31 * result + (showPersonalData ? 1 : 0);
        return result;
    }

    public User(long id, String username, String passHash, String description, int role_id, String name, String surname, String email, boolean showPersonalData) {
        this.id = id;
        this.username = username;
        this.passHash = passHash;
        this.description = description;
        this.role_id = role_id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.showPersonalData = showPersonalData;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isShowPersonalData() {
        return showPersonalData;
    }

    public void setShowPersonalData(boolean showPersonalData) {
        this.showPersonalData = showPersonalData;
    }
}
