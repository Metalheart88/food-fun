package domain;

public class Customer {

    private String id;
    private String password;
    private String name;
    private String email;
    private String phone;

    // ADDRESS
    private String country;
    private String province;
    private String streetName;
    private int streetNumber;
    private int suiteNumber;
    private String postalCode;
    private int poBox;
    private String creditCard;

    public Customer(String id, String password, String name, String email, String phone, String country, String province, String streetName,
                    int streetNumber, int suiteNumber, String postalCode, int poBox) {

        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.province = province;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.suiteNumber = suiteNumber;
        this.postalCode = postalCode;
        this.poBox = poBox;
    }

    public Customer(String id, String password, String name, String email, String country, String province, String streetName,
                    int streetNumber, String postalCode) {

        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.country = country;
        this.province = province;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
    }

    public Customer(String id, String email, String phone, String name, String country, String province, int streetNumber, String streetName, String postalCode) {

        this.id = id;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.country = country;
        this.province = province;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
    }

    public Customer(String id, String password, String email, String phone, String name, String country, String province, int streetNumber, String streetName, String postalCode) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.country = country;
        this.province = province;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
    }

    public Customer(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getSuiteNumber() {
        return suiteNumber;
    }

    public void setSuiteNumber(int suiteNumber) {
        this.suiteNumber = suiteNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getPoBox() {
        return poBox;
    }

    public void setPoBox(int poBox) {
        this.poBox = poBox;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
