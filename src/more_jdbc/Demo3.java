package more_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

// BATCH UPDATE
public class Demo3 {
    private final static String URL = "jdbc:mysql://localhost:3306/jdbc_db";
    private final static String USER = "root";
    private final static String PASSWORD = "Mahbubul123!";
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press 1 to add student");
            System.out.println("Press 2 to see student");
            System.out.println("Press: ");
            int selecct= scanner.nextInt();
            if(selecct == 1){
                while (true){
                    System.out.print("Enter the name of your student: ");
                    String name = scanner.next();
                    System.out.print("Enter the age of your student: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter marks of your student: ");
                    double marks = scanner.nextDouble();
                    System.out.print("Enter more data: Y/N: ");
                    String choice = scanner.next();
                    String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %d, %f)",name, age, marks);
                    statement.addBatch(query);
                    if(choice.toUpperCase().equals("N")){
                        break;
                    }

                }
                int[] result = statement.executeBatch();
                if(result.length > 0){
                    System.out.println("Your student has been successfully added");
                }else {
                    System.out.println("Your student has not been successfully added");
                }
            }

//
            if(selecct == 2){
                String query = "SELECT * FROM students";
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    double marks = resultSet.getDouble("marks");
                    System.out.println(id + " " + age + " " + name + " " + marks);
                }
            }
            connection.close();



        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
