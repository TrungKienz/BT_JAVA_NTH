import java.sql.*;

public class StudentManagementSystem_1 {

    private static final String DB_URL = "jdbc:mysql://localhost/university";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            createTable(connection);
            
            addStudent(connection, "Nguyen Thi Hanh", "2001-02-08", "IT");

            updateStudent(connection, 1, "Bui Trung Kien", "2001-12-23", "Tester");

            removeStudent(connection, 2);

            searchStudent(connection, "Nguyen Thi Hanh");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), birthday DATE, department VARCHAR(50))";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        }
    }

    private static void addStudent(Connection connection, String name, String birthday, String department) throws SQLException {
        String insertSQL = "INSERT INTO students (name, birthday, department) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, birthday);
            preparedStatement.setString(3, department);
            preparedStatement.executeUpdate();
            System.out.println("Student added successfully.");
        }
    }

    private static void updateStudent(Connection connection, int id, String name, String birthday, String department) throws SQLException {
        String updateSQL = "UPDATE students SET name = ?, birthday = ?, department = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, birthday);
            preparedStatement.setString(3, department);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            System.out.println("Student updated successfully.");
        }
    }

    private static void removeStudent(Connection connection, int id) throws SQLException {
        String deleteSQL = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Student removed successfully.");
        }
    }

    private static void searchStudent(Connection connection, String name) throws SQLException {
        String searchSQL = "SELECT * FROM students WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchSQL)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String studentName = resultSet.getString("name");
                String birthday = resultSet.getString("birthday");
                String department = resultSet.getString("department");
                System.out.println("ID: " + id + ", Name: " + studentName + ", Birthday: " + birthday + ", Department: " + department);
            }
        }
    }
}