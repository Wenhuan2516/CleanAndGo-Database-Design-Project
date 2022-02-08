package CleanandGoobjects;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;


public class Employees {
    private void printMenu() {
        System.out.println("___________________________________________________________\n");
        System.out.println("               Welcome to Clean-and-Go Shop                \n");
        System.out.println("                      Employees                            \n");
        System.out.println("___________________________________________________________\n");
    }
    public void menuDriver() throws ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        boolean answer = false;
        while (answer == false) {
            printMenu();
            int m = 0;
            System.out.println("Please enter employee ID: ");
            int ID = scan.nextInt();

            // //create select statement to get employee information
            // String selectStatement = "SELECT schedule FROM `Clean_And_Go.Employee` where ID_Number = " + ID;
            // try (Statement stmt = con.createStatement()) {
            //     ResultSet rs = stmt.executeQuery(selectStatement);
            
            // System.out.println(rs);
            // }
                //or do this
            Class.forName("com.mysql.cj.jdbc.Driver");
            String DB_URL = "jdbc:mysql://localhost:3306/Clean_And_Go?serverTimezone=UTC&useSSL=TRUE";
            String USER = "student";
            String PASS = "password";
            String QUERY = "SELECT schedule FROM Clean_And_Go.Employee WHERE ID_Number = " + ID;
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);
             ) {              
            
            //Display values
            System.out.println("ID: " + ID);
            System.out.println("Works: " + rs.getString("schedule"));
            
            } catch (SQLException e) {
                e.printStackTrace();
            } 

            System.out.println();
            System.out.println("If done, press \"r\" to exit, otherwise press enter");
            String r = scan.nextLine();
            if(r == "r"){
                m = 1;
            }

            if (m == 1) {
                answer = true;
            }
        }
        scan.close();
    }
}
