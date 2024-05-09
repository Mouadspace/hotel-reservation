package ui;

import java.io.IOException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import constants.COLORS;
import constants.FONTS;
import mswing.CustomButton;
import mswing.CustomTopBar;

import utils.ImgUtil;
import utils.navigation.Screen;

public class Home extends Screen implements ActionListener{
  private CustomButton chooseDateButton;







  public Home() throws FontFormatException, IOException{
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
    
    JLabel roomPrice = new JLabel("100$/night");
    roomPrice.setFont(font.getLabelBold());
    roomPrice.setForeground(COLORS.primary);
    
    JLabel roomType = new JLabel("Connecting room");
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