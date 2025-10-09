package com.pluralsight.Week3;

/**
 * Represents a product (within an online store). Has information like the SKU, name of product,
 * its price, and the department the item belongs to.
 *
 * @author Ravi Spigner
 */
public class Product {
    private String sku;
    private String productName;
    private double price;
    private String department;

    public Product() {
        this.sku = "";
        this.productName = "";
        this.price = 0.0;
        this.department = "";
    }

    public Product(String sku, String productName, double price, String department) {
        this.sku = sku;
        this.productName = productName;
        this.price = price;
        this.department = department;
    }

    public void display() {
        System.out.println("SKU: " + this.getSku() +
                ", Product Name: " + this.getProductName() +
                ", Price: "+  this.getPrice() +
                ", Department:  " + this.getDepartment() + ".");
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
