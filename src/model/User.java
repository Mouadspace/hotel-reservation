package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.DataBase;

public class User {
  private int userID;
  private String name;
  private String email;
  private String password;
  private String phoneNo;
  public boolean isLoggedIn=false;

  
  public User(){
  }

  public User(int userID, String name, String email, String phoneNo)
  {
    this.userID = userID;
    this.name = name;
    this.email = email;
    this.phoneNo = phoneNo;
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

  public String getName()
  {
    return name;
  }

  public String getPhoneNo()
  {
    return phoneNo;
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

  public static ArrayList<User> GetAllUsers() throws SQLException
  {
    ArrayList<User> ret = new ArrayList<User>();
    String query = "SELECT ClientID, Name, Email, Phone FROM client";
    ResultSet resultSet = DataBase.getStatement().executeQuery(query);
    while (resultSet.next())
    {
      ret.add(new User(
        resultSet.getInt(1),
        resultSet.getString(2),
        resultSet.getString(3),
        resultSet.getString(4)
      ));
    }
    return ret;
  }

  public void Drop() throws SQLException
  {
        // Drom the user
        String query = "DELETE FROM client WHERE ClientID=" + Integer.toString(this.userID);
        DataBase.getStatement().executeUpdate(query);
  }


}