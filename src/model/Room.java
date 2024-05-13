package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
        String title;

    public Room(int roomID, String title, String roomName, String building, float price, String image,int members,int bathrooms, int bedrooms, String description)
    {
        this.roomID = roomID;
        this.title = title;
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
    public String getTitle()
    {
        return this.title;
    }

    
    public boolean IsRoomCurrentlyReserved() throws SQLException
    {
        LocalDate now = LocalDate.now();
        String query = "SELECT ReservationID FROM reservation WHERE RoomID=" + this.roomID + " AND CheckInDate<'" + now.toString() + "' AND CheckOutDate>'" + now.toString() + "'";
        ResultSet resultSet = DataBase.getStatement().executeQuery(query);
        if (resultSet.next()) return true;
        return false;
    }

    public void Drop() throws SQLException
    {
        // Drom the room and all other references to it
        String reservationQuery = "DELETE FROM reservation WHERE RoomID=" + Integer.toString(this.roomID);
        String roomDetailsQuery = "DELETE FROM roomdetails WHERE RoomID=" + Integer.toString(this.roomID);
        String roomQuery = "DELETE FROM room WHERE RoomID=" + Integer.toString(this.roomID);
        DataBase.getStatement().executeUpdate(reservationQuery);
        DataBase.getStatement().executeUpdate(roomDetailsQuery);
        DataBase.getStatement().executeUpdate(roomQuery);
    }

    static public ArrayList<Room> GetRooms() throws SQLException
    {
        ArrayList<Room> ret = new ArrayList<Room>();
        String query = "SELECT R.RoomID, R.roomType, RD.Building ,R.Price, R.imagePath, RD.Max_Members, RD.Bathroom, RD.Bedroom, RD.Description, R.Title FROM room as R INNER JOIN roomdetails as RD ON R.RoomID = RD.RoomID";
        ResultSet resultSet = DataBase.getStatement().executeQuery(query);
        while (resultSet.next())
        {
            ret.add(
                new Room(
                    resultSet.getInt(1), 
                    resultSet.getString(10),
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

    static public ArrayList<Room> GetRoomsByType(String roomType) throws SQLException {
        ArrayList<Room> ret = new ArrayList<Room>();
        String query = "SELECT R.RoomID, R.roomType, RD.Building ,R.Price, R.imagePath, RD.Max_Members, RD.Bathroom, RD.Bedroom, RD.Description, R.Title FROM room as R INNER JOIN roomdetails as RD ON R.RoomID = RD.RoomID WHERE R.roomType='" + roomType + "'";
        ResultSet resultSet = DataBase.getStatement().executeQuery(query);
        while (resultSet.next()) {
            ret.add(
                new Room(
                    resultSet.getInt(1), 
                    resultSet.getString(10),
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
    

    static public ArrayList<Room> GetRoomInBuilding(String building) throws SQLException
    {
        ArrayList<Room> ret = new ArrayList<Room>();
        String query = "SELECT R.RoomID, R.roomType, RD.Building ,R.Price, R.imagePath, RD.Max_Members, RD.Bathroom, RD.Bedroom, RD.Description, R.Title FROM room as R INNER JOIN roomdetails as RD ON R.RoomID = RD.RoomID WHERE Building='" + building + "'";
        ResultSet resultSet = DataBase.getStatement().executeQuery(query);
        while (resultSet.next())
        {
            ret.add(
                new Room(
                    resultSet.getInt(1), 
                    resultSet.getString(10),
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
