package com.pluralsight.Week9;
/**
 * Represents a vehicle that exists in a dealership's inventory for the dealership database/
 * application, which is available for purchase.
 * Includes the vin number of the vehicle and the dealership id (where it is available
 * for purchase).
 *
 * @author Ravi Spigner
 */
public class DealershipInventoryEntry {
    private int dealership_id;
    private int vin;
    public DealershipInventoryEntry(int dealership_id, int vin) {
        this.dealership_id = dealership_id;
        this.vin = vin;
    }
    @Override
    public String toString() {
        return "dealership_id: " + dealership_id +
                ", vin: " + vin;
    }
    public int getDealership_id() {
        return dealership_id;
    }
    public void setDealership_id(int dealership_id) {
        this.dealership_id = dealership_id;
    }
    public int getVin() {
        return vin;
    }
    public void setVin(int vin) {
        this.vin = vin;
    }
}
