package by.it.shelkovich.jd3_03.dao.jaxbBeans;

import by.it.shelkovich.jd3_03.dao.entities.User;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "users"
})
@XmlRootElement(name="Users")
public class Users {
    @XmlElement(required = true, name="User")
    List<User> users;

    public Users() {
    }

    public Users(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
