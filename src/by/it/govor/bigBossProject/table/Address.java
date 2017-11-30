package by.it.govor.bigBossProject.table;


public class Address {
    private int id;
    private String country;
    private String city;
    private int user_ID;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", user_ID=" + user_ID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address addres = (Address) o;

        if (id != addres.id) return false;
        if (user_ID != addres.user_ID) return false;
        if (country != null ? !country.equals(addres.country) : addres.country != null) return false;
        return city != null ? city.equals(addres.city) : addres.city == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + user_ID;
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public Address(String country, String city, int user_ID) {
        id=0;
        this.country = country;
        this.city = city;
        this.user_ID = user_ID;
    }

    public Address(int id, String country, String city, int user_ID) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.user_ID = user_ID;
    }
}
