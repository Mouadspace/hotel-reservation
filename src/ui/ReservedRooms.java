package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import constants.COLORS;
import constants.FONTS;
import model.Room;
import mswing.CustomButton;
import mswing.CustomPanel;
import mswing.CustomTopBar;

import utils.ImgUtil;
import utils.navigation.Screen;

public class ReservedRooms extends Screen implements ActionListener{
  private CustomButton chooseDateButton;
  ArrayList<Room> rooms = new ArrayList<Room>();

    // Getting data form the database
    // Image of the cancellation Panel - LEFT Panel:
    String rightPanelImage = "connecting-rooms.jpg";
    // Room type - LEFT Panel:
    String rightPanelRoomType = "Connection Room";
    // Check In:
    String rightPanelCheckIn = "12-09-2024";
    // Check Out:
    String rightPanelCheckOut = "15-09-2024";
    // Total Price - LEFT Panel:
    Integer totalPriceRightPanel = 220;    
    // Cancelation date - LEFT Panel
    Boolean freeCancellation = true;


  public ReservedRooms() throws FontFormatException, IOException{
    rooms.add(new Room(1, "Connecting room", "A1", 100f));
    rooms.add(new Room(2, "Business room", "A1", 350f));

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

    // RIGHT SIDE PANEL
    JPanel roomDetails = new JPanel();
    roomDetails.setLayout(new BoxLayout(roomDetails, BoxLayout.Y_AXIS));
    roomDetails.setBackground(COLORS.surface);
    roomDetails.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, COLORS.lightGrey));
    roomDetails.setPreferredSize(new Dimension(320, 0));

    JLabel hotelImage = new JLabel();
    hotelImage.setIcon(new ImageIcon(ImgUtil.makeRounedImage("assets/"+rightPanelImage, 12, 280)));
    // Here we use the border as margin or padding
    hotelImage.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 0));
    hotelImage.setAlignmentX(0.0f);

    JLabel roomType = new JLabel(rightPanelRoomType);
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

    JLabel checkInDate = new JLabel(rightPanelCheckIn);
    checkInDate.setFont(font.getLabelBold());
    checkInDate.setForeground(new Color(0x222831));
    checkingsGridXY.gridx = 1;
    checkingsGridXY.gridy = 0;
    checkingsGridXY.fill  = GridBagConstraints.HORIZONTAL;
    checkingsGridXY.weightx = 0;
    CheckingsPanel.add(checkInDate, checkingsGridXY);

    JLabel checkOutDate = new JLabel(rightPanelCheckOut);
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

    JTextArea roomDetailsContent = new JTextArea("Soundproofed Air conditioning Free cots/infant beds Flat-screen TV Hairdryer Bathrobes Free bottled water Espresso maker");
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
    // totalPriceAndCancelPanel.setBackground(Color.red);
    GridBagConstraints totalPriceAndCancelPanelGridXY = new GridBagConstraints();

    JLabel totalPrice = new JLabel(totalPriceRightPanel.toString()+"$");
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
                System.out.println("Cancel button clicked");
              }
        }     
      });
    // Add the button to its Panel:
    cancelPanel.add(cancelButton);
    // Addin the cancel to the grid
    totalPriceAndCancelPanelGridXY.gridx = 1;
    totalPriceAndCancelPanelGridXY.gridy = 0;
    totalPriceAndCancelPanelGridXY.fill  = GridBagConstraints.HORIZONTAL;
    totalPriceAndCancelPanelGridXY.weightx = 0;
    totalPriceAndCancelPanel.add(cancelPanel, totalPriceAndCancelPanelGridXY);
    totalPriceAndCancelPanel.setMaximumSize(new Dimension(320, totalPriceAndCancelPanel.getPreferredSize().height));

    // MAIN PANEL
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    JLabel roomOptionsLabel = new JLabel("Your Reservations");
    roomOptionsLabel.setFont(font.getH4());
    roomOptionsLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
    
    JPanel roomListPanel = new JPanel();
    roomListPanel.setLayout(new BoxLayout(roomListPanel, BoxLayout.Y_AXIS));
    roomListPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));


    for (Room room: rooms){
      CustomPanel roomCard = new CustomPanel();
      roomCard.setLayout(new FlowLayout(FlowLayout.LEFT));
      roomCard.setBackground(COLORS.surface);
      roomCard.setRoundAll(12);
      roomCard.setAlignmentX(0.0f);
      roomCard.setCursor(new Cursor(Cursor.HAND_CURSOR));
      
      JLabel roomImage = new JLabel();
      roomImage.setIcon(new ImageIcon(ImgUtil.makeRounedImage("assets/connecting-rooms.jpg", 12, 130, 100)));
      roomImage.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 10));

      JPanel cardRoomDetails = new JPanel();
      cardRoomDetails.setBackground(COLORS.transparent);
      cardRoomDetails.setLayout(new BoxLayout(cardRoomDetails, BoxLayout.Y_AXIS));

      JLabel roomTypeLabel = new JLabel(room.GetRoomName());
      roomTypeLabel.setFont(font.getH6()); 
      
      LocalDate cardDateIn = LocalDate.of(2024, 9, 12);
      String dayNameIn = cardDateIn.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
      String monthNameIn = cardDateIn.getMonth().getDisplayName(TextStyle.SHORT, Locale.getDefault());
      String dayIn = "12";

      LocalDate cardDateOut = LocalDate.of(2024, 9, 15);
      String dayNameOut = cardDateOut.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
      String monthNameOut = cardDateOut.getMonth().getDisplayName(TextStyle.SHORT, Locale.getDefault());
      String dayOut = "15";

      JLabel checkingDates = new JLabel(dayNameIn + " " + dayIn  + " " + monthNameIn + " - " + dayNameOut + " " + dayOut  + " " + monthNameOut);
      checkingDates.setFont(font.getLabel());
      checkingDates.setForeground(COLORS.grey);

      
      JLabel quantity = new JLabel("<html>4 people<br> 3 bedrooms<br> 3 bathrooms</html>");
      quantity.setFont(font.getLabel());
      quantity.setForeground(COLORS.grey);

      JLabel roomPriceLabel = new JLabel(220 + "$");
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
    
      roomListPanel.add(roomCard);
      roomListPanel.add(Box.createVerticalStrut(20));
    }


    // ADDING COMPONENT TO THE MAIN PANEL 
    mainPanel.add(roomOptionsLabel, BorderLayout.NORTH);
    mainPanel.add(roomListPanel, BorderLayout.CENTER);

    JPanel helperButtom = new JPanel(new GridLayout());
    helperButtom.setBackground(new Color(0, 0, 0, 0));
    helperButtom.setAlignmentX(0.0f);
    chooseDateButton = new CustomButton();
    chooseDateButton.setBackground(COLORS.primary);
    chooseDateButton.setForeground(COLORS.surface);
    chooseDateButton.setText("choose your date");
    chooseDateButton.setFont(font.getH6());
    chooseDateButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    helperButtom.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
    chooseDateButton.setFocusable(false);
    helperButtom.setMaximumSize(new Dimension(320, helperButtom.getPreferredSize().height));
    helperButtom.add(chooseDateButton);


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
    add(headerPanel, BorderLayout.NORTH);
    add(mainPanel, BorderLayout.CENTER);
    add(roomDetails, BorderLayout.EAST);



    // ADDING LISTENERS 
    chooseDateButton.addActionListener(this);


  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if( e.getSource() == chooseDateButton){
      navigateTo("/reservation");
    }
  }



  

}