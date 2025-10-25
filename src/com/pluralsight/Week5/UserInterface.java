package com.pluralsight.Week5;
import java.util.List;
import java.util.Scanner;
import static com.pluralsight.Week5.DealershipFileManager.isNumber;
/**
 * *******Add program description here******
 *
 * @author Ravi Spigner
 */
public class UserInterface {
    private Dealership dealership;
    private final static Scanner scanner = new Scanner(System.in);

    public UserInterface() {
        //part of writeup to have a constructor, but I am not using this
        setDealership(DealershipFileManager.getDealership());
    }
    //TODO: reduce line numbers to < 100 characters
    //TODO: add class javadoc comments
    public void beginShoppingExperience() {
        displayWelcome();
        displayMenu();
        displayGoodbye();
    }
    public void displayMenu() {
        System.out.println("----Menu----");
        String input;
        //begin main menu
        label:
        while (true) {
            System.out.println("---Main Menu---");
            System.out.print("""
                        Please enter an option:
                        1 - Find vehicles within a price range
                        2 - Find vehicles by make / model
                        3 - Find vehicles by year range
                        4 - Find vehicles by color
                        5 - Find vehicles by mileage range
                        6 - Find vehicles by type (car, truck, SUV, van)
                        7 - List ALL vehicles
                        8 - Add a vehicle
                        9 - Remove a vehicle
                        99 - Quit
                        """);
            System.out.print("~: ");
            input = scanner.nextLine().trim();
            switch (input) {
                case "1": {
                    //Find vehicles by price range
                    System.out.print("Please enter the upper limit (dollar) amount of the " +
                            "price range search: ");
                    String upperAmountString = scanner.nextLine().trim();
                    if (!DealershipFileManager.isNumber(upperAmountString)) {
                        DealershipFileManager.errorMessage(upperAmountString, true);
                        continue;
                    }
                    double upperAmount = Double.parseDouble(upperAmountString);
                    System.out.print("Please enter the lower limit (dollar) amount of the " +
                            "price range search: ");
                    String lowerAmountString = scanner.nextLine().trim();
                    if (!DealershipFileManager.isNumber(lowerAmountString)) {
                        DealershipFileManager.errorMessage(lowerAmountString, true);
                        continue;
                    }
                    double lowerAmount = Double.parseDouble(lowerAmountString);
                    this.displayVehiclesPriceRange(upperAmount, lowerAmount);
                    break;
                }
                case "2":
                    //Find vehicles by make OR model
                    System.out.println("---Make/Model Search---");
                    System.out.print("Please enter the vehicle Make/Model to search: ");
                    String makeOrModel = scanner.nextLine().trim();
                    if (DealershipFileManager.isNumber(makeOrModel)) {
                        DealershipFileManager.errorMessage(makeOrModel, false);
                        continue;
                    }
                    this.displayVehiclesMakeOrModel(makeOrModel);
                    break;
                case "3": {
                    //Find vehicles by year range
                    System.out.print("Please enter the upper limit (year) of the year range " +
                            "search: ");
                    String upperAmountString = scanner.nextLine().trim();
                    if (!DealershipFileManager.isNumber(upperAmountString)) {
                        DealershipFileManager.errorMessage(upperAmountString, true);
                        continue;
                    }
                    int upperAmount = Integer.parseInt(upperAmountString);
                    System.out.print("Please enter the lower limit (year) of the year range " +
                            "search: ");
                    String lowerAmountString = scanner.nextLine().trim();
                    if (!DealershipFileManager.isNumber(lowerAmountString)) {
                        DealershipFileManager.errorMessage(lowerAmountString, true);
                        continue;
                    }
                    int lowerAmount = Integer.parseInt(lowerAmountString);
                    this.displayVehiclesYearRange(upperAmount, lowerAmount);
                    break;
                }
                case "4":
                    //Find vehicles by color
                    System.out.println("---Color Search---");
                    System.out.print("Please enter the vehicle Color to search: ");
                    String color = scanner.nextLine().trim();
                    if (DealershipFileManager.isNumber(color)) {
                        DealershipFileManager.errorMessage(color, false);
                        continue;
                    }
                    this.displayVehiclesColor(color);
                    break;
                case "5": {
                    //Find vehicles by mileage range
                    System.out.print("Please enter the upper mileage limit of the search: ");
                    String upperAmountString = scanner.nextLine().trim();
                    if (!DealershipFileManager.isNumber(upperAmountString)) {
                        DealershipFileManager.errorMessage(upperAmountString, true);
                        continue;
                    }
                    int upperAmount = Integer.parseInt(upperAmountString);
                    System.out.print("Please enter the lower mileage limit of the search: ");
                    String lowerAmountString = scanner.nextLine().trim();
                    if (!DealershipFileManager.isNumber(lowerAmountString)) {
                        DealershipFileManager.errorMessage(upperAmountString, true);
                        continue;
                    }
                    int lowerAmount = Integer.parseInt(lowerAmountString);
                    this.displayVehiclesMileageRange(upperAmount, lowerAmount);
                    break;
                }
                case "6":
                    //Find vehicles by type
                    System.out.println("---Vehicle Type Search---");
                    System.out.print("Please enter the vehicle Type to search: ");
                    String type = scanner.nextLine().trim();
                    if (DealershipFileManager.isNumber(type)) {
                        DealershipFileManager.errorMessage(type, false);
                        continue;
                    }
                    this.displayVehiclesType(type);
                    break;
                case "7":
                    //List all vehicles
                    this.displayAllVehicles();
                    break;
                case "8":
                    //Add a vehicle
                    if (this.getVehicleInfoAndAddOrRemove(true)) {
                        System.out.println("Vehicle Successfully Added!");
                    } else {
                        System.out.println("Vehicle Adding Failed...");
                    }
                    break;
                case "9":
                    //Remove a vehicle
                    if (this.getVehicleInfoAndAddOrRemove(false)) {
                        System.out.println("Vehicle Successfully Removed!");
                    } else {
                        System.out.println("Vehicle Removing Failed...");
                    }
                    this.getVehicleInfoAndAddOrRemove(false);
                    break;
                case "99":
                    //Quit
                    break label; //goodbye message will be displayed via beginShoppingExperience()
                default:
                    System.out.println("-------------------");
                    System.out.println("ERROR: Invalid Menu Option: " + input);
                    System.out.println("Only a single number (1-9 or 99) entered is " +
                            "acceptable for your menu input");
                    System.out.println("-------------------");
                    break;
            }
        }
    }
    public boolean getVehicleInfoAndAddOrRemove(boolean isAdding) {
        if (isAdding) {
            System.out.println("---Vehicle Add---");
        } else {
            System.out.println("---Vehicle Remove---");
        }
        System.out.print("Please enter the vin number of the vehicle: ");
        String vinString = scanner.nextLine().trim();
        if (!isNumber(vinString)) {
            DealershipFileManager.errorMessage(vinString, true);
            return false;
        }
        int vin = Integer.parseInt(vinString);
        System.out.print("Please enter the year of the vehicle: ");
        String yearString = scanner.nextLine().trim();
        if (!isNumber(yearString)) {
            DealershipFileManager.errorMessage(yearString, true);
            return false;
        }
        int year = Integer.parseInt(yearString);
        System.out.print("Please enter the make of the vehicle: ");
        String make = scanner.nextLine().trim();
        if (isNumber(make)) {
            DealershipFileManager.errorMessage(yearString, false);
            return false;
        }
        System.out.print("Please enter the model of the vehicle: ");
        String model = scanner.nextLine().trim();
        if (isNumber(model)) {
            DealershipFileManager.errorMessage(model, false);
            return false;
        }
        System.out.print("Please enter the type of the vehicle: ");
        String type = scanner.nextLine().trim();
        if (isNumber(type)) {
            DealershipFileManager.errorMessage(type, false);
            return false;
        }
        System.out.print("Please enter the color of the vehicle: ");
        String color = scanner.nextLine().trim();
        if (isNumber(color)) {
            DealershipFileManager.errorMessage(color, false);
            return false;
        }
        System.out.print("Please enter the odometer reading of the vehicle: ");
        String odometerString = scanner.nextLine().trim();
        if (!isNumber(odometerString)) {
            DealershipFileManager.errorMessage(odometerString, true);
            return false;
        }
        int odometer = Integer.parseInt(odometerString);
        System.out.print("Please enter the price (in dollars) of the vehicle: ");
        String priceString = scanner.nextLine().trim();
        if (!isNumber(priceString)) {
            DealershipFileManager.errorMessage(priceString, true);
            return false;
        }
        int price = Integer.parseInt(priceString);
        Vehicle v = new Vehicle(vin, year, make, model, type, color, odometer, price);
        if(isAdding) {
            return dealership.addVehicle(v);
        } else {
            return dealership.removeVehicle(v);
        }
    }
    public void displayVehiclesPriceRange(double upper, double lower) {
        System.out.println("----Vehicles Within Price Range----");
        List<Vehicle> vehicles = this.getDealership().getVehiclesPriceRange(upper, lower);
        for(Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
    public void displayVehiclesYearRange(int upper, int lower) {
        System.out.println("----Vehicles Within Year Range----");
        List<Vehicle> vehicles = this.getDealership().getVehiclesYearRange(upper, lower);
        for(Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
    public void displayVehiclesMileageRange(int upper, int lower) {
        System.out.println("----Vehicles Within Mileage Range----");
        List<Vehicle> vehicles = this.getDealership().getVehiclesMileageRange(upper, lower);
        for(Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
    public void displayVehiclesMakeOrModel(String makeOrModel) {
        System.out.println("----Vehicles Matching Make/Model----");
        List<Vehicle> vehicles = this.getDealership().getVehiclesMakeOrModel(makeOrModel);
        for(Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
    public void displayVehiclesColor(String color) {
        System.out.println("----Vehicles Matching Color----");
        List<Vehicle> vehicles = this.getDealership().getVehiclesColor(color);
        for(Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
    public void displayVehiclesType(String type) {
        System.out.println("----Vehicles Matching Type----");
        List<Vehicle> vehicles = this.getDealership().getVehiclesType(type);
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
    public void displayAllVehicles() {
        System.out.println("----All Vehicles----");
        List<Vehicle> vehicles = this.getDealership().getAllVehicles();
        for(Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
    public static void displayWelcome() {
        System.out.println("----Welcome to Our Car Dealership----");
    }
    public static void displayGoodbye() {
        System.out.println("----Goodbye! We Appreciate Your Business!----");
    }

    public Dealership getDealership() {
        return this.dealership;
    }

    public void setDealership(Dealership dealership) {
        this.dealership = dealership;
    }
}
