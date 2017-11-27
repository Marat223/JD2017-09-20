package by.it.shelkovich.jd3_03.entities;

import by.it.shelkovich.jd3_03.annotations.Entity;

@Entity(tableName="follow_links")
public class FollowLink {
    private int id;
    private int user_id;
    private int follower_id;

    public FollowLink() {
    }

    public FollowLink(int id, int user_id, int follower_id) {
        this.id = id;
        this.user_id = user_id;
        this.follower_id = follower_id;
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

        if (id != that.id) return false;
        if (user_id != that.user_id) return false;
        return follower_id == that.follower_id;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user_id;
        result = 31 * result + follower_id;
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(int follower_id) {
        this.follower_id = follower_id;
    }
}
