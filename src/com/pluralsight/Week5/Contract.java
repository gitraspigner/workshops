package com.pluralsight.Week5;
/**
 * A contract represents the details for a vehicle purchase/sale. A contract can either be a sale
 * (financed or not) or a lease. Contains customer info, the vehicle sold, the date of purchase, and
 * the total price of the sale.
 *
 * @author Ravi Spigner
 */
public abstract class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private double totalPrice;
    public Contract(String date, String customerName, String customerEmail,
                    Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
        this.totalPrice = getTotalPrice();
    }
    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();
    public abstract String toString();
    public abstract String toStringForFileWrite();
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public Vehicle getVehicleSold() {
        return vehicleSold;
    }
    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
