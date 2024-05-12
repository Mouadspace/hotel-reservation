package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import DB.DataBase;

public class User {
  private int userID;
  private String email;
  private String password;
  public boolean isLoggedIn=false;

  
  public User(){
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public int getUserIDFromDB(String email) throws SQLException{
    int id = 0;
    String query = "select clientid from client where email = '" + email + "';";
    ResultSet resultSet = DataBase.getStatement().executeQuery(query);
    while (resultSet.next()){
      id = resultSet.getInt("clientid");
    }
    return id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setUser(String email,String password) throws SQLException{
    this.email = email;
    this.password = password;
  }

  public void setUser(int id, String email,String password) throws SQLException{
    this.userID = id;
    this.email = email;
    this.password = password;
  }


  public void saveUserToDb(String email,String password) throws SQLException{
    DataBase.getStatement().executeUpdate("insert into client (email,password) values('"+email+"','"+password+"')");
  }

  public boolean checkUser(String email) throws SQLException{
    String query = "select email from client where email='"+email+"'";
    ResultSet resultSet = DataBase.getStatement().executeQuery(query);
    boolean isFound = false;

    while (resultSet.next()) {
        isFound = true;
    }
    return isFound;
  }

  public boolean checkUser(String email,String password) throws SQLException{
    String query = "select email from client where email='"+email+"' and password='"+password+"'";
    ResultSet resultSet = DataBase.getStatement().executeQuery(query);
    boolean isFound = false;

    while (resultSet.next()) {
        isFound = true;
    }
    return isFound;
  }


}