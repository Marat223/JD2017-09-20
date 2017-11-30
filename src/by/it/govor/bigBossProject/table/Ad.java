package by.it.govor.bigBossProject.table;


public class Ad {
    private int id;
    private String processor;
    private String video_card;
    private String motherboard;
    private String ram;
    private String power_supply;
    private String HDD;
    private int user_ID;

    public Ad(String processor, String video_card, String motherboard, String ram, String power_supply, String HDD, int user_ID) {
        this.id = 0;
        this.processor = processor;
        this.video_card = video_card;
        this.motherboard = motherboard;
        this.ram = ram;
        this.power_supply = power_supply;
        this.HDD = HDD;
        this.user_ID = user_ID;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", processor='" + processor + '\'' +
                ", video_card='" + video_card + '\'' +
                ", motherboard='" + motherboard + '\'' +
                ", ram='" + ram + '\'' +
                ", power_supply='" + power_supply + '\'' +
                ", HDD='" + HDD + '\'' +
                ", user_ID=" + user_ID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ad ad = (Ad) o;

        if (id != ad.id) return false;
        if (user_ID != ad.user_ID) return false;
        if (processor != null ? !processor.equals(ad.processor) : ad.processor != null) return false;
        if (video_card != null ? !video_card.equals(ad.video_card) : ad.video_card != null) return false;
        if (motherboard != null ? !motherboard.equals(ad.motherboard) : ad.motherboard != null) return false;
        if (ram != null ? !ram.equals(ad.ram) : ad.ram != null) return false;
        if (power_supply != null ? !power_supply.equals(ad.power_supply) : ad.power_supply != null) return false;
        return HDD != null ? HDD.equals(ad.HDD) : ad.HDD == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (processor != null ? processor.hashCode() : 0);
        result = 31 * result + (video_card != null ? video_card.hashCode() : 0);
        result = 31 * result + (motherboard != null ? motherboard.hashCode() : 0);
        result = 31 * result + (ram != null ? ram.hashCode() : 0);
        result = 31 * result + (power_supply != null ? power_supply.hashCode() : 0);
        result = 31 * result + (HDD != null ? HDD.hashCode() : 0);
        result = 31 * result + user_ID;
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setVideo_card(String video_card) {
        this.video_card = video_card;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setPower_supply(String power_supply) {
        this.power_supply = power_supply;
    }

    public void setHDD(String HDD) {
        this.HDD = HDD;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public int getId() {
        return id;
    }

    public String getProcessor() {
        return processor;
    }

    public String getVideo_card() {
        return video_card;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public String getRam() {
        return ram;
    }

    public String getPower_supply() {
        return power_supply;
    }

    public String getHDD() {
        return HDD;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public Ad(int id, String processor, String video_card, String motherboard, String ram, String power_supply, String HDD, int user_ID) {
        this.id = id;
        this.processor = processor;
        this.video_card = video_card;
        this.motherboard = motherboard;
        this.ram = ram;
        this.power_supply = power_supply;
        this.HDD = HDD;
        this.user_ID = user_ID;
    }
}
