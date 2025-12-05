package com.pluralsight.Week9;
/**
 * *******Add program description here******
 *
 * @author Ravi Spigner
 */
public class DealershipDealer {
    private String name;
    private String address;
    private String phone;
    public DealershipDealer(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "name: " + name +
                ", address: " + address +
                ", phone: " + phone;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
