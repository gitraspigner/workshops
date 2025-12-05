package com.pluralsight.Week9;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * *******Add program description here******
 *
 * @author Ravi Spigner
 */
public class DealershipApplication {
    private static Scanner scanner;
    public static void main(String[] args) throws SQLException {
        //check login info exists
        if (args.length != 2) {
            System.out.println(
                    "Application needs two arguments to run: " +
                            "<username> <password>");
            System.exit(1);
        }
        //A database URL has the following format:
        //jdbc:mysql://[host][:port]/[databaseName]
        //1. Open datasource (DataManager handles opening/closing the connection)
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/dealership");
        dataSource.setUsername(args[0]);
        dataSource.setPassword(args[1]);
        DealershipDataManager dataManager = new DealershipDataManager(dataSource);
        menu(dataManager);
    }
    public static void menu(DealershipDataManager dataManager) throws SQLException {
        displayWelcome();
        String input;
        scanner = new Scanner(System.in);
        while (true) {
            System.out.print("""
                ------------Main Menu------------
                Options:
                1) Display
                2) Add/Remove Vehicle From Inventory
                3) Sell a Vehicle (Make a Sales Contract)
                0) Exit
                Select an option:\s""");
            input = scanner.nextLine().trim();
            if (!isNumber(input)) {
                errorMessageNumber(input, true);
                continue;
            }
            if (input.equals("1")) {
                //Display submenu
                while (true) {
                    System.out.print("""
                        ------------Display Menu------------
                        Options:
                        1) Display All Vehicles
                        2) Display Vehicles: Under Price, By Make/Model, By Year, By Color,
                           Under Odometer/Mileage, By Type
                        0) Exit
                        Select an option:\s""");
                    input = scanner.nextLine().trim();
                    if (!isNumber(input)) {
                        errorMessageNumber(input, true);
                        continue;
                    }
                    if (input.equals("1")) {
                        dataManager.getAllVehicles().forEach(System.out::println);
                    } else if (input.equals("2")) {
                        //Display by filters submenu
                        while (true) {
                            System.out.print("""
                            ------------Filter Display Menu------------
                            Options:
                            1) Display Vehicles Under Price
                            2) Display Vehicles By Make/Model
                            3) Display Vehicles By Year
                            4) Display Vehicles By Color
                            5) Display Vehicles Under Odometer/Mileage
                            6) Display Vehicles By Type
                            0) Exit
                            Select an option:\s""");
                            input = scanner.nextLine().trim();
                            if (!isNumber(input)) {
                                errorMessageNumber(input, true);
                                continue;
                            }
                            //TODO: Add error checking
                            if (input.equals("1")) {
                                System.out.print("Enter maximum price: ");
                                double maxPrice = Double.parseDouble(scanner.nextLine().trim());
                                dataManager.getVehiclesUnderPrice(maxPrice).forEach(System.out::println);
                            } else if (input.equals("2")) {
                                System.out.print("Enter make: ");
                                String make = scanner.nextLine().trim();
                                System.out.print("Enter model: ");
                                String model = scanner.nextLine().trim();
                                dataManager.getVehiclesByMakeModel(make, model).forEach(System.out::println);
                            } else if (input.equals("3")) {
                                System.out.print("Enter year: ");
                                int year = Integer.parseInt(scanner.nextLine().trim());
                                dataManager.getVehiclesByYear(year).forEach(System.out::println);
                            } else if (input.equals("4")) {
                                System.out.print("Enter color: ");
                                String color = scanner.nextLine().trim();
                                dataManager.getVehiclesByColor(color).forEach(System.out::println);
                            } else if (input.equals("5")) {
                                System.out.print("Enter maximum mileage/odometer: ");
                                int maxOdometer = Integer.parseInt(scanner.nextLine().trim());
                                dataManager.getVehiclesUnderOdometer(maxOdometer).forEach(System.out::println);
                            } else if (input.equals("6")) {
                                System.out.print("Enter vehicle type: ");
                                String type = scanner.nextLine().trim();
                                dataManager.getVehiclesByType(type).forEach(System.out::println);
                            } else if (input.equals("0")) {
                                break; //exit Filter Display submenu
                            } else {
                                errorMessage(input, "Is An Invalid Menu Option. Only 1-6 or 0 " +
                                        "is acceptable.");
                            }
                        }
                    } else if (input.equals("0")) {
                        break; //exit Display submenu
                    } else {
                        errorMessage(input, "Is An Invalid Menu Option. Only 1,2, or 0 is acceptable.");
                    }
                }
            } else if (input.equals("2")) {
                //Add/Remove submenu
                while (true) {
                    System.out.print("""
                        ------------Add/Remove Vehicle Menu------------
                        Options:
                        1) Add Vehicle
                        2) Remove Vehicle
                        0) Exit
                        Select an option:\s""");
                    input = scanner.nextLine().trim();
                    if (!isNumber(input)) {
                        errorMessageNumber(input, true);
                        continue;
                    }
                    if (input.equals("1")) {
                        //Add Vehicle
                        //TODO: Add error checking
                        System.out.println("Enter vehicle details:");
                        System.out.print("VIN (integer): ");
                        int vin = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Sold (true/false): ");
                        boolean sold = Boolean.parseBoolean(scanner.nextLine().trim());
                        System.out.print("Vehicle Type: ");
                        String vehicleType = scanner.nextLine().trim();
                        System.out.print("Year: ");
                        int year = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Make: ");
                        String make = scanner.nextLine().trim();
                        System.out.print("Model: ");
                        String model = scanner.nextLine().trim();
                        System.out.print("Color: ");
                        String color = scanner.nextLine().trim();
                        System.out.print("Odometer: ");
                        int odometer = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Price: ");
                        double price = Double.parseDouble(scanner.nextLine().trim());
                        DealershipVehicle vehicle = new DealershipVehicle(
                                vin, sold, year, make, model, vehicleType, color, odometer, price
                        );
                        dataManager.addVehicle(vehicle);
                        System.out.println("Vehicle added successfully!");
                    } else if (input.equals("2")) {
                        //Remove Vehicle
                        System.out.print("Enter VIN of vehicle to remove: ");
                        int vin = Integer.parseInt(scanner.nextLine().trim());
                        boolean removed = dataManager.removeVehicle(vin);
                        if (removed) {
                            System.out.println("Vehicle removed successfully!");
                        } else {
                            System.out.println("No vehicle found with VIN: " + vin);
                        }
                    } else if (input.equals("0")) {
                        break; //exit Add/Remove submenu
                    } else {
                        errorMessage(input, "Is An Invalid Menu Option. Only 1,2, or 0 is acceptable.");
                    }
                }
            } else if (input.equals("3")) {
                //Sales Contract submenu
                //TODO: Add error checking
                System.out.println("Enter sales contract details:");
                System.out.print("Date of sale (YYYY-MM-DD): ");
                String date = scanner.nextLine().trim();
                System.out.print("VIN of vehicle sold: ");
                int vin = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Dealership ID: ");
                int dealershipId = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Customer name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Customer email: ");
                String email = scanner.nextLine().trim();
                System.out.print("Sale price: ");
                int price = Integer.parseInt(scanner.nextLine().trim());
                DealershipSalesContract contract = new DealershipSalesContract(
                        date, vin, dealershipId, name, email, price
                );

                dataManager.addSalesContract(contract);
                System.out.println("Sales contract recorded successfully!");
            } else if (input.equals("0")) {
                displayGoodbye();
                break; //exit Main menu
            } else {
                errorMessage(input, "Is An Invalid Menu Option. Only 1,2, 3, or 0 is acceptable.");
            }
        }
    }
    public static boolean isNumber (String input){
        try {
            Double.parseDouble(input); //will return true for doubles/decimals and ints
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void errorMessageNumber (String input,boolean wantedNumber){
        if (wantedNumber) {
            errorMessage(input, " should be a number, not a word");
        } else {
            errorMessage(input, " should be a word, not a number");
        }
    }
    public static void errorMessage (String input, String errorMessage){
        System.out.println("----------------------------------------");
        System.out.println("ERROR: " + input + " " + errorMessage);
        System.out.println("----------------------------------------");
    }
    public static void displayWelcome () {
        System.out.println("--------------------------------------");
        System.out.println("Welcome To The Vehicle Dealership Database");
        System.out.println("--------------------------------------");
    }
    public static void displayGoodbye () {
        System.out.println("-----------------------------");
        System.out.println("Thank you! See you next time!");
        System.out.println("-----------------------------");
    }
}
