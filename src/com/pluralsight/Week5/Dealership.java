package com.pluralsight.Week5;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a car dealership including its info (name, address, and phone number) and the
 * collection of vehicles it has in its inventory.
 *
 * @author Ravi Spigner
 */
public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;
    private ArrayList<Contract> transactions;
    public Dealership(String phone, String address, String name) {
        this.phone = phone;
        this.address = address;
        this.name = name;
        inventory = new ArrayList<Vehicle>();
        transactions = new ArrayList<Contract>();
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public List<Vehicle> getAllVehicles() {
        return inventory;
    }
    public List<Contract> getAllContracts() {
        return transactions;
    }
    public String toStringForFileWrite() {
        return name +
                "|" + address +
                "|" + phone;
    }
    public List<Vehicle> getVehiclesPriceRange(double upper, double lower) {
        List<Vehicle> result = new ArrayList<Vehicle>();
        for(Vehicle v: inventory) {
            if (v.getPrice() <= upper && v.getPrice() >= lower) {
                result.add(v);
            }
        }
        return result;
    }
    public List<Vehicle> getVehiclesYearRange(int upper, int lower) {
        List<Vehicle> result = new ArrayList<Vehicle>();
        for(Vehicle v: inventory) {
            if (v.getYear() <= upper && v.getYear() >= lower) {
                result.add(v);
            }
        }
        return result;
    }
    public List<Vehicle> getVehiclesMileageRange(int upper, int lower) {
        List<Vehicle> result = new ArrayList<Vehicle>();
        for(Vehicle v: inventory) {
            if (v.getOdometer() <= upper && v.getOdometer() >= lower) {
                result.add(v);
            }
        }
        return result;
    }
    public List<Vehicle> getVehiclesMakeOrModel(String makeOrModel) {
        List<Vehicle> result = new ArrayList<Vehicle>();
        for(Vehicle v: inventory) {
            if (v.getMake().equalsIgnoreCase(makeOrModel) || v.getModel().equalsIgnoreCase(makeOrModel)) {
                result.add(v);
            }
        }
        return result;
    }
    public List<Vehicle> getVehiclesColor(String color) {
        List<Vehicle> result = new ArrayList<Vehicle>();
        for(Vehicle v: inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                result.add(v);
            }
        }
        return result;
    }
    public List<Vehicle> getVehiclesType(String type) {
        List<Vehicle> result = new ArrayList<Vehicle>();
        for(Vehicle v: inventory) {
            if (v.getVehicleType().equalsIgnoreCase(type)) {
                result.add(v);
            }
        }
        return result;
    }
    public Vehicle getVehicleByVin(int vin) {
        for (Vehicle v : inventory) {
            if (v.getVin() == vin) {
                return v;
            }
        }
        return null; //no vehicle matching vin found
    }
    public boolean addVehicle(Vehicle vehicle) {
        return inventory.add(vehicle);
    }
    public boolean removeVehicle(Vehicle vehicle) {
        for (Vehicle v: inventory) {
            if (v.equals(vehicle)) {
                inventory.remove(vehicle);
                return true;
            }
        }
        return false;
    }
    public boolean addContract(Contract contract) {
        return transactions.add(contract) &&
                inventory.remove(getVehicleByVin(contract.getVehicleSold().getVin()));
    }
    public boolean removeContract(Contract contract) {
        for (Contract c: transactions) {
            if (c.equals(contract)) {
                transactions.remove(contract);
                return true;
            }
        }
        return false;
    }
}
