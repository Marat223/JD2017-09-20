package by.it.shelkovich.jd3_03.entities;

import by.it.shelkovich.jd3_03.annotations.Entity;

@Entity(tableName="posts")
public class Post {
    private int id;
    private int user_id;
    private String imageURL;
    private String description;
    private int likes;
    private String lat;
    private String lng;

    public Post() {
    }

    public Post(int id, int user_id, String imageURL, String description, int likes, String lat, String lng) {
        this.id = id;
        this.user_id = user_id;
        this.imageURL = imageURL;
        this.description = description;
        this.likes = likes;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", imageURL='" + imageURL + '\'' +
                ", description='" + description + '\'' +
                ", likes=" + likes +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (user_id != post.user_id) return false;
        if (likes != post.likes) return false;
        if (!imageURL.equals(post.imageURL)) return false;
        if (!description.equals(post.description)) return false;
        if (lat != null ? !lat.equals(post.lat) : post.lat != null) return false;
        return lng != null ? lng.equals(post.lng) : post.lng == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user_id;
        result = 31 * result + imageURL.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + likes;
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
