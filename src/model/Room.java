package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.DataBase;

public class Room {
    private
        int roomID;
        String roomName;
        String building;
        float price;
        String image;
        int members;
        int bathrooms;
        int bedrooms;
        String description;

    public Room(int roomID, String roomName, String building, float price, String image,int members,int bathrooms, int bedrooms, String description)
    {
        this.roomID = roomID;
        this.roomName = roomName;
        this.building = building;
        this.price = price;
        this.image=image;
        this.members=members;
        this.bathrooms=bathrooms;
        this.bedrooms=bedrooms;
        this.description=description;
    }
    
    public int getRoomID() {
        return this.roomID;
    }
    public String getRoomName() {
        return this.roomName;
    }
    public String getBuilding() {
        return this.building;
    }
    public float getPrice() {
        return this.price;
    }
    public String getImage(){
        return this.image;
    }
    public int getMembers(){
        return this.members;
    }
    public int getBathrooms(){
        return this.bathrooms;
    }
    public int getBedrooms(){
        return this.bedrooms;
    }
    public String getDescription(){
        return this.description;
    }

    static public ArrayList<Room> GetRooms() throws SQLException
    {
        ArrayList<Room> ret = new ArrayList<Room>();
        String query = "SELECT R.RoomID, R.roomType, RD.Building ,R.Price, R.imagePath, RD.Max_Members, RD.Bathroom, RD.Bedroom, RD.Description FROM room as R INNER JOIN roomdetails as RD ON R.RoomID = RD.RoomID";
        ResultSet resultSet = DataBase.getStatement().executeQuery(query);
        while (resultSet.next())
        {
            ret.add(
                new Room(
                    resultSet.getInt(1), 
                    resultSet.getString(2), 
                    resultSet.getString(3), 
                    resultSet.getFloat(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getInt(7),
                    resultSet.getInt(8),
                    resultSet.getString(9)
                )
            );
        }
        return ret;
    }
}
