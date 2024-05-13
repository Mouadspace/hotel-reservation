package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import constants.COLORS;
import constants.FONTS;
import model.Room;
import model.User;
import mswing.CustomButton;
import mswing.CustomPanel;
import mswing.CustomTopBar;
import routes.InitRoutes;
import utils.ImgUtil;
import utils.navigation.Screen;
import utils.navigation.ScreenManager;

public class Home extends Screen implements ActionListener{
  // The ClientID & RoomID
  private int ClientID;
  private int RoomID;

  public int getClientID() {
    return ClientID;
  }

  public void setClientID(int clientID) {
    ClientID = clientID;
  }

  public int getRoomID() {
    return RoomID;
  }

  public void setRoomID(int roomID) {
    RoomID = roomID;
  }

  private CustomButton chooseDateButton;
  ArrayList<Room> rooms = new ArrayList<Room>();
  private Room currentCard;
  private User client;


  public Home(User client) throws FontFormatException, IOException, SQLException{
    this.client = client;
    rooms=Room.GetRooms();
    currentCard = rooms.get(0);

    setLayout(new BorderLayout());
    setBackground(COLORS.background);

    // FONTS : 
    FONTS font = new FONTS();

    JPanel headerPanel = new JPanel(new GridLayout());
    headerPanel.setPreferredSize(new Dimension(0, 50));
    headerPanel.setBackground(COLORS.background);
    headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, COLORS.lightGrey));


    CustomTopBar customTopBar = new CustomTopBar(client);
    // ADDING THE HEADER
    headerPanel.add(customTopBar);

    Room firstRoom = rooms.get(0);
    // RIGHT SIDE PANEL
    JPanel roomDetails = new JPanel();
    roomDetails.setLayout(new BoxLayout(roomDetails, BoxLayout.Y_AXIS));
    roomDetails.setBackground(COLORS.surface);
    roomDetails.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, COLORS.lightGrey));
    roomDetails.setPreferredSize(new Dimension(320, 0));

    JLabel hotelImage = new JLabel();
    hotelImage.setIcon(new ImageIcon(ImgUtil.makeRounedImage("assets/"+firstRoom.getImage(), 12, 280)));
    hotelImage.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
    hotelImage.setAlignmentX(0.0f);

    
    JPanel typePricePanel = new JPanel();
    typePricePanel.setLayout(new BoxLayout(typePricePanel, BoxLayout.X_AXIS));
    typePricePanel.setBackground(COLORS.transparent);
    
    JLabel roomPrice = new JLabel((int)firstRoom.getPrice() + "$/night");
    roomPrice.setFont(font.getLabelBold());
    roomPrice.setForeground(COLORS.primary);
    
    JLabel roomType = new JLabel(firstRoom.getRoomName());
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

    JTextArea roomDetailsContent = new JTextArea(firstRoom.getDescription());
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


    JPanel helperButtom = new JPanel(new GridLayout());
    helperButtom.setBackground(new Color(0, 0, 0, 0));
    helperButtom.setAlignmentX(0.0f);
    chooseDateButton = new CustomButton();
    chooseDateButton.setBackground(COLORS.primary);
    chooseDateButton.setForeground(COLORS.surface);
    chooseDateButton.setText("Book now");
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

    // MAIN PANEL
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    JLabel roomOptionsLabel = new JLabel("Discover our rooms");
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
      roomImage.setIcon(new ImageIcon(ImgUtil.makeRounedImage("assets/"+room.getImage(), 12, 130, 100)));
      roomImage.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 10));

      JPanel cardRoomDetails = new JPanel();
      cardRoomDetails.setBackground(COLORS.transparent);
      cardRoomDetails.setLayout(new BoxLayout(cardRoomDetails, BoxLayout.Y_AXIS));
      
      JLabel roomPriceLabel = new JLabel((int)room.getPrice() + "$/night");
      roomPriceLabel.setFont(font.getLabelBold());
      roomPriceLabel.setForeground(COLORS.primary);
      roomPriceLabel.setAlignmentX(0.0f);

      JLabel roomTypeLabel = new JLabel(room.getRoomName());
      roomTypeLabel.setFont(font.getH6());    
      
      JLabel quantity = new JLabel("<html>" + room.getMembers() + " people<br> " + room.getBedrooms() + " bedrooms<br> " + room.getBathrooms() + " bathrooms</html>");
      quantity.setFont(font.getLabel());
      quantity.setForeground(COLORS.grey);

     
      cardRoomDetails.add(roomPriceLabel);
      cardRoomDetails.add(roomTypeLabel);
      cardRoomDetails.add(quantity);
      
      roomCard.add(roomImage);
      roomCard.add(cardRoomDetails);
      roomCard.setMaximumSize(new Dimension(Math.max(roomCard.getPreferredSize().width, 370), roomCard.getPreferredSize().height));

      // Add a MouseListener to the roomCard
      roomCard.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            // Setting the RoomID
            setRoomID(room.getRoomID());
            
            // Update the room details in the typePricePanel
            roomType.setText(room.getRoomName());
            roomPrice.setText((int) room.getPrice() + "$/night");
            roomDetailsContent.setText(room.getDescription());
            try {
              hotelImage.setIcon(new ImageIcon(ImgUtil.makeRounedImage("assets/"+room.getImage(), 12, 280)));
            } catch (IOException e1) {
              e1.printStackTrace();
            }

            // Repaint the roomDetails panel to reflect the changes
            roomDetails.repaint();
            currentCard = room;

          }
      }); 

      roomListPanel.add(roomCard);
      roomListPanel.add(Box.createVerticalStrut(20));
      
    }


    // ADDING COMPONENT TO THE MAIN PANEL 
    mainPanel.add(roomOptionsLabel, BorderLayout.NORTH);
    mainPanel.add(roomListPanel, BorderLayout.CENTER);

    


    

    // ADDING PANELES TO FRAME
    add(headerPanel, BorderLayout.NORTH);
    add(mainPanel, BorderLayout.CENTER);
    add(roomDetails, BorderLayout.EAST);



    // ADDING LISTENERS 
    chooseDateButton.addActionListener(this);


  }

  public void updateDisplayedRooms(ArrayList<Room> updatedRooms) {
    rooms = updatedRooms;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if( e.getSource() == chooseDateButton){
      try {
        ScreenManager sm = InitRoutes.screenManager; 
        
        if(client.isLoggedIn){
          sm.add(new Reservation(currentCard, client), "/reservation");
          navigateTo("/reservation");
        }else{
          navigateTo("/login");
        }
      } catch (FontFormatException | IOException e1) {
        e1.printStackTrace();
      }
    }
  }


}