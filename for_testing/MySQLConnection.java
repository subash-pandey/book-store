import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    public static void main(String[] args) {

        // Database credentials
        String url = "jdbc:mysql://localhost:8080/book_store";
        String user = "root";
        String password = "Aarush123$";

        Connection connection = null;

        try {
            // Load the MySQL JDBC driver
            //Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Successfully connected to the database.");
            } else {
                System.out.println("Failed to connect to the database.");
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        } finally {
            // Close the connection when finished
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing the connection: " + e.getMessage());
            }
        }
    }
}