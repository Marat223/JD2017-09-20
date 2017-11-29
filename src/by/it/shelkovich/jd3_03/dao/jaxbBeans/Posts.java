package by.it.shelkovich.jd3_03.dao.jaxbBeans;

import by.it.shelkovich.jd3_03.dao.entities.Post;
import by.it.shelkovich.jd3_03.dao.entities.User;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "posts"
})
@XmlRootElement(name="Posts")
public class Posts {
    @XmlElement(name="Post")
    List<Post> posts;

    public Posts() {
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Posts(List<Post> posts) {
        this.posts = posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
