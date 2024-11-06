import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddEmployees {
    // Database connection credentials
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employee";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "jonne123";
    private Connection connection;

    public AddEmployees() {
        try {
            // Load the JDBC driver and establish the database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Database connected successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(int empId, String nameEn, String nameFa, String nameJa, int age, double salary) {
        String sql = "INSERT INTO employees (emp_id, name_en, name_fa, name_ja, age, salary) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the parameters for the SQL query
            statement.setInt(1, empId);
            statement.setString(2, nameEn);
            statement.setString(3, nameFa);
            statement.setString(4, nameJa);
            statement.setInt(5, age);
            statement.setDouble(6, salary);

            // Execute the update and print a success message
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new employee was added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to close the database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
