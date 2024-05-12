package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DB.DataBase;

public class Reservation {
    private int ReservationID;
    private int RoomID;
    private double TotalPrice;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String roomType; 
    private String imagePath;
    private int maxMembers;
    private int bathrooms;
    private int bedrooms;
    private String description;
    private int clientID;
    private String clientName; 
    private String email;
    

    public int getReservationID() {
        return ReservationID;
    }

    public void setReservationID(int reservationID) {
        ReservationID = reservationID;
    }

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int roomID) {
        RoomID = roomID;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getClientID()
    {
        return clientID;
    }
    public String getClientName()
    {
        return clientName;
    }
    public String getEmail()
    {
        return email;
    }

    public Reservation(int ReservationID, int RoomID, double TotalPrice, LocalDate checkIn, LocalDate checkOut, String roomType, String imagePath, int maxMembers, int bathrooms, int bedrooms, String description) {
        this.ReservationID = ReservationID;
        this.RoomID = RoomID;
        this.TotalPrice = TotalPrice;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomType = roomType;
        this.imagePath = imagePath;
        this.maxMembers = maxMembers;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.description = description;
    }

    public Reservation(
        int ReservationID, 
        int RoomID, 
        double TotalPrice, 
        LocalDate checkIn, 
        LocalDate checkOut,
        String roomType, 
        String imagePath, 
        int maxMembers, 
        int bathrooms, 
        int bedrooms, 
        String description, 
        int clientID, 
        String clientName, 
        String email
    ) 
    {
        this.ReservationID = ReservationID;
        this.RoomID = RoomID;
        this.TotalPrice = TotalPrice;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomType = roomType;
        this.imagePath = imagePath;
        this.maxMembers = maxMembers;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.description = description;
        this.clientID = clientID;
        this.clientName = clientName;
        this.email = email;
    }

    public Reservation(Reservation reservedRoom) {
        this.ReservationID = reservedRoom.getReservationID();
        this.RoomID = reservedRoom.getRoomID();
        this.TotalPrice = reservedRoom.getTotalPrice();
        this.checkIn = reservedRoom.getCheckIn();
        this.checkOut = reservedRoom.getCheckOut();
        this.roomType = reservedRoom.getRoomType();
        this.imagePath = reservedRoom.getImagePath();
        this.maxMembers = reservedRoom.getMaxMembers();
        this.bathrooms = reservedRoom.getBathrooms();
        this.bedrooms = reservedRoom.getBedrooms();
        this.description = reservedRoom.getDescription();
    }


    

    // This is to get all the reservations of a client, through his/her id.
    // The data that we're selecting is what we need to display in the Reservation page
    // Either of the user is in the Right panel, or just in card in the main content.
    static public ArrayList<Reservation> getReservations(int ClientID) throws SQLException {
        ArrayList<Reservation> result = new ArrayList<Reservation>();
        String query = "SELECT  rsv.ReservationID, rm.roomID, rsv.TotalPrice, rsv.CheckInDate, \n" + //
                        "rsv.CheckOutDate, rm.roomType, rm.imagePath, dt.Max_Members, dt.Bathroom,\n" + //
                        "dt.Bedroom, dt.Description FROM reservation rsv\n" + //
                        "JOIN room rm ON rsv.RoomID = rm.RoomID\n" + //
                        "JOIN roomdetails dt ON rm.RoomID = dt.RoomID\n" + //
                        "WHERE rsv.ClientID = " + ClientID ;
        ResultSet resultSet = DataBase.getStatement().executeQuery(query);
        while (resultSet.next())
        {
            result.add(
                new Reservation(
                    resultSet.getInt(1), 
                    resultSet.getInt(2),
                    resultSet.getDouble(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8),
                    resultSet.getInt(9),
                    resultSet.getInt(10),
                    resultSet.getString(11)
                ) 
            );
        }
        return result;
    }
    
    // Use this to get reservations by using the room ID
    static public ArrayList<Reservation> getReservationsByRoom(int RoomID) throws SQLException {
        ArrayList<Reservation> result = new ArrayList<Reservation>();
        String query = "SELECT  rsv.ReservationID, rm.roomID, rsv.TotalPrice, rsv.CheckInDate, \n" + //
                        "rsv.CheckOutDate, rm.roomType, rm.imagePath, dt.Max_Members, dt.Bathroom,\n" + //
                        "dt.Bedroom, dt.Description FROM reservation rsv\n" + //
                        "JOIN room rm ON rsv.RoomID = rm.RoomID\n" + //
                        "JOIN roomDetails dt ON rm.RoomID = dt.RoomID\n" + //
                        "WHERE rsv.RoomID = " + RoomID ;
        ResultSet resultSet = DataBase.getStatement().executeQuery(query);
        while (resultSet.next())
        {
            result.add(
                new Reservation(
                    resultSet.getInt(1), 
                    resultSet.getInt(2),
                    resultSet.getDouble(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8),
                    resultSet.getInt(9),
                    resultSet.getInt(10),
                    resultSet.getString(11)
                ) 
            );
        }
        return result;
    }

    

    // This is to cancel the reservation:
    public void cancelReservation() throws SQLException {
        int reservationId = this.ReservationID;
        String query = "DELETE FROM reservation WHERE ReservationID = " + reservationId;
        // Assuming you already have a connection object
        DataBase.getStatement().executeUpdate(query);
    }
}