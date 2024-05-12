DROP TABLE IF EXISTS roomdetails;
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS client;

-- Create Client table
CREATE TABLE client (
    ClientID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100),
    Email VARCHAR(100),
    Password VARCHAR(100),
    Phone VARCHAR(20)
);

-- Create Room table
CREATE TABLE room (
    RoomID INT PRIMARY KEY AUTO_INCREMENT,
    roomType VARCHAR(50),
    Title VARCHAR(50),
    Price DECIMAL(10, 2),
    imagePath VARCHAR(50)
);

-- Create Reservation table
CREATE TABLE reservation (
    ReservationID INT PRIMARY KEY AUTO_INCREMENT,
    ClientID INT,
    RoomID INT,
    CheckInDate DATE,
    CheckOutDate DATE,
    TotalPrice DECIMAL(10, 2),

    FOREIGN KEY (ClientID) REFERENCES Client(ClientID),
    FOREIGN KEY (RoomID) REFERENCES Room(RoomID)
);

-- Create Admin table
CREATE TABLE admin (
    AdminID INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100),
    Password VARCHAR(100)
);
-- Create Admin table
CREATE TABLE roomdetails (
    RoomDetailID INT PRIMARY KEY AUTO_INCREMENT,
    RoomID INT,
    Max_Members INT,               
    Bathroom INT,
    Bedroom INT,
    Description VARCHAR(200),
    Building VARCHAR(10),
    FOREIGN KEY (RoomID) REFERENCES Room(RoomID)
);