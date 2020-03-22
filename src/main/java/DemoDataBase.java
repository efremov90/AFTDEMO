package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*1. настройка h2 https://o7planning.org/ru/11895/installing-h2-database-and-using-h2-console
http://www.h2database.com/html/main.html
2. При ошибке подключения h2 database: не удается найти файл javaw
нужно в переменные среды добавить переменную JAVA_HOME
с путем до java, например, C:\Program Files\Java\jdk-13.0.2\bin
3. Также необходимо добавить драйвер h2 C:\Program Files (x86)\H2\bin файл-jar в classpath
4. Теория https://www.examclouds.com/ru/java/java-core-russian/lesson22*/
public class DemoDataBase {
    public static final String DRIVER = "org.h2.Driver";
    public static final String DB = "test";
    public static final String URL = "jdbc:h2:~/"+DB;
    public static final String USER = "sanya";
    public static final String PASSWORD = "12345";

    private static final String CREATE_SCHEMA_DEMO="create schema DEMO;";

    private static final String DROP_SCHEMA_DEMO="drop schema DEMO";

    private static final String CREATE_TABLE_PERSON="create table DEMO.PERSON " +
            "(PERSON_ID NUMBER(5) auto_increment," +
            "FIRSTNAME VARCHAR2(255)," +
            "LASTNAME  VARCHAR2(255)," +
            "GENDER    VARCHAR2(50));" +
            "create unique index DEMO.PERSON_PERSON_ID_UINDEX" +
            "    on DEMO.PERSON (PERSON_ID);" +
            "alter table DEMO.PERSON" +
            "    add constraint PERSON_PK" +
            "    primary key (PERSON_ID);";

    private static final String DROP_TABLE_PERSON="drop table DEMO.PERSON;";

    private static final String INSERT_DEFAULT_PERSON="insert into DEMO.PERSON (FIRSTNAME,LASTNAME,GENDER) " +
            "values ('Александр','Ефремов','MALE');" +
            "insert into DEMO.PERSON (FIRSTNAME,LASTNAME,GENDER) " +
            "values ('Ольга','Ефремова','FEMALE');";

    public static void main (String[] args) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(CREATE_SCHEMA_DEMO);
            stmt.executeUpdate(CREATE_TABLE_PERSON);
            stmt.executeUpdate(INSERT_DEFAULT_PERSON);
            ResultSet rs = stmt.executeQuery("SELECT * FROM DEMO.PERSON");
            while (rs.next()) {
                System.out.println(rs.getString("FIRSTNAME")+" "
                        +rs.getString("LASTNAME")+" "
                        +GenderType.valueOf(rs.getString("GENDER")).getDescription());
            }
            stmt.executeUpdate(DROP_TABLE_PERSON);
            stmt.executeUpdate(DROP_SCHEMA_DEMO);
            conn.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}