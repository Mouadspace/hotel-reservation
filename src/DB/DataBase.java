package DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;



public class DataBase {
  private static Statement statement;
  
  public static Statement getStatement(){
    Connection conn;
    try {
      conn = DBConnection.getConnection();
      statement = conn.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return statement;
  }
  
}
