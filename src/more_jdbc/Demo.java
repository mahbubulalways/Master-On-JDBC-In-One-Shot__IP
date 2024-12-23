package more_jdbc;

import java.sql.*;
// MYSQL DATABASE CONNECTIVITY
public class Demo {
    private final static String URL = "jdbc:mysql://localhost:3306/jdbc_db";
    private final static String USER = "root";
    private final static String PASSWORD = "Mahbubul123!";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement statement = connection.createStatement();

//          INSERT

//          String insertQuery= "INSERT INTO students(name, age, marks) VALUES('Shahin',24,89)";
            String insertQuery = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %d, %f)", "Atik", 24, 98.2);
            int rowAffected = statement.executeUpdate(insertQuery);
            System.out.println("DATA INSERT: " + rowAffected);

//          UPDATE
            String updateQuery = String.format("UPDATE students set name = '%s' WHERE id = %d", "Sabbir", 5);
            int rowUpdated = statement.executeUpdate(updateQuery);
            System.out.println("ROW UPDATE: "  + rowUpdated);

//          DELETE
            String deleteQuery = String.format("DELETE FROM students WHERE id = %d", 4);
            int rowDeleted = statement.executeUpdate(deleteQuery);
            System.out.println("DELETED ROW: "+rowDeleted);

//          GET
            String query = "select * from students";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
             int id = resultSet.getInt("id");
             String name = resultSet.getString("name");
             int age = resultSet.getInt("age");
             double marks = resultSet.getDouble("marks");
             System.out.println(id + " " + age + " " + name + " " + marks);
           }
            statement.close();





        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
