package Olya;

import java.sql.*;

public class MainData {
    private static Connection conn;
    private static Statement st;

    public static void main(String[] args) {
        String userName="root";
        String password="Mood161333!";
        String connectionURL="jdbc:mysql://localhost:3306/adresses";

        try{
           // Class.forName("com.mysql.jdbc.Driver");
            try  (Connection conn=DriverManager.getConnection(connectionURL,userName,password)) {
            System.out.println("Connection to DB successful");

            String query = "SELECT first_name FROM people";
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String firstName = rs.getString(1);
                System.out.println(firstName);
            }
        }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }




    }
}
