package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.DataBase;

public class Building {
    static public ArrayList<String> GetBuildings() throws SQLException
    {
        ArrayList<String> ret = new ArrayList<String>();
        String query = "SELECT building FROM roomdetails GROUP BY building";
        ResultSet resultSet = DataBase.getStatement().executeQuery(query);
        while (resultSet.next())
        {
            ret.add(resultSet.getString("building"));
        }
        return ret;
    }
}
