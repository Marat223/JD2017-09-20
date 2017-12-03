package by.it.meshchenko.jd03_03.beans;

public class Room {

    private int id;
    private String name;
    private Float square;
    private int shoppingCenterId;

    public Room() {}

    public Room(int id, String name, Float square, int shoppingCenterId) {
        this.id = id;
        this.name = name;
        this.square = square;
        this.shoppingCenterId = shoppingCenterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSquare() {
        return square;
    }

    public void setSquare(Float square) {
        this.square = square;
    }

    public int getShoppingCenterId() {
        return shoppingCenterId;
    }

    public void setShoppingCenterId(int shoppingCenterId) {
        this.shoppingCenterId = shoppingCenterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (shoppingCenterId != room.shoppingCenterId) return false;
        if (name != null ? !name.equals(room.name) : room.name != null) return false;
        return square != null ? square.equals(room.square) : room.square == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (square != null ? square.hashCode() : 0);
        result = 31 * result + shoppingCenterId;
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", square=" + square +
                ", shoppingCenterId=" + shoppingCenterId +
                "}\n";
    }
}
