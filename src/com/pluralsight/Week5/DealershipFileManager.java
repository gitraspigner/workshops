package com.pluralsight.Week5;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 * Reads vehicles and writes all vehicles from/to an input file for a car dealership program.
 * Input File is formatted as such:
 * Dealership Name|Dealership Address|Dealership Phone Number
 * Vin Number|Year|Make|Model|VehicleType|Color|OdometerReading|Price
 * Vin Number|Year|Make|Model|VehicleType|Color|OdometerReading|Price
 * ....
 *
 * @author Ravi Spigner
 */
public class DealershipFileManager {
    private static final String filePath = "DataFiles/inventory.csv";
    public static boolean isNumber(String input) {
        try {
            Double.parseDouble(input); //will return true for doubles/decimals and ints
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void errorMessage(String input, boolean wantedNumber) {
        System.out.println("-------------------");
        if (wantedNumber) {
            System.out.println("ERROR: " + input + " is not a number");
        } else {
            System.out.println("ERROR: " + input + " is a word, not a number");
        }
        System.out.println("-------------------");

    }
    //read through file (if it exists) and build vehicles list
    public static Dealership getDealership() {
        Dealership dealership = null;
        //read user file only if it exists
        if (Files.exists(Path.of(filePath))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                String vinString;
                String yearString;
                String make;
                String model;
                String vehicleType;
                String color;
                String odometerString;
                String priceString;
                String[] vehicleData;
                int lineNumber = 1;
                //get first line of input, which is the info for the car dealership
                line = reader.readLine();
                String[] dealershipData;
                dealershipData = line.split("\\|");
                //create dealership object
                dealership = new Dealership(dealershipData[2], dealershipData[1],
                        dealershipData[0]);
                //read and create vehicle objects for the remaining file
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    vehicleData = line.split("\\|");
                    if (vehicleData.length != 8) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: Invalid line (format) found: " + line);
                        System.out.println("Must have 8 components separated by '|' character");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line and keep reading, may as well
                    }
                    vinString = vehicleData[0];
                    if (!isNumber(vinString)) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: " + vinString + " is not a valid vin " +
                                "consisting of numbers");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line
                    }
                    int vin = Integer.parseInt(vinString);
                    yearString = vehicleData[1];
                    if (!isNumber(yearString)) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: " + yearString + " is not a valid year " +
                                "consisting of numbers");
                        System.out.println("Proper Format: XXXX");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line
                    }
                    int year = Integer.parseInt(yearString);
                    make = vehicleData[2];
                    if (isNumber(make)) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: " + make + " is a number, not a " +
                                "make/manufacturer (consisting of words)");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line
                    }
                    model = vehicleData[3];
                    if (isNumber(model)) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: " + model + " is a number, not a " +
                                "model (consisting of words)");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line
                    }
                    vehicleType = vehicleData[4];
                    if (isNumber(vehicleType)) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: " + vehicleType + " is a number, not a " +
                                "vehicle type (consisting of words)");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line
                    }
                    color = vehicleData[5];
                    if (isNumber(color)) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: " + color + " is a number, not a " +
                                "color (consisting of words)");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line
                    }
                    odometerString = vehicleData[6];
                    if (!isNumber(odometerString)) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: " + odometerString + " is not number");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line
                    }
                    int odometer = Integer.parseInt(odometerString);
                    priceString = vehicleData[7];
                    if (!isNumber(priceString)) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: " + priceString + " is not a number");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line
                    }
                    double price = Double.parseDouble(priceString);
                    dealership.addVehicle(new Vehicle(vin, year, make, model,
                            vehicleType, color, odometer, price));
                }
            } catch (Exception e) {
                System.out.println("Error reading file (file may be unreadable or restricted).");
            }
        } else {
            System.out.println("Error reading file (file may be unreadable or restricted).");
        }
        return dealership;
    }
    public static void saveDealership(Dealership dealership) {
        //this syntax for the try block
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(filePath))) {
            //write dealership info to top of file
            bufferedWriter.write(UserInterface.getDealership().toStringForFileWrite());
            bufferedWriter.newLine();
            //write vehicles in inventory
            for (Vehicle v : dealership.getAllVehicles()) {
                bufferedWriter.write(v.toStringForFileWrite());
                bufferedWriter.newLine();
            }
            //closes automatically using try block syntax above,
            //no need for manual bufferedWriter.close() here
        } catch (IOException e) {
            System.out.println("-------------------");
            System.out.println("ERROR: Invalid file path " +
                    filePath); //could not write to file, possible invalid filepath
            System.out.println("-------------------");
        }
    }
}
