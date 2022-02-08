import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

import CleanandGoobjects.CustomersAndServices;
import CleanandGoobjects.Employees;
import CleanandGoobjects.EquipmentAndSupplies;
import CleanandGoobjects.Updates;


class CleanAndGo {
    public static void main(String[] args) throws IOException {
        CustomersAndServices customers = new CustomersAndServices();
        Employees employees = new Employees();
        EquipmentAndSupplies equipment = new EquipmentAndSupplies();
        Updates update = new Updates();
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Clean_And_Go?serverTimezone=UTC&useSSL=TRUE";
            String user, pass;
            System.out.println();
            System.out.println("Please enter your username and password");
            System.out.println();
            user = readEntry("userid : ");
            pass = readEntry("password: ");
            conn = DriverManager.getConnection(url, user, pass);

            

            boolean answer = false;
            do {
                printMainMenu();
                System.out.print("Type in your option: ");
                System.out.flush();
                String ch = readLine();
                System.out.println();
                switch (ch.charAt(0)) {
                    case 'a': equipment.menuDriver();
                        break;
                    case 'b': customers.menuDriver();
                        break;
                    case 'c': employees.menuDriver();
                        break;
                    case 'd': update.menuDriver();
                        break;
                    case 'q': answer = true;
                        break;
                    default:
                        System.out.println(" Not a valid option ");
                }
            } while (!answer); 

        } catch (ClassNotFoundException e) {
            System.out.println("Could not load the driver");
        } catch (SQLException ex) {
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





    static String readLine() {
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
    public static void printMainMenu() {
        System.out.println("_____________________________________________________________________________\n");
        System.out.println("                             ____________                                    \n");
        System.out.println("                      Welcome to Clean-and-Go Shop                           \n");
        System.out.println("                             ____________                                    \n");
        System.out.println("_____________________________________________________________________________\n");
        System.out.println("                       (a) Equipment & Supplies                              \n");
        System.out.println("                       (b) Customers & Services                              \n");
        System.out.println("                       (c) Employees                                         \n");
        System.out.println("                       (d) Updates                                           \n");
        System.out.println("                       (q) Quit                                              \n");
        System.out.println("_____________________________________________________________________________\n");
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