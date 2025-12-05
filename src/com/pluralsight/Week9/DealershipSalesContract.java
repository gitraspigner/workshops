package com.pluralsight.Week9;

import com.pluralsight.Week5.Vehicle;

/**
 * *******Add program description here******
 *
 * @author Ravi Spigner
 */
public class DealershipSalesContract {
    private String date; //date of sale
    private int vin; //vin of vehicle sold
    private int dealership_id;
    private String name; //customer name
    private String email; //customer email
    private int price;
    public DealershipSalesContract(String date, int vin, int dealership_id, String name,
                                   String email, int price) {
        this.date = date;
        this.vin = vin;
        this.dealership_id = dealership_id;
        this.name = name;
        this.email = email;
        this.price = price;
    }
    @Override
    public String toString() {
        return "date: " + date +
                ", vin: " + vin +
                ", dealership_id: " + dealership_id +
                ", name: " + name +
                ", email: " + email +
                ", price: " + price;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getVin() {
        return vin;
    }
    public void setVin(int vin) {
        this.vin = vin;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
