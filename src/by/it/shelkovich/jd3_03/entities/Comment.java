package by.it.shelkovich.jd3_03.entities;

import by.it.shelkovich.jd3_03.annotations.Entity;

@Entity(tableName="comments")
public class Comment {
    private int id;
    private int user_id;
    private int post_id;
    private String text;

    public Comment() {
    }

    public Comment(int id, int user_id, int post_id, String text) {
        this.id = id;
        this.user_id = user_id;
        this.post_id = post_id;
        this.text = text;
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

        if (id != comment.id) return false;
        if (user_id != comment.user_id) return false;
        if (post_id != comment.post_id) return false;
        return text.equals(comment.text);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user_id;
        result = 31 * result + post_id;
        result = 31 * result + text.hashCode();
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

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
