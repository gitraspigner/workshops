package com.pluralsight.Week3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Simulates an online store of products. Products and their information are taken from a
 * file called 'products.csv'. User/shopper can add or remove products from their cart, and
 * can search for products by the product name, product price, or department.
 *
 *
 * @author Ravi Spigner
 */
public class OnlineStore {
    private static final Scanner scanner = new Scanner(System.in);
    private static HashMap<String, Product> productsAvailable = new HashMap<>();
    private static HashMap<String, Product> customerCart = new HashMap<>();

    public static void main(String[] args) {
        shop();
    }

    public static void shop() {
        readFile();
        displayWelcome();
        mainMenu();
        displayGoodbye();
    }

    public static void readFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("DataFiles/products.csv"));
            String line;
            //skip first line, it is just the format of the file
            bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null) {
                String[] lineData = line.split("\\|");
                productsAvailable.put(lineData[0],new Product(lineData[0], lineData[1],
                        Double.parseDouble(lineData[2]), lineData[3]));
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayWelcome() {
        System.out.println("---Welcome To The Online Store---");
    }

    public static void displayGoodbye() {
        System.out.println("---Thank you for shopping!---");
        System.out.println("---See you next time!---");
    }

    public static void mainMenu() {
        System.out.print("Please enter an option (1-Display Products, 2-Display Cart, 3-Exit): ");
        int input = scanner.nextInt();
        scanner.nextLine();
        while (input != 3) {

            if (input == 1) {
                displayProducts();
                getProductInput();
            } else if (input == 2) {
                displayCart();
                getCartInput();
            } else {
                break;
            }
            System.out.print("Please enter an option (1-Display Products, 2-Display Cart, 3-Exit): ");
            input = scanner.nextInt();
            scanner.nextLine();
        }
    }

    public static void displayProducts() {
        System.out.println("---Available Products---");
        for(Map.Entry<String, Product> product : productsAvailable.entrySet()) {
            product.getValue().display();
        }
    }

    public static void getProductInput() {
        System.out.print("Please enter an option (1-Search, 2-Add Product To Cart, " +
                "3-Back to main menu): ");
        int input = scanner.nextInt();
        scanner.nextLine();
        while(input != 3) {

            if (input == 1) {
                //search for a product (by sku, price, or department)
                searchProducts();
            } else if (input == 2) {
                //add product to cart
                addProductToCart();
            } else {
                break;
            }
            System.out.print("Please enter an option (1-Search, 2-Add Product To Cart, " +
                    "3-Back to main menu): ");
            input = scanner.nextInt();
            scanner.nextLine();
        }
    }

    public static void searchProducts() {
        System.out.print("Please enter the product info to search for (the Product Name, Department," +
                " or Price): ");
        String productInfo = scanner.nextLine();
        System.out.println("---Products found---");
        for(Map.Entry<String, Product> product : productsAvailable.entrySet()) {
            if(productInfo.equalsIgnoreCase(product.getKey()) ||
                    productInfo.equalsIgnoreCase(product.getValue().getDepartment())) {
                product.getValue().display();
            } else {
                try {
                    double inputPrice = Double.parseDouble(productInfo);
                    if (Double.parseDouble(productInfo) == product.getValue().getPrice()) {
                        product.getValue().display();
                    }
                } catch (NumberFormatException e) {
                    //just continue without comparison
                }
            }
        }
    }

    public static void addProductToCart() {
        System.out.print("Please enter the SKU of the product to add to your cart: ");
        String productSKU = scanner.nextLine().toUpperCase();

        //check if product is already in cart
        if (customerCart.containsKey(productSKU)) {
            System.out.println("That product is already in your cart.");
            return;
        }

        Product product = productsAvailable.get(productSKU);

        if (product != null) {
            customerCart.put(productSKU, product);
            productsAvailable.remove(productSKU);
        } else {
            System.out.println("That product doesn't exist.");
        }
    }

    public static void removeProductFromCart() {
        System.out.print("Please enter the SKU of the product to remove from your cart: ");
        String productSKU = scanner.nextLine().toUpperCase();

        //check if the product is in the cart
        if (!customerCart.containsKey(productSKU)) {
            System.out.println("That product is not in your cart.");
            return;
        }

        Product product = customerCart.get(productSKU);
        productsAvailable.put(productSKU, product);
        customerCart.remove(productSKU);
        System.out.println("Product removed from cart.");
    }

    public static void displayCart() {
        System.out.println("---Your Cart---");
        for(Map.Entry<String, Product> product : customerCart.entrySet()) {
            product.getValue().display();
        }
    }

    public static void getCartInput() {
        System.out.print("Please enter an option (1-Check out, 2-Remove Product from Cart, " +
                "3-Back to main menu): ");
        int input = scanner.nextInt();
        scanner.nextLine();
        while (input != 3) {

            if (input == 1) {
                displayCart();
                checkOut();
                break;
            } else if (input == 2) {
                //remove product from cart
                removeProductFromCart();
            } else {
                break;
            }
            System.out.print("Please enter an option (1-Check out, 2-Remove Product from Cart, " +
                    "3-Back to main menu): ");
            input = scanner.nextInt();
            scanner.nextLine();
        }
    }

    public static void checkOut() {
        System.out.println("---Check Out---");
        double total = 0.0;
        for(Map.Entry<String, Product> product : customerCart.entrySet()) {
            total += product.getValue().getPrice();
        }
        System.out.println("Your Total Is: $" + total + ".");
    }
}
