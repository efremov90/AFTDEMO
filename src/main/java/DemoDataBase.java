package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//добавить драйвер h2 C:\Program Files (x86)\H2\bin файл-jar в classpath
public class DemoDataBase {
    public static final String DRIVER = "org.h2.Driver";
    public static final String DB = "test.mv.db";
    public static final String URL = "jdbc:h2:~/"+DB;
    public static final String USER = "sanya";
    public static final String PASSWORD = "12345";

    public static void main (String[] args) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PERSON");
            while (rs.next()) {
                System.out.println(rs.getString("FIRSTNAME")+" "
                        +rs.getString("LASTNAME")+" "
                        +GenderType.valueOf(rs.getString("GENDER")).getDescription());
            }
            conn.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());

        }
        System.out.println("Hello world");
    }
}
