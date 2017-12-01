package by.it.meshchenko.jd03_02.beans;

public class Address {

    private int id;
    private int streetId;
    private String buildingNumberStr;

    public Address() {}

    public Address(int id, int streetId, String buildingNumberStr) {
        this.id = id;
        this.streetId = streetId;
        this.buildingNumberStr = buildingNumberStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStreetId() {
        return streetId;
    }

    public void setStreetId(int streetId) {
        this.streetId = streetId;
    }

    public String getBuildingNumberStr() {
        return buildingNumberStr;
    }

    public void setBuildingNumberStr(String buildingNumberStr) {
        this.buildingNumberStr = buildingNumberStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (id != address.id) return false;
        if (streetId != address.streetId) return false;
        return buildingNumberStr.equals(address.buildingNumberStr);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + streetId;
        result = 31 * result + buildingNumberStr.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", streetId=" + streetId +
                ", buildingNumberStr='" + buildingNumberStr + '\'' +
                "}\n";
    }
}
