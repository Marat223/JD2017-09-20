package by.it.govor.jd02_09.jaxB;


import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", propOrder = {
        "name",
        "nick",
        "telephone",
        "Address"
})
public class User {
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class) //тип адаптера
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String nick;
    @XmlElement(required = true)
    private String telephone;
    @XmlElement(required = true)
    private Address address = new Address();
    public User() { }

    public User(String name, String nick, String telephone, Address address) {
        this.name = name;
        this.nick = nick;
        this.telephone = telephone;
        this.address = address;
    }



    public String getName() {
        return name;
    }
    public String getNickname() {
        return nick;
    }
    public String getTelephone() {
        return telephone;
    }
    public Address getAddress() {
        return address;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setNickname(String nickname) {
        this.nick = nick;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public String toString() {
        return "\nname: " + name + "\nnick: " + nick
                + "\ntelephone: " + telephone + address.toString();
    }



    @XmlRootElement
    @XmlType(name = " Address ", propOrder = {
            "country",
            "city"
    })
    public static class Address {
        private String country;
        private String city;

        public Address() {}

        public Address(String country, String city) {
            this.country = country;
            this.city = city;
        }

        public String getCountry() {
            return country;
        }
        public String getCity() {
            return city;
        }


        public void setCountry(String country) {
            this.country = country;
        }
        public void setCity(String city) {
            this.city = city;
        }

        public String toString() {
            return "\nAddress:" + "\n\tCountry: " + country
                    + "\n\tCity: " + city + "\n";
        }
    }
}
