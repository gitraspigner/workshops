package com.pluralsight.Week5;
import java.util.ArrayList;
import java.util.List;
/**
 * *******Add program description here******
 *
 * @author Ravi Spigner
 */
public class Dealership {
    private String name;
    private String address;
    private String phone;
    private static ArrayList<Vehicle> inventory = null;
    public Dealership(String phone, String address, String name) {
        this.phone = phone;
        this.address = address;
        this.name = name;
        inventory = new ArrayList<Vehicle>();
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
}
