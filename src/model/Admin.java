package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DataBase;

public class Admin {
    static public boolean checkAdmin(String email) throws SQLException
    {
        String query = "select email from admin where email='"+email+"'";
        ResultSet resultSet = DataBase.getStatement().executeQuery(query);
        return (boolean)resultSet.next();
    }
    static public boolean checkAdmin(String email, String password) throws SQLException
    {
        String query = "select email from admin where email='"+email+"' and password='"+password+"'";
        ResultSet resultSet = DataBase.getStatement().executeQuery(query);
        return (boolean)resultSet.next();
    }
}
