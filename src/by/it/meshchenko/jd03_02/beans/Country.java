package by.it.meshchenko.jd03_02.beans;

public class Country {
    private int id;
    private String name;

    public Country() {}

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (this.id != country.id) return false;
        return this.name != null ? this.name.equals(country.name) : country.name == null;
    }

    @Override
    public int hashCode() {
        int result = this.id;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                "}\n";
    }
}
