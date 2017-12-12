package by.it.mustaphin.project.java.bean;

public class Right {

    private int id_right;
    private boolean admin;
    private boolean user;
    private boolean guest;

    public Right(int id_right, boolean admin, boolean user, boolean guest) {
        this.id_right = id_right;
        this.admin = admin;
        this.user = user;
        this.guest = guest;
    }

    public Right() {

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.id_right;
        hash = 73 * hash + (this.admin ? 1 : 0);
        hash = 73 * hash + (this.user ? 1 : 0);
        hash = 73 * hash + (this.guest ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Right other = (Right) obj;
        if (this.id_right != other.id_right) {
            return false;
        }
        if (this.admin != other.admin) {
            return false;
        }
        if (this.user != other.user) {
            return false;
        }
        if (this.guest != other.guest) {
            return false;
        }
        return true;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public boolean isGuest() {
        return guest;
    }

    public void setGuest(boolean guest) {
        this.guest = guest;
    }

    @Override
    public String toString() {
        return "Right{" + "id_right=" + id_right + ", admin=" + admin + ", user=" + user + ", guest=" + guest + '}';
    }

    public int getId_right() {

        return id_right;
    }

    public void setId_right(int id_right) {
        this.id_right = id_right;
    }

}
