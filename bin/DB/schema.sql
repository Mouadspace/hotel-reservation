-- Create Client table
CREATE TABLE Client (
    ClientID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100),
    Email VARCHAR(100),
    Password VARCHAR(100),
    Phone VARCHAR(20)
);

-- Create Hotel table
CREATE TABLE Hotel (
    HotelID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100),
    Location VARCHAR(100),
    Description TEXT

);

-- Create Room table
CREATE TABLE Room (
    RoomID INT PRIMARY KEY AUTO_INCREMENT,
    HotelID INT,
    Type VARCHAR(50),
    Price DECIMAL(10, 2),
    Availability BOOLEAN,
    Image      BLOB,
    FOREIGN KEY (HotelID) REFERENCES Hotel(HotelID)
);

-- Create Reservation table
CREATE TABLE Reservation (
    ReservationID INT PRIMARY KEY AUTO_INCREMENT,
    ClientID INT,
    RoomID INT,
    CheckInDate DATE,
    CheckOutDate DATE,
    TotalPrice DECIMAL(10, 2),
    Status VARCHAR(50),
    FOREIGN KEY (ClientID) REFERENCES Client(ClientID),
    FOREIGN KEY (RoomID) REFERENCES Room(RoomID)
);

-- Create Discount table
CREATE TABLE Discount (
    DiscountID INT PRIMARY KEY AUTO_INCREMENT,
    HotelID INT,
    DiscountPercentage DECIMAL(5, 2),
    Description TEXT,
    FOREIGN KEY (HotelID) REFERENCES Hotel(HotelID)
);

-- Create Admin table
CREATE TABLE Admin (
    AdminID INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100),
    Password VARCHAR(100)
);
-- Create Admin table
CREATE TABLE RoomDetails (
    RoomDetailID INT PRIMARY KEY AUTO_INCREMENT,
    RoomID INT,
    Max_Members INT,               
    Bathroom INT,
    Description VARCHAR(200),
    Building VARCHAR(10),
    FOREIGN KEY (RoomID) REFERENCES Room(RoomID)
    
);

--------------------------------------------------------