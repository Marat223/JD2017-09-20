package bean;


import java.util.HashMap;
import java.util.Map;

public class Right {
    private int id_user;
    private Map<String, Boolean> couples = new HashMap<>();

    public Right(int id_user, Map<String, Boolean> couples) {
        this.id_user = id_user;
        this.couples.putAll(couples);
    }

    public Right() {

    }

    @Override
    public String toString() {
        return "Right{" +
                "id_user=" + id_user +
                ", couples=" + couples +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Right)) return false;

        Right right = (Right) o;

        if (id_user != right.id_user) return false;
        return couples.equals(right.couples);
    }

    @Override
    public int hashCode() {
        int result = id_user;
        result = 31 * result + couples.hashCode();
        return result;
    }

    public int getId_user() {

        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Map<String, Boolean> getCouples() {
        return couples;
    }

    public void setCouples(Map<String, Boolean> couples) {
        this.couples = couples;
    }
}


