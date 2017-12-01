package by.it.meshchenko.jd03_02.beans;

public class City {

    private int id;
    private String name;
    private int counrtyId;

    public City() {}

    public City(int id, String name, int counrtyId) {
        this.id = id;
        this.name = name;
        this.counrtyId = counrtyId;
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

    public int getCounrtyId() {
        return counrtyId;
    }

    public void setCounrtyId(int counrtyId) {
        this.counrtyId = counrtyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (id != city.id) return false;
        if (counrtyId != city.counrtyId) return false;
        return name.equals(city.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + counrtyId;
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", counrtyId=" + counrtyId +
                "}\n";
    }
}
