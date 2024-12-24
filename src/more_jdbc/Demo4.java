package more_jdbc;

import java.sql.*;

public class Demo4 {
    private final static String URL = "jdbc:mysql://localhost:3306/jdbc_db";
    private final static String USER = "root";
    private final static String PASSWORD = "Mahbubul123!";
    public static void main(String[] args) {
try {
    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
    connection.setAutoCommit(false);
    Statement statement = connection.createStatement();
    String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
    String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

    //     debit Query
    PreparedStatement preparedDebitStatement = connection.prepareStatement(debitQuery);

    preparedDebitStatement.setDouble(1, 500);
    preparedDebitStatement.setInt(2, 1001);
    preparedDebitStatement.executeUpdate();



    //    credit Query
    PreparedStatement preparedCreditStatement = connection.prepareStatement(creditQuery);
    preparedCreditStatement.setDouble(1, 500);
    preparedCreditStatement.setInt(2, 1002);

//    QUERY PERFORM
    int updateCount = preparedDebitStatement.executeUpdate();
    int updateCount2 = preparedCreditStatement.executeUpdate();

//    SATISFY CONDITION
    if (isSufficient(connection, 1002, 500)){
        connection.commit();
        System.out.println("Transaction Successful");
    }else {
        connection.rollback();
        System.out.println("Transaction Failed");
    }
}catch (Exception e) {
    System.out.println(e.getMessage());
}
    }

// METHODS

    public  static  boolean isSufficient(Connection connection, int account_number, double  amount){
       try {
           String query = "SELECT balance FROM accounts WHERE account_number = ?";
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setInt(1, account_number);
           ResultSet resultSet = preparedStatement.executeQuery();
           double balance = resultSet.getDouble("balance");
           if(balance >= amount){
               return true;
           }else {
               return false;
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

}

