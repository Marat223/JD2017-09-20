package by.it.shelkovich.jd3_03.dao.entities;

import by.it.shelkovich.jd3_03.dao.annotations.Entity;

import javax.xml.bind.annotation.*;

@Entity(tableName="follow_links")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="follow_link", propOrder = {
        "id",
        "user_id",
        "follower_id"
})
public class FollowLink {

    @XmlElement(required = true)
    private Long id;

    @XmlElement(required = true)
    private Long user_id;

    @XmlElement(required = true)
    private Long follower_id;

    public FollowLink() {
    }

    @Override
    public String toString() {
        return "FollowLink{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", follower_id=" + follower_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowLink that = (FollowLink) o;

        if (!id.equals(that.id)) return false;
        if (!user_id.equals(that.user_id)) return false;
        return follower_id.equals(that.follower_id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user_id.hashCode();
        result = 31 * result + follower_id.hashCode();
        return result;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(Long follower_id) {
        this.follower_id = follower_id;
    }

    public FollowLink(Long id, Long user_id, Long follower_id) {

        this.id = id;
        this.user_id = user_id;
        this.follower_id = follower_id;
    }
}
