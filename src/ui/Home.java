package ui;

import java.io.IOException;
import java.util.ArrayList;
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

public class Home extends Screen implements ActionListener{
  private CustomButton chooseDateButton;
  ArrayList<Room> rooms = new ArrayList<Room>();







  public Home() throws FontFormatException, IOException{
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
    hotelImage.setIcon(new ImageIcon(ImgUtil.makeRounedImage("assets/connecting-rooms.jpg", 12, 280)));
    hotelImage.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
    hotelImage.setAlignmentX(0.0f);

    
    JPanel typePricePanel = new JPanel();
    typePricePanel.setLayout(new BoxLayout(typePricePanel, BoxLayout.X_AXIS));
    typePricePanel.setBackground(COLORS.transparent);
    
    JLabel roomPrice = new JLabel((int)rooms.get(0).GetPrice() + "$/night");
    roomPrice.setFont(font.getLabelBold());
    roomPrice.setForeground(COLORS.primary);
    
    JLabel roomType = new JLabel(rooms.get(0).GetRoomName());
    roomType.setFont(font.getH5());
    
    typePricePanel.add(roomType);
    typePricePanel.add(Box.createHorizontalGlue());
    typePricePanel.add(roomPrice);
    typePricePanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 20));
    typePricePanel.setMaximumSize(new Dimension(320, typePricePanel.getPreferredSize().height));
    typePricePanel.setAlignmentX(0.0f);
    
    
    JLabel roomDetailsLabel = new JLabel("Room details");
    roomDetailsLabel.setFont(font.getLabelBold());
    roomDetailsLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 0));

    JTextArea roomDetailsContent = new JTextArea("Soundproofed Air conditioning Free cots/infant beds Flat-screen TV Hairdryer Bathrobes Free bottled water Espresso maker");
    roomDetailsContent.setFont(font.getLabelBold());
    roomDetailsContent.setForeground(COLORS.grey);
    roomDetailsContent.setLineWrap(true);
    roomDetailsContent.setWrapStyleWord(true);
    roomDetailsContent.setEditable(false);
    roomDetailsContent.getCaret().deinstall(roomDetailsContent);
    roomDetailsContent.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    roomDetailsContent.setAlignmentX(0.0f);
    roomDetailsContent.setMaximumSize(new Dimension(320, roomDetailsContent.getPreferredSize().height));
    
    JLabel roomFeaturesLabel = new JLabel("Room features");
    roomFeaturesLabel.setFont(font.getLabelBold());
    roomFeaturesLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 0));

    String features[] = {"WI-FI","Bathroom","Flat-screen TV","Espresso maker"}; 
    JPanel roomFeaturesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    roomFeaturesPanel.setBackground(COLORS.transparent);

    for (String feature: features){
      JLabel item = new JLabel("+ "+ feature);
      item.setFont(font.getLabel());
      item.setForeground(COLORS.grey);
      item.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
      roomFeaturesPanel.add(item);
    }
    roomFeaturesPanel.setAlignmentX(0.0f);
    roomFeaturesPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));


    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    JLabel roomOptionsLabel = new JLabel("Rooms options");
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
      
      JLabel roomPriceLabel = new JLabel((int)room.GetPrice() + "$/night");
      roomPriceLabel.setFont(font.getLabelBold());
      roomPriceLabel.setForeground(COLORS.primary);
      roomPriceLabel.setAlignmentX(0.0f);

      JLabel roomTypeLabel = new JLabel(room.GetRoomName());
      roomTypeLabel.setFont(font.getH6());    
      
      JLabel quantity = new JLabel("<html>4 people<br> 3 bedrooms<br> 3 bathrooms</html>");
      quantity.setFont(font.getLabel());
      quantity.setForeground(COLORS.grey);

     
      
      cardRoomDetails.add(roomPriceLabel);
      cardRoomDetails.add(roomTypeLabel);
      cardRoomDetails.add(quantity);
      
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
    roomDetails.add(typePricePanel);
    roomDetails.add(roomDetailsLabel);
    roomDetails.add(roomDetailsContent);
    roomDetails.add(roomFeaturesLabel);
    roomDetails.add(roomFeaturesPanel);

    roomDetails.add(Box.createVerticalGlue());
    roomDetails.add(helperButtom);


    

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