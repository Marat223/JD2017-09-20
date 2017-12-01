package by.it.meshchenko.jd03_02.beans;

import java.sql.Date;
import java.util.Optional;

public class LeaseRoom {

    private int id;
    private Float price;
    private java.sql.Date dateStartLease;
    private java.sql.Date dateStopLease;
    private int roomId;
    private int userId;

    public LeaseRoom() {}

    public LeaseRoom(int id, Float price, Date dateStartLease, Date dateStopLease, int roomId, int userId) {
        this.id = id;
        this.price = price;
        this.dateStartLease = dateStartLease;
        this.dateStopLease = dateStopLease;
        this.roomId = roomId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getDateStartLease() {
        return dateStartLease;
    }

    public void setDateStartLease(Date dateStartLease) {
        this.dateStartLease = dateStartLease;
    }

    public Date getDateStopLease() {
        return dateStopLease;
    }

    public void setDateStopLease(Date dateStopLease) {
        this.dateStopLease = dateStopLease;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeaseRoom leaseRoom = (LeaseRoom) o;

        if (id != leaseRoom.id) return false;
        if (roomId != leaseRoom.roomId) return false;
        if (userId != leaseRoom.userId) return false;
        if (price != null ? !price.equals(leaseRoom.price) : leaseRoom.price != null) return false;
        if (dateStartLease != null ? !dateStartLease.equals(leaseRoom.dateStartLease) : leaseRoom.dateStartLease != null)
            return false;
        return dateStopLease != null ? dateStopLease.equals(leaseRoom.dateStopLease) : leaseRoom.dateStopLease == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (dateStartLease != null ? dateStartLease.hashCode() : 0);
        result = 31 * result + (dateStopLease != null ? dateStopLease.hashCode() : 0);
        result = 31 * result + roomId;
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return "LeaseRoom{" +
                "id=" + id +
                ", price=" + price +
                ", dateStartLease=" + dateStartLease +
                ", dateStopLease=" + dateStopLease +
                ", roomId=" + roomId +
                ", userId=" + userId +
                "}\n";
    }
}
