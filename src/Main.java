// Main.java
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize database
        DatabaseManager.initializeDatabase();

        // Main menu loop
        while (true) {
            System.out.println("\n=== Booktracker Application ===");
            System.out.println("1. Home Page (View Statistics)");
            System.out.println("2. User Manager");
            System.out.println("3. Book Manager");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    homePage();
                    break;
                case 2:
                    userManagerMenu();
                    break;
                case 3:
                    bookManagerMenu();
                    break;
                case 4:
                    System.out.println("Exiting application...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void homePage() {
        while (true) {
            System.out.println("\n=== Home Page ===");
            System.out.println("1. View mean age of users");
            System.out.println("2. View total pages read by all users");
            System.out.println("3. View total users who have read more than one book");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    StatisticsManager.displayMeanAge();
                    break;
                case 2:
                    StatisticsManager.displayTotalPagesRead();
                    break;
                case 3:
                    StatisticsManager.displayUsersWithMultipleBooks();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void userManagerMenu() {
        while (true) {
            System.out.println("\n=== User Manager ===");
            System.out.println("1. Add a new user");
            System.out.println("2. View reading habits for a user");
            System.out.println("3. Back to main menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    UserManager.addUser();
                    break;
                case 2:
                    UserManager.viewUserReadingHabits();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void bookManagerMenu() {
        while (true) {
            System.out.println("\n=== Book Manager ===");
            System.out.println("1. Change book title");
            System.out.println("2. Delete reading habit record");
            System.out.println("3. View total readers for a book");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    BookManager.changeBookTitle();
                    break;
                case 2:
                    BookManager.deleteReadingHabit();
                    break;
                case 3:
                    BookManager.displayTotalReadersForBook();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}