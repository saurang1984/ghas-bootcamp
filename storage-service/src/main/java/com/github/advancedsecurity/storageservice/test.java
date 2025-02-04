import java.sql.*;
import java.util.Scanner;

public class SQLInjectionDemo {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/testdb"; // Change database name if needed
        String user = "root";  
        String password = "HelloWorld1234567";  

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String inputUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String inputPassword = scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            String query = "SELECT * FROM users WHERE username = '" + inputUsername + "' AND password = '" + inputPassword + "'";
            System.out.println("Executing Query: " + query); // Debugging output

            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("Login successful! Welcome, " + rs.getString("username"));
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
