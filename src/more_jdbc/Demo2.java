package more_jdbc;

import java.sql.*;

public class Demo2 {
    private final static String URL = "jdbc:mysql://localhost:3306/jdbc_db";
    private final static String USER = "root";
    private final static String PASSWORD = "Mahbubul123!";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement statement = connection.createStatement();

//          INSERT

          String queryString= "INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";
          PreparedStatement preparedStatement = connection.prepareStatement(queryString);
          preparedStatement.setString(1,"Shahin");
          preparedStatement.setInt(2,20);
          preparedStatement.setDouble(3,99.9);
            int row = preparedStatement.executeUpdate();
            System.out.println("NEW USER: "+row);

//         UPDATE

            String updateQuery = "UPDATE students SET marks = ? WHERE id = ?";
            PreparedStatement updateStatement= connection.prepareStatement(updateQuery);
           updateStatement.setDouble(1, 66.6);
           updateStatement.setInt(2, 5);
           int update =updateStatement.executeUpdate();
           System.out.println(update);
//

//         DELETE
            String deleteQuery = "DELETE FROM students WHERE id = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, 14);
            int delete =deleteStatement.executeUpdate();
            System.out.println(delete);

//            GET MARKS SPECIFIC STUDENT
            String getMarks = "SELECT marks FROM students WHERE id = ?";
            PreparedStatement getMarksStatement = connection.prepareStatement(getMarks);
            getMarksStatement.setInt(1, 13);
            ResultSet particularMark =  getMarksStatement.executeQuery();
            while (particularMark.next()) {
//                int id = particularMark.getInt("id");
//                String name = particularMark.getString("name");
//                int age = particularMark.getInt("age");
                double marks = particularMark.getDouble("marks");
//                System.out.println(id + " " + age + " " + name + " " + marks);
                System.out.println(marks);
            }



//
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
