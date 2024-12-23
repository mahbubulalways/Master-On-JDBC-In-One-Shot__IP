import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// POSTGRESQL DATABASE CONNECTIVITY
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello, World!");
         try {
             String str ="SELECT * FROM person WHERE name = 'Shahin'";
             String url="jdbc:postgresql://localhost:5432/person_db";
             String username="postgres";
             String password="mahbubul";

             Connection conn = DriverManager.getConnection(url, username, password);
             Statement stnt = conn.createStatement();
             ResultSet rs =stnt.executeQuery(str);
             while(rs.next()){
                 System.out.println(rs.getString("name"));
             }
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
    }

}



