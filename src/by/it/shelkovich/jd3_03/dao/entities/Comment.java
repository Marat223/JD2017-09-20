package by.it.shelkovich.jd3_03.dao.entities;

import by.it.shelkovich.jd3_03.dao.annotations.Entity;

import javax.xml.bind.annotation.*;

@Entity(tableName="comments")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="comment", propOrder = {
        "id",
        "user_id",
        "post_id",
        "text"
})
public class Comment {
    @XmlElement(required = true)
    private Long id;

    @XmlElement(required = true)
    private Long user_id;

    @XmlElement(required = true)
    private Long post_id;

    @XmlElement(required = true)
    private String text;

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", post_id=" + post_id +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (!id.equals(comment.id)) return false;
        if (!user_id.equals(comment.user_id)) return false;
        if (!post_id.equals(comment.post_id)) return false;
        return text.equals(comment.text);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user_id.hashCode();
        result = 31 * result + post_id.hashCode();
        result = 31 * result + text.hashCode();
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

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Comment(Long id, Long user_id, Long post_id, String text) {

        this.id = id;
        this.user_id = user_id;
        this.post_id = post_id;
        this.text = text;
    }
}
