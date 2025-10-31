package com.pluralsight.Week5;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 * Reads vehicles and writes all vehicles & contracts from/to an input file for a car dealership program.
 * Vehicle Input File is formatted as such:
 * Dealership Name|Dealership Address|Dealership Phone Number
 * Vin Number|Year|Make|Model|VehicleType|Color|OdometerReading|Price
 * Vin Number|Year|Make|Model|VehicleType|Color|OdometerReading|Price
 * ....
 * Contract Input File is formatted as such:
 * CONTRACT_TYPE|DATE|CUSTOMER_NAME|CUSTOMER_EMAIL|VIN|YEAR|MAKE|MODEL|VEHICLE_TYPE|COLOR|
 * ODOMETER|VEHICLE_PRICE|[contract-specific-fields]|TOTAL_PRICE|MONTHLY_PAYMENT
 * .....
 * @author Ravi Spigner
 */
public class DealershipFileManager {
    private static final String vehiclesFilePath = "DataFiles/inventory.csv";
    private static final String contractsFilePath = "DataFiles/contracts.csv";
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
    //read through file (if it exists) and build vehicles & contracts list
    public static Dealership getDealership() {
        Dealership dealership = null;
        if (Files.exists(Path.of(vehiclesFilePath))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(vehiclesFilePath))) {
                String line = reader.readLine();
                if (line != null) {
                    String[] dealershipData = line.split("\\|");
                    if (dealershipData.length >= 3) {
                        dealership = new Dealership(dealershipData[2], dealershipData[1], dealershipData[0]);
                    } else {
                        System.out.println("Invalid dealership header line.");
                        return null;
                    }
                }
                int lineNumber = 2;
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    String[] vehicleData = line.split("\\|");
                    if (vehicleData.length < 8) continue;
                    try {
                        int vin = Integer.parseInt(vehicleData[0].trim());
                        int year = Integer.parseInt(vehicleData[1].trim());
                        String make = vehicleData[2].trim();
                        String model = vehicleData[3].trim();
                        String type = vehicleData[4].trim();
                        String color = vehicleData[5].trim();
                        int odometer = Integer.parseInt(vehicleData[6].trim());
                        double price = Double.parseDouble(vehicleData[7].trim());

                        dealership.addVehicle(new Vehicle(vin, year, make, model, type, color, odometer, price));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid vehicle at line " + lineNumber);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error reading vehicles file: " + e.getMessage());
            }
        } else {
            System.out.println("Vehicles file not found.");
            return null;
        }
        if (Files.exists(Path.of(contractsFilePath))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(contractsFilePath))) {
                String line;
                int lineNumber = 1;
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    String[] data = line.split("\\|");
                    if (data.length < 12) continue;
                    String type = data[0].trim();
                    String date = data[1].trim();
                    String customerName = data[2].trim();
                    String customerEmail = data[3].trim();
                    int vin = Integer.parseInt(data[4].trim());
                    int year = Integer.parseInt(data[5].trim());
                    String make = data[6].trim();
                    String model = data[7].trim();
                    String vehicleType = data[8].trim();
                    String color = data[9].trim();
                    int odometer = Integer.parseInt(data[10].trim());
                    double price = Double.parseDouble(data[11].trim());
                    Vehicle vehicle = dealership.getVehicleByVin(vin);
                    if (vehicle == null) {
                        vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                    }
                    Contract contract;
                    if (type.equalsIgnoreCase("SALE")) {
                        boolean isFinanced = data.length > 16 && data[16].trim().equalsIgnoreCase("YES");
                        contract = new SalesContract(date, customerName, customerEmail, vehicle, isFinanced);
                    } else if (type.equalsIgnoreCase("LEASE")) {
                        contract = new LeaseContract(date, customerName, customerEmail, vehicle);
                    } else {
                        continue;
                    }
                    dealership.getAllContracts().add(contract);
                }
            } catch (Exception e) {
                System.out.println("Error reading contracts file: " + e.getMessage());
            }
        }
        return dealership;
    }
    public static void saveDealership(Dealership dealership) {
        //this syntax for the try block
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(vehiclesFilePath))) {
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
                    vehiclesFilePath); //could not write to file, possible invalid filepath
            System.out.println("-------------------");
        }
        //this syntax for the try block
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(contractsFilePath))) {
            //write contracts in dealership
            for (Contract c : dealership.getAllContracts()) {
                Vehicle v = c.getVehicleSold();

                String baseData = (c instanceof SalesContract ? "SALE" : "LEASE") + "|" +
                        c.getDate() + "|" +
                        c.getCustomerName() + "|" +
                        c.getCustomerEmail() + "|" +
                        v.getVin() + "|" +
                        v.getYear() + "|" +
                        v.getMake() + "|" +
                        v.getModel() + "|" +
                        v.getVehicleType() + "|" +
                        v.getColor() + "|" +
                        v.getOdometer() + "|" +
                        v.getPrice();

                String lineToWrite;

                if (c instanceof SalesContract) {
                    SalesContract sc = (SalesContract) c;
                    lineToWrite = baseData + "|" +
                            sc.getProcessingFee() + "|" +
                            sc.getTotalPrice() + "|" +
                            sc.getMonthlyPayment() + "|" +
                            (sc.isFinanced() ? "YES" : "NO");
                } else if (c instanceof LeaseContract) {
                    LeaseContract lc = (LeaseContract) c;
                    lineToWrite = baseData + "|" +
                            lc.getExpectedEndingValue() + "|" +
                            lc.getLeaseFee() + "|" +
                            lc.getTotalPrice() + "|" +
                            lc.getMonthlyPayment();
                } else {
                    System.out.println("-------------------");
                    System.out.println("ERROR: Unknown contract type for vehicle VIN " + v.getVin());
                    System.out.println("-------------------");
                    continue; //skip invalid contract type
                }

                bufferedWriter.write(lineToWrite);
                bufferedWriter.newLine();
            }
            //closes automatically using try block syntax above,
            //no need for manual bufferedWriter.close() here
        } catch (IOException e) {
            System.out.println("-------------------");
            System.out.println("ERROR: Invalid file path " +
                    contractsFilePath); //could not write to file, possible invalid filepath
            System.out.println("-------------------");
        }
    }
}
