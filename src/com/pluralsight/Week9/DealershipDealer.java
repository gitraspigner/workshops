package com.pluralsight.Week9;
/**
 * *******Add program description here******
 *
 * @author Ravi Spigner
 */
public class DealershipDealer {
    private int dealership_id;
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
        return "id: " + dealership_id +
                "name: " + name +
                ", address: " + address +
                ", phone: " + phone;
    }
    public int getDealership_id() {
        return dealership_id;
    }
    public void setDealership_id(int dealership_id) {
        this.dealership_id = dealership_id;
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
