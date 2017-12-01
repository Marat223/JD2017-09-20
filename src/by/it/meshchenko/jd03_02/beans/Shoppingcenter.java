package by.it.meshchenko.jd03_02.beans;

import java.util.Optional;

public class Shoppingcenter {

    private int id;
    private String name;
    private int addressId;
    private Optional<Float> lat;
    private Optional<Float> lng;
    private Optional<String> description;

    public Shoppingcenter() {}

    public Shoppingcenter(int id, String name, int addressId, Optional<Float> lat, Optional<Float> lng, Optional<String> description) {
        this.id = id;
        this.name = name;
        this.addressId = addressId;
        this.lat = lat;
        this.lng = lng;
        this.description = description;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Optional<Float> getLat() {
        return lat;
    }

    public void setLat(Optional<Float> lat) {
        this.lat = lat;
    }

    public Optional<Float> getLng() {
        return lng;
    }

    public void setLng(Optional<Float> lng) {
        this.lng = lng;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(Optional<String> description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shoppingcenter that = (Shoppingcenter) o;

        if (id != that.id) return false;
        if (addressId != that.addressId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (lat != null ? !lat.equals(that.lat) : that.lat != null) return false;
        if (lng != null ? !lng.equals(that.lng) : that.lng != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + addressId;
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Shoppingcenter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addressId=" + addressId +
                ", lat=" + lat +
                ", lng=" + lng +
                ", description=" + description +
                "}\n";
    }
}
