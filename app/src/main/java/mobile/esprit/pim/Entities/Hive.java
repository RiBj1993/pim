package mobile.esprit.pim.Entities;


public class Hive {
    private int id_ruche;
    private int id_user;
    private double temperature;
    private double humidity;
    private double weight;
    private double longitude;
    private double latitude;
    private int reference;
    private String ip;

    public Hive(int id_ruche, String ip, int reference, double latitude, double longitude, double weight, int id_user, double temperature, double humidity) {
        this.id_ruche = id_ruche;
        this.ip = ip;
        this.reference = reference;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weight = weight;
        this.id_user = id_user;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "Hive{" +
                "id_ruche=" + id_ruche +
                ", id_user=" + id_user +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", weight=" + weight +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", reference=" + reference +
                ", ip='" + ip + '\'' +
                '}';
    }

    public Hive() {
    }

    public int getId_ruche() {
        return id_ruche;
    }

    public void setId_ruche(int id_ruche) {
        this.id_ruche = id_ruche;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
