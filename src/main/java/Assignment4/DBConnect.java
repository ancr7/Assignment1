package Assignment4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBConnect {

  public static void main(String[] args) {
    Connection connection;
    try {
      Class.forName("com.mysql.jdbc.Driver");
//      String url = "\"jdbc:mysql://localhost/test?\" +\n"
//          + "                                   \"user=admin&password=Password@123\"";
      String url = "jdbc:mysql://localhost:3306/assignment";
      Properties info = new Properties();
      info.put("user", "admin");
      info.put("password", "Password@123");

//      Class.forName("mysql-connector-java-8.0.25");
//      Class.forName("com.mysql.jdbc.Driver").newInstance();
      connection = DriverManager.getConnection(url,info);
//      Connection connection = DriverManager
//          .getConnection("jdbc:mysql://localhost:3306/assignment", "admin", "Password@123");
//      Statement statement = connection.createStatement();
//      ResultSet resultSet = statement.executeQuery("select * from ItemTable");
//      while (resultSet.next()) {
//        System.out.println(resultSet.getString("name"));
//      }
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
