package com.pluralsight.Week2;

import java.util.Scanner;

/**
 * Manages the inventory of books for a Library. Users use a menu to look
 * up available books for checkout, checked out books to check in, or just to check in a book by
 * its book ID.
 *
 * @author Ravi Spigner
 */
public class Library {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Book[] books = new Book[20];

        // Preload books
        books[0] = new Book(1, "123-456789", "Naruto Vol. 1", false, "");
        books[1] = new Book(2, "456-789123", "The Hunger Games", true, "Jerry");
        books[2] = new Book(3, "789-123456", "Raj", false, "");
        books[3] = new Book(4, "321-654987", "Microserfs", true, "Phil");
        books[4] = new Book(5, "654-987321", "Electronics Vol.1", false, "");
        books[5] = new Book(6, "987-321654", "Storm Front", true, "Artemis");

        int choice = 0; //represents the choice of menu (displayMenu) options 1-4
        while (choice != 4) {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1: //display books available for checkout,
                        // and ask if user want to check out each
                    System.out.println("Available books to check out:");
                    for (Book book : books) {
                        if (book != null && !book.isCheckedOut()) {
                            book.display();
                            System.out.print("Do you want to check out this book? (Y = Yes, N = No): ");
                            char checkoutChoice = scanner.nextLine().toUpperCase().charAt(0);
                            if (checkoutChoice == 'Y') {
                                System.out.print("Enter your name: ");
                                String name = scanner.nextLine();
                                book.setIsCheckedOut(true);
                                book.setCheckedOutTo(name);
                                System.out.println("Successfully checked out to " + book.getCheckedOutTo());
                            } else {
                                System.out.println("Invalid menu choice.");
                            }
                        }
                    }
                    break;
                case 2: // display checked out books
                        // and ask if user want to check in each, keep viewing checked out books,
                        // or return to main menu
                    System.out.println("Books that have been checked out:");
                    for (Book book : books) {
                        if (book != null && book.isCheckedOut()) {
                            book.display();
                            System.out.print("Do you want to check this book in? " +
                                    "(C = Check in book, V = Keep viewing checked out books, " +
                                    "X = Exit to main menu): ");
                            char action = scanner.nextLine().toUpperCase().charAt(0);
                            if (action == 'C') {
                                book.setIsCheckedOut(false);

                                System.out.println("Book " + book.getTitle() + " has been " +
                                        "checked in by " + book.getCheckedOutTo() + " successfully!");
                                book.setCheckedOutTo("");
                            } else if (action == 'X') {
                                break;
                            } else {
                                System.out.println("Invalid menu choice.");
                                break;
                            }
                        }
                    }
                    break;
                case 3: //check in book by ID
                    System.out.print("Enter the book ID to check in: ");
                    int checkInId = scanner.nextInt();
                    scanner.nextLine();
                    boolean found = false;
                    for (Book book : books) {
                        if (book != null && book.getId() == checkInId) {
                            found = true;
                            if (book.isCheckedOut()) {
                                book.setIsCheckedOut(false);
                                System.out.println("Book " + book.getTitle() +
                                        " has been checked in by " + book.getCheckedOutTo() +
                                        " successfully!");
                                book.setCheckedOutTo("");
                                continue;
                            } else {
                                System.out.println("That book is already checked in.");
                            }
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using this library!");
                    System.out.println("See you next time!");
                    break;
                default:
                    System.out.println("Invalid menu choice: " + choice);
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }

    public static void displayMenu() {
        System.out.print("""
            What do you want to do?
            1 - Show available books
            2 - Show checked out books
            3 - Check in a book by ID
            4 - Exit
            Enter your command:\s""");
    }
}
