package by.it.shelkovich.jd3_03.dao.entities;

import by.it.shelkovich.jd3_03.dao.annotations.Entity;

import javax.xml.bind.annotation.*;

@Entity(tableName="posts")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="post", propOrder = {
        "id",
        "user_id",
        "imageURL",
        "description",
        "likes",
        "lat",
        "lng"

})
public class Post {
    @XmlElement(required = true)
    private Long id;

    @XmlElement(required = true)
    private Long user_id;

    @XmlElement(required = true)
    private String imageURL;

    @XmlElement(required = true)
    private String description;

    @XmlElement(required = true)
    private Integer likes;

    @XmlElement(required = false)
    private String lat;

    @XmlElement(required = false)
    private String lng;

    public Post() {
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

        if (!id.equals(post.id)) return false;
        if (!user_id.equals(post.user_id)) return false;
        if (!imageURL.equals(post.imageURL)) return false;
        if (!description.equals(post.description)) return false;
        if (!likes.equals(post.likes)) return false;
        if (lat != null ? !lat.equals(post.lat) : post.lat != null) return false;
        return lng != null ? lng.equals(post.lng) : post.lng == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user_id.hashCode();
        result = 31 * result + imageURL.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + likes.hashCode();
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
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

    public Post(Long id, Long user_id, String imageURL, String description, Integer likes, String lat, String lng) {

        this.id = id;
        this.user_id = user_id;
        this.imageURL = imageURL;
        this.description = description;
        this.likes = likes;
        this.lat = lat;
        this.lng = lng;
    }
}
