package by.it.mustaphin.project.java.bean;

public class Role {
    private int id_role;
    private String type;

    public Role(String type) {

        this.type = type;
    }

    public Role(int id_role, String type) {

        this.id_role = id_role;
        this.type = type;
    }

    public Role() {

    }

    @Override
    public String toString() {
        return "Role{" +
                "type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (id_role != role.id_role) return false;
        return type.equals(role.type);
    }

    @Override
    public int hashCode() {
        int result = id_role;
        result = 31 * result + type.hashCode();
        return result;
    }

    public int getId_role() {

        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
