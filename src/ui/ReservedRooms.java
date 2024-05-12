package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.*;

// import org.w3c.dom.events.MouseEvent;

import constants.COLORS;
import constants.FONTS;
import model.Reservation;
import mswing.CustomButton;
import mswing.CustomPanel;
import mswing.CustomTopBar;

import utils.ImgUtil;
import utils.navigation.Screen;

public class ReservedRooms extends Screen{

  private CustomButton returnHome;

  // Here we just initialize the array of the reservations of the user 
  // and the Room in the right panel
  private Reservation rightPanelRoom;
  ArrayList<Reservation> allReservations = new ArrayList<Reservation>();


  // This function is to get a string of a day in letters, in number and the month
  // Example: date = 2024-05-20 -> funtion return = Thu 05 May
  public String getShortDate(LocalDate date) {
    String dayName = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
    String monthName = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.getDefault());
    int day = date.getDayOfMonth();

    String shortDate = dayName + " " + day + " " + monthName;

    return shortDate;
  }


  public ReservedRooms() throws FontFormatException, IOException, SQLException{
    // Here we get the instance of the connected user:
    int ClientID = 4;

    // Setting some Panels that we're going to need in both cases (if there is any reservations or there isn't)
    // This the parent panel:
    setLayout(new BorderLayout());
    setBackground(COLORS.background);

    // FONTS : 
    FONTS font = new FONTS();

    JPanel headerPanel = new JPanel(new GridLayout());
    headerPanel.setPreferredSize(new Dimension(0, 50));
    headerPanel.setBackground(COLORS.background);
    headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, COLORS.lightGrey));
     
    // ADDING THE HEADER
    headerPanel.add(new CustomTopBar());
 
    // MAIN PANEL
    JPanel mainPanel = new JPanel();

    // Here we get the reservations and set the default right panel room
    // All Reservations
    allReservations = Reservation.getReservations(ClientID);

    if (allReservations.isEmpty()) {
      //Creating a label for a message of 0 Reservations have been made yet
      JLabel message1 = new JLabel("Looks like you haven't made any reservations yet.");
      message1.setBorder(BorderFactory.createEmptyBorder(100, 100, 5, 100));
      message1.setFont(font.getH5());
      JLabel message2 = new JLabel("Feel free to explore our available rooms and find the perfect one for your next stay!");
      message2.setBorder(BorderFactory.createEmptyBorder(0, 70, 10, 70));
      message2.setFont(font.getH6());

      // Creating a button of Going to reserve some pages
      JPanel goHomeButton = new JPanel(new GridLayout());
      goHomeButton.setBackground(new Color(0, 0, 0, 0));
      goHomeButton.setAlignmentX(0.0f);
      returnHome = new CustomButton();
      returnHome.setBackground(COLORS.primary);
      returnHome.setForeground(COLORS.surface);
      returnHome.setText("Check Avaible Rooms");
      returnHome.setFont(font.getH6());
      returnHome.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
      goHomeButton.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
      returnHome.setFocusable(false);
      goHomeButton.setMaximumSize(new Dimension(320, 20));
      goHomeButton.add(returnHome);

      // Set some action to the button
    returnHome.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          if (e.getSource() == returnHome){
            // Navigate back to home page
            navigateTo("/home"); 
      }}     
    });


      // Adding the text message
      mainPanel.add(message1);
      mainPanel.add(message2);
      mainPanel.add(goHomeButton);
    } else {
      mainPanel.setLayout(new BorderLayout());

    // Default Right Panel ROOM
    rightPanelRoom = new Reservation(allReservations.get(0));

   

    // RIGHT SIDE PANEL
    JPanel roomDetails = new JPanel();
    roomDetails.setLayout(new BoxLayout(roomDetails, BoxLayout.Y_AXIS));
    roomDetails.setBackground(COLORS.surface);
    roomDetails.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, COLORS.lightGrey));
    roomDetails.setPreferredSize(new Dimension(320, 0));

    JLabel hotelImage = new JLabel();
    hotelImage.setIcon(new ImageIcon(ImgUtil.makeRounedImage("assets/"+rightPanelRoom.getImagePath(), 12, 280)));
    // Here we use the border as margin or padding
    hotelImage.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 0));
    hotelImage.setAlignmentX(0.0f);

    JLabel roomType = new JLabel(rightPanelRoom.getRoomType());
    roomType.setFont(font.getH5());
    roomType.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 0));

    JPanel CheckingsPanel = new JPanel(new GridBagLayout());
    CheckingsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    CheckingsPanel.setAlignmentX(0.0f);
    CheckingsPanel.setBackground(new Color(0, 0, 0, 0));
    GridBagConstraints checkingsGridXY = new GridBagConstraints();

    JLabel checkIn = new JLabel("Check-in");
    checkIn.setFont(font.getLabel());
    checkIn.setForeground(new Color(0x222831));
    checkingsGridXY.gridx = 0;
    checkingsGridXY.gridy = 0;
    checkingsGridXY.fill  = GridBagConstraints.HORIZONTAL;
    checkingsGridXY.weightx = 1;
    CheckingsPanel.add(checkIn, checkingsGridXY);

    JLabel checkOut = new JLabel("Check-out");
    checkOut.setFont(font.getLabel());
    checkOut.setForeground(new Color(0x222831));
    checkingsGridXY.gridx = 0;
    checkingsGridXY.gridy = 1;
    checkingsGridXY.fill  = GridBagConstraints.HORIZONTAL;
    checkingsGridXY.weightx = 1;
    CheckingsPanel.add(checkOut, checkingsGridXY);

    JLabel checkInDate = new JLabel(getShortDate(rightPanelRoom.getCheckIn()));
    checkInDate.setFont(font.getLabelBold());
    checkInDate.setForeground(new Color(0x222831));
    checkingsGridXY.gridx = 1;
    checkingsGridXY.gridy = 0;
    checkingsGridXY.fill  = GridBagConstraints.HORIZONTAL;
    checkingsGridXY.weightx = 0;
    CheckingsPanel.add(checkInDate, checkingsGridXY);

    JLabel checkOutDate = new JLabel(getShortDate(rightPanelRoom.getCheckOut()));
    checkOutDate.setFont(font.getLabelBold());
    checkOutDate.setForeground(new Color(0x222831));
    checkingsGridXY.gridx = 1;
    checkingsGridXY.gridy = 1;
    checkingsGridXY.fill  = GridBagConstraints.HORIZONTAL;
    checkingsGridXY.weightx = 0;
    CheckingsPanel.add(checkOutDate, checkingsGridXY);
    
    CheckingsPanel.setMaximumSize(new Dimension(320, CheckingsPanel.getPreferredSize().height));
    
    JLabel roomDetailsLabel = new JLabel("Room details");
    roomDetailsLabel.setFont(font.getMediumBold());
    roomDetailsLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 0));

    JTextArea roomDetailsContent = new JTextArea(rightPanelRoom.getDescription());
    roomDetailsContent.setFont(font.getLabel());
    roomDetailsContent.setForeground(new Color(0x222831));
    roomDetailsContent.setLineWrap(true);
    roomDetailsContent.setWrapStyleWord(true);
    roomDetailsContent.setEditable(false);
    roomDetailsContent.getCaret().deinstall(roomDetailsContent);
    roomDetailsContent.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    roomDetailsContent.setAlignmentX(0.0f);
    roomDetailsContent.setMaximumSize(new Dimension(320, roomDetailsContent.getPreferredSize().height));
    
    JLabel roomFeaturesLabel = new JLabel("Room features");
    roomFeaturesLabel.setFont(font.getMediumBold());
    roomFeaturesLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 0));

    String features[] = {"WI-FI","Bathroom","Flat-screen TV","Espresso maker"}; 
    JPanel roomFeaturesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    roomFeaturesPanel.setBackground(COLORS.transparent);

    for (String feature: features){
      JLabel item = new JLabel("+ "+ feature);
      item.setFont(font.getLabel());
      item.setForeground(new Color(0x222831));
      item.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
      roomFeaturesPanel.add(item);
    }
    roomFeaturesPanel.setAlignmentX(0.0f);
    roomFeaturesPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

    JPanel totalPriceAndCancelPanel = new JPanel(new GridBagLayout());
    totalPriceAndCancelPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
    totalPriceAndCancelPanel.setAlignmentX(0.0f);
    totalPriceAndCancelPanel.setBackground(new Color(0, 0, 0, 0));
    GridBagConstraints totalPriceAndCancelPanelGridXY = new GridBagConstraints();

    JLabel totalPrice = new JLabel(rightPanelRoom.getTotalPrice()+"$");
    totalPrice.setFont(font.getH4());
    totalPrice.setForeground(new Color(0xFC6222));
    totalPriceAndCancelPanelGridXY.gridx = 0;
    totalPriceAndCancelPanelGridXY.gridy = 0;
    totalPriceAndCancelPanelGridXY.fill  = GridBagConstraints.HORIZONTAL;
    totalPriceAndCancelPanelGridXY.weightx = 1;
    totalPriceAndCancelPanel.add(totalPrice, totalPriceAndCancelPanelGridXY);

    JPanel cancelPanel = new JPanel(new GridLayout());
    cancelPanel.setBackground(new Color(0, 0, 0, 0));
    cancelPanel.setAlignmentX(0.0f);
    CustomButton cancelButton = new CustomButton();
    cancelButton.setFocusable(false);
    cancelButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
    cancelButton.setBorder(1, new Color(0xFC6222) );
    cancelButton.setBorderRadius(12);
    cancelButton.setEffectColor(new Color(0xFC6222));
    cancelButton.setForeground(new Color(0xFC6222));
    cancelButton.setText("Cancel");
    cancelButton.setFont(font.getLabel()); 
    // Set some action to the button
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          if (e.getSource() == cancelButton){
            try {
              rightPanelRoom.cancelReservation();
              
              // We'll get the new list of reservations
              allReservations = Reservation.getReservations(4);

              // Navigate back to home page
              navigateTo("/home");

            } catch (SQLException e1) {
              e1.printStackTrace();
            } 
      }}     
    });
    // Add the button to its Panel:
    cancelPanel.add(cancelButton);
    // Adding the cancel to the grid
    totalPriceAndCancelPanelGridXY.gridx = 1;
    totalPriceAndCancelPanelGridXY.gridy = 0;
    totalPriceAndCancelPanelGridXY.fill  = GridBagConstraints.HORIZONTAL;
    totalPriceAndCancelPanelGridXY.weightx = 0;
    totalPriceAndCancelPanel.add(cancelPanel, totalPriceAndCancelPanelGridXY);
    totalPriceAndCancelPanel.setMaximumSize(new Dimension(320, totalPriceAndCancelPanel.getPreferredSize().height));

    
    // Continue the code of the main panel

    JLabel roomOptionsLabel = new JLabel("Your Reservations");
    roomOptionsLabel.setFont(font.getH4());
    roomOptionsLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
    
    JPanel roomListPanel = new JPanel();
    roomListPanel.setLayout(new BoxLayout(roomListPanel, BoxLayout.Y_AXIS));
    roomListPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));


    for (Reservation room: allReservations){
      CustomPanel roomCard = new CustomPanel();
      roomCard.setLayout(new FlowLayout(FlowLayout.LEFT));
      roomCard.setBackground(COLORS.surface);
      roomCard.setRoundAll(12);
      roomCard.setAlignmentX(0.0f);
      roomCard.setCursor(new Cursor(Cursor.HAND_CURSOR));
      
      JLabel roomImage = new JLabel();
      roomImage.setIcon(new ImageIcon(ImgUtil.makeRounedImage("assets/"+room.getImagePath(), 12, 130, 100)));
      roomImage.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 10));

      JPanel cardRoomDetails = new JPanel();
      cardRoomDetails.setBackground(COLORS.transparent);
      cardRoomDetails.setLayout(new BoxLayout(cardRoomDetails, BoxLayout.Y_AXIS));

      JLabel roomTypeLabel = new JLabel(room.getRoomType());
      roomTypeLabel.setFont(font.getH6()); 
      
      String shortDateIn = getShortDate(room.getCheckIn());

      String shortDateOut = getShortDate(room.getCheckOut());
      
      JLabel checkingDates = new JLabel(shortDateIn + " - " + shortDateOut);
      checkingDates.setFont(font.getLabel());
      checkingDates.setForeground(COLORS.grey);

      
      JLabel quantity = new JLabel("<html>"+ room.getMaxMembers() +" People <br>"+ room.getBedrooms() +" Bedrooms<br>"+ room.getBathrooms() +" Bathrooms</html>");
      quantity.setFont(font.getLabel());
      quantity.setForeground(COLORS.grey);

      JLabel roomPriceLabel = new JLabel(room.getTotalPrice() + "$");
      roomPriceLabel.setFont(font.getMediumBold());
      roomPriceLabel.setForeground(COLORS.primary);
      roomPriceLabel.setAlignmentX(0.0f);
      roomPriceLabel.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));

     
      cardRoomDetails.add(roomTypeLabel);
      cardRoomDetails.add(checkingDates);
      cardRoomDetails.add(quantity);
      cardRoomDetails.add(roomPriceLabel);
      
      roomCard.add(roomImage);
      roomCard.add(cardRoomDetails);
      roomCard.setMaximumSize(new Dimension(Math.max(roomCard.getPreferredSize().width, 370), roomCard.getPreferredSize().height));

      // Add an ActionListener to the roomCard
      // Here we change the content of the Right Panel Room
      roomCard.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
             // Update the room details in the typePricePanel
             rightPanelRoom = new Reservation(room);
             roomType.setText(rightPanelRoom.getRoomType());
             totalPrice.setText(Double.toString(rightPanelRoom.getTotalPrice()) + "$");
             roomDetailsContent.setText(rightPanelRoom.getDescription());
             checkInDate.setText(getShortDate(rightPanelRoom.getCheckIn()));
             checkOutDate.setText(getShortDate(rightPanelRoom.getCheckOut()));
             roomDetailsContent.setText(rightPanelRoom.getDescription());
             try {
               hotelImage.setIcon(new ImageIcon(ImgUtil.makeRounedImage("assets/"+rightPanelRoom.getImagePath(), 12, 280)));
             } catch (IOException e1) {
               e1.printStackTrace();
             }
             roomDetails.repaint();
          }
      });
    
      roomListPanel.add(roomCard);
      roomListPanel.add(Box.createVerticalStrut(20));
    }

    


    // ADDING COMPONENT TO THE MAIN PANEL 
    mainPanel.add(roomOptionsLabel, BorderLayout.NORTH);
    mainPanel.add(roomListPanel, BorderLayout.CENTER);


    // ADDING COMPONENTS TO SUMMARY PANEL
    roomDetails.add(hotelImage);
    roomDetails.add(roomType);
    roomDetails.add(CheckingsPanel);
    roomDetails.add(roomDetailsLabel);
    roomDetails.add(roomDetailsContent);
    roomDetails.add(roomFeaturesLabel);
    roomDetails.add(roomFeaturesPanel);

    // roomDetails.add(Box.createVerticalGlue());
    roomDetails.add(totalPriceAndCancelPanel);
  
    // ADDING PANELES TO FRAME
    // Here is the Room Details, cause we'll use only if there is rooms
    add(roomDetails, BorderLayout.EAST);

  }
  // The rest of the panels.
  add(headerPanel, BorderLayout.NORTH);
  add(mainPanel, BorderLayout.CENTER);
  }



}