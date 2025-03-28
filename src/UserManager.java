// UserManager.java
import java.sql.*;
import java.util.Scanner;

public class UserManager {
    private static Scanner scanner = new Scanner(System.in);

    public static void addUser() {
        try (Connection conn = DatabaseManager.getConnection()) {
            System.out.println("\n=== Add New User ===");

            System.out.print("Enter user ID: ");
            int userID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter gender (m/f): ");
            String gender = scanner.nextLine();

            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            String sql = "INSERT INTO User(userID, age, gender, name) VALUES(?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userID);
                pstmt.setInt(2, age);
                pstmt.setString(3, gender);
                pstmt.setString(4, name);
                pstmt.executeUpdate();
                System.out.println("User added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    public static void viewUserReadingHabits() {
        try (Connection conn = DatabaseManager.getConnection()) {
            System.out.println("\n=== View User Reading Habits ===");

            System.out.print("Enter user ID: ");
            int userID = scanner.nextInt();
            scanner.nextLine();

            String sql = "SELECT b.title, rh.pagesRead, rh.submissionMoment " +
                    "FROM ReadingHabit rh " +
                    "JOIN Book b ON rh.book = b.bookID " +
                    "WHERE rh.user = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userID);
                ResultSet rs = pstmt.executeQuery();

                System.out.println("\nReading Habits for User ID: " + userID);
                System.out.println("---------------------------------");
                System.out.printf("%-60s %-10s %-20s\n", "Book Title", "Pages", "Submission Date");
                System.out.println("---------------------------------");

                boolean hasData = false;
                while (rs.next()) {
                    hasData = true;
                    String title = rs.getString("title");
                    int pages = rs.getInt("pagesRead");
                    String date = rs.getString("submissionMoment");
                    System.out.printf("%-60s %-10d %-20s\n", title, pages, date);
                }

                if (!hasData) {
                    System.out.println("No reading habits found for this user.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving reading habits: " + e.getMessage());
        }
    }
}