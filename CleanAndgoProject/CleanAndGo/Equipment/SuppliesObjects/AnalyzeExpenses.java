package Equipment.SuppliesObjects;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

import Equipment.SuppliesObjects.AnalyzeExpenses;
public class AnalyzeExpenses {
    public void Menu() throws IOException{
        Connection conn = null;
        boolean answer = false;
        
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Clean_And_Go?serverTimezone=UTC&useSSL=TRUE";
            String user, pass;
            user = readEntry("userid : ");
            pass = readEntry("password: ");
            conn = DriverManager.getConnection(url, user, pass);
            do {
                printMenu();
                System.out.print("Type in your option: ");
                System.out.flush();
                String ch = readLine();
                System.out.println();
                switch (ch.charAt(0)) {
                    case 'a': CleaningSuppliesAnnualExpense(conn);
                        break;
                    case 'b': //AddNewService(conn);
                        break;
                    case 'r': answer = true;
                        break;
                    default:
                        System.out.println(" Not a valid option ");
                } //switch
            } while (answer == false);
            

        } catch (ClassNotFoundException e) {
            System.out.println("Could not load the driver");
        }catch (SQLException ex) {
            System.out.println(ex);
        }  finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
    }
    private void printMenu() {
        System.out.println("___________________________________________________________\n");
        System.out.println("               Welcome to Clean-and-Go Shop                \n");
        System.out.println("                    Analyze Expenses                       \n");
        System.out.println("___________________________________________________________\n");
        System.out.println("                (a) Cleaning Supplies Annual Expenses      \n");
        System.out.println("                (b) Annual Expense Report                  \n");
        System.out.println("                (r) Return to Equipment and Supplies Menu  \n");
        System.out.println("___________________________________________________________\n");
    } 

    public static void CleaningSuppliesAnnualExpense(Connection conn) throws SQLException, IOException {
        Statement stmt = conn.createStatement();

        String query = "select sum( Cleaning_supplies.UnitPrice * Transaction.Quantity) as TotalExpense " +
                       "from Cleaning_supplies, Transaction " +
                       "where Cleaning_supplies.name = Transaction.Cleaning_supplies_name and Transaction.transaction_date like '2021%'"
        ;
        ResultSet rset;
        rset = stmt.executeQuery(query);
        
         System.out.print("    The Annual Cleaning Supplies Expense for 2021 is ");
         while ( rset.next()){
            System.out.print(rset.getDouble(1) + " " + "\n");
         }   
         System.out.println("--------------------------------------------------\n");
         stmt.close();
    }


    public static void AnnualExpenseReport(Connection conn) throws SQLException, IOException {
        Statement stmt = conn.createStatement();

        String query = "select sum( Cleaning_supplies.UnitPrice * Transaction.Quantity) as TotalExpense " +
                       "from Cleaning_supplies, Transaction " +
                       "where Cleaning_supplies.name = Transaction.Cleaning_supplies_name and Transaction.transaction_date like '2021%'"
        ;
        ResultSet rset;
        rset = stmt.executeQuery(query);
        
         System.out.print("    The Annual Cleaning Supplies Expense for 2021 is ");
         while ( rset.next()){
            System.out.print(rset.getDouble(1) + " " + "\n");
         }   
         System.out.println("--------------------------------------------------\n");
         stmt.close();
    }



    private String readLine() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr, 1);
        String line = "";

        try {
            line = br.readLine();
        } catch (IOException e) {
            System.out.println("Error in SimpleIO.readLine: " +
                    "IOException was thrown");
            System.exit(1);
        }
        return line;
    }





    static String readEntry(String prompt) {
        try {
            StringBuffer buffer = new StringBuffer();
            System.out.print(prompt);
            System.out.flush();
            int c = System.in.read();
            while(c != '\n' && c != -1) {
                buffer.append((char)c);
                c = System.in.read();
            }
            return buffer.toString().trim();
        } catch (IOException e) {
            return "";
        }
    }

}
