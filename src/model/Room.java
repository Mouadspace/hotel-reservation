package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.DataBase;

public class Room {
    private int roomID;
    private String roomName;
    private String building;
    private float price;

    public Room(int roomID, String roomName, String building, float price)
    {
        this.roomID = roomID;
        this.roomName = roomName;
        this.building = building;
        this.price = price;
    }
    
    public int GetRoomID() {return this.roomID;}
    public String GetRoomName() {return this.roomName;}
    public String GetBuilding() {return this.building;}
    public float GetPrice() {return this.price;}

    static public ArrayList<Room> GetRooms() throws SQLException
    {
        ArrayList<Room> ret = new ArrayList<Room>();
        String query = "SELECT room.RoomID, Title, Building, Price FROM room INNER JOIN roomdetails ON room.RoomID = roomdetails.RoomID";
        ResultSet resultSet = DataBase.getStatement().executeQuery(query);
        while (resultSet.next())
        {
            ret.add(
                new Room(
                    resultSet.getInt(0), 
                    resultSet.getString(1), 
                    resultSet.getString(2), 
                    resultSet.getFloat(3)
                )
            );
        }
        return ret;
    }
}
