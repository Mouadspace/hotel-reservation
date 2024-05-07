package ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import mswing.CustomButton;
import mswing.CustomField;
import mswing.CustomFrame;
import mswing.CustomPanel;
import utils.ImgUtil;

public class SavedHotels implements ActionListener, DateChangeListener {

  // Getting data form the database
  // Image of the cancellation Panel - LEFT Panel:
  String leftPanelImage = "connecting-rooms.jpg";
  // Room type - LEFT Panel:
  String leftPanelRoomType = "Connection Room";
  // Check In:
  String leftPanelCheckIn = "12-09-2024";
  // Check Out:
  String leftPanelCheckOut = "15-09-2024";
  // Room details - LEFT Panel:
  String leftPanelRoomDetails = "Soundproofed Air conditioning Free cots / infant beds Flat-screen TV Hairdryer Bathrobes Free bottled water Espresso maker";
  // Room Features - LEFT Panel:
  String leftPanelRoomFeatures = "*WI-FI *Bathroom *Flat-screen TV *Expresso maker";
  
  // Total Price - LEFT Panel:
  Integer totalPriceLeftPanel = 220;    
  // Cancelation date - LEFT Panel
  Boolean freeCancellation = true;

  // Initionalizing
  private CustomFrame frame;
  private CustomButton backButton;
  private DatePicker startDatePicker;
  private DatePicker endDatePicker;
  



  private JPanel leftJustify(Component component )  {
    JPanel b = new JPanel();
    b.setLayout(new BoxLayout(b, BoxLayout.X_AXIS));   
    b.add(component);
    b.add(Box.createHorizontalGlue());
    b.setMaximumSize(new Dimension(b.getMaximumSize().width, b.getPreferredSize().height));
    return b;
  }



  public SavedHotels() throws FontFormatException, IOException{
    frame = new CustomFrame();
    frame.getContentPane().setBackground(new Color(0xF3F3F3));
    frame.setResizable(true);

    // FONTS : 
    File font_file = new File("Poppins-Regular.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
    Font h4Font = font.deriveFont(Font.BOLD,24f);
    Font h5Font = font.deriveFont(Font.BOLD,20f);
    Font h6Font = font.deriveFont(Font.BOLD,16f);
    // Font h2Font = font.deriveFont(Font.BOLD, 0)

    Font labelSmall = font.deriveFont(12f);
    Font labelMedium = font.deriveFont(Font.BOLD,12f);

    // Here we're creating a panel with Flow Layout with a left alignment.
    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    // We set the height of the panel to 50px, and for the width is stretches.
    headerPanel.setPreferredSize(new Dimension(0, 50));
    // Setting the background color.
    headerPanel.setBackground(new Color(0xF3F3F3));
    // Creating a 1px border in the bottom of the panel.
    headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 51)));


    // Here is a panel for the icon of going back
    JPanel backIconHelper = new JPanel();
    backIconHelper.setBackground(new Color(0, 0, 0, 0));
    backIconHelper.setBorder(BorderFactory.createEmptyBorder(0 ,0, 0, 0));
    
    backButton = new CustomButton();
    backButton.setIcon(new ImageIcon(ImgUtil.resizeImage("assets/back.png", 20, 20)));
    backButton.setFocusable(false);
    // Here we use the border as margin or padding
    backButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    backButton.setBorderRadius(100);
    backButton.setForeground(Color.red);

    
    // ADDING COMPONENTS TO HEADER
    backIconHelper.add(backButton);
    headerPanel.add(backIconHelper);


    // RIGHT SIDE PANEL - To see all the user's reservations
    JPanel cancellationPanel = new JPanel();
    cancellationPanel.setLayout(new BoxLayout(cancellationPanel, BoxLayout.Y_AXIS));
    cancellationPanel.setBackground(new Color(255, 255, 255));
    cancellationPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(0, 0, 0, 51)));
    cancellationPanel.setPreferredSize(new Dimension(320, 0));

    JLabel hotelImage = new JLabel();
    hotelImage.setIcon(new ImageIcon(ImgUtil.makeRounedImage("assets/"+leftPanelImage, 12, 280)));
    // Here we use the border as margin or padding
    hotelImage.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 0));
    hotelImage.setAlignmentX(0.0f);

    
    JLabel roomType = new JLabel(leftPanelRoomType);
    roomType.setFont(h5Font);
    roomType.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
    
    JPanel CheckingsPanel = new JPanel(new GridBagLayout());
    CheckingsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    CheckingsPanel.setAlignmentX(0.0f);
    CheckingsPanel.setBackground(new Color(0, 0, 0, 0));
    GridBagConstraints checkingsGridXY = new GridBagConstraints();

    JLabel checkIn = new JLabel("Check-in");
    checkIn.setFont(labelSmall);
    checkingsGridXY.gridx = 0;
    checkingsGridXY.gridy = 0;
    checkingsGridXY.fill  = GridBagConstraints.HORIZONTAL;
    checkingsGridXY.weightx = 1;
    CheckingsPanel.add(checkIn, checkingsGridXY);

    JLabel checkOut = new JLabel("Check-out");
    checkOut.setFont(labelSmall);
    checkingsGridXY.gridx = 0;
    checkingsGridXY.gridy = 1;
    checkingsGridXY.fill  = GridBagConstraints.HORIZONTAL;
    checkingsGridXY.weightx = 1;
    CheckingsPanel.add(checkOut, checkingsGridXY);

    JLabel checkInDate = new JLabel(leftPanelCheckIn);
    checkInDate.setFont(labelMedium);
    checkingsGridXY.gridx = 1;
    checkingsGridXY.gridy = 0;
    checkingsGridXY.fill  = GridBagConstraints.HORIZONTAL;
    checkingsGridXY.weightx = 0;
    CheckingsPanel.add(checkInDate, checkingsGridXY);

    JLabel checkOutDate = new JLabel(leftPanelCheckOut);
    checkOutDate.setFont(labelMedium);
    checkingsGridXY.gridx = 1;
    checkingsGridXY.gridy = 1;
    checkingsGridXY.fill  = GridBagConstraints.HORIZONTAL;
    checkingsGridXY.weightx = 0;
    CheckingsPanel.add(checkOutDate, checkingsGridXY);
    
    CheckingsPanel.setMaximumSize(new Dimension(320, CheckingsPanel.getPreferredSize().height));

    JLabel roomDetailsTitle = new JLabel("Room details");
    roomDetailsTitle.setFont(h6Font);
    roomDetailsTitle.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 0));

    JTextArea roomDetails = new JTextArea(leftPanelRoomDetails);
    roomDetails.setFont(labelSmall);
    roomDetails.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    // Here for jumping the line
    roomDetails.setLineWrap(true);
    roomDetails.setWrapStyleWord(true);
    roomDetails.setAlignmentX(0.0f);
    roomDetails.setMaximumSize(new Dimension(roomDetails.getMaximumSize().width, roomDetails.getPreferredSize().height));

    JLabel roomFeaturesTitle = new JLabel("Features");
    roomFeaturesTitle.setFont(h6Font);
    roomFeaturesTitle.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 0));

    JTextArea roomFeatures = new JTextArea(leftPanelRoomFeatures);
    roomFeatures.setFont(labelSmall);
    roomFeatures.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    // Here for jumping the line
    roomFeatures.setLineWrap(true);
    roomFeatures.setWrapStyleWord(true);
    roomFeatures.setAlignmentX(0.0f);
    roomFeatures.setMaximumSize(new Dimension(roomFeatures.getMaximumSize().width, roomFeatures.getPreferredSize().height));

    
    // totalPriceAndCancelPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 20));
    JPanel totalPriceAndCancelPanel = new JPanel(new GridBagLayout());
    totalPriceAndCancelPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    totalPriceAndCancelPanel.setAlignmentX(0.0f);
    totalPriceAndCancelPanel.setBackground(new Color(0, 0, 0, 0));
    GridBagConstraints totalPriceAndCancelPanelGridXY = new GridBagConstraints();

    JLabel totalPrice = new JLabel(totalPriceLeftPanel.toString()+"$");
    totalPrice.setFont(h4Font);
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
    cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    cancelButton.setBorder(1, new Color(0xFC6222) );
    cancelButton.setBorderRadius(12);
    cancelButton.setPreferredSize(new Dimension(75, 50));
    cancelButton.setEffectColor(new Color(0xFC6222));
    cancelButton.setForeground(new Color(0xFC6222));
    cancelButton.setText("Cancel");
    cancelButton.setFont(labelMedium);
    cancelButton.setFocusable(false);
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


    // ADDING COMPONENTS TO SUMMARY PANEL
    cancellationPanel.add(hotelImage);
    cancellationPanel.add(roomType);
    cancellationPanel.add(CheckingsPanel);
    cancellationPanel.add(roomDetailsTitle);
    cancellationPanel.add(roomDetails);
    cancellationPanel.add(roomFeaturesTitle);
    cancellationPanel.add(roomFeatures);
    cancellationPanel.add(totalPriceAndCancelPanel);
    // cancellationPanel.add(Box.createVerticalGlue());
    // cancellationPanel.add(cancelPanel);



    // LEFT SIDE PANEL
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    
    GridBagConstraints gbcDates = new GridBagConstraints();
    
    JPanel datesPanel = new JPanel();
    datesPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
    datesPanel.setLayout(new GridBagLayout());
    
    JLabel startDate = new JLabel("Choose your starting date");
    startDate.setFont(labelSmall);
    gbcDates.gridx = 0;
    gbcDates.gridy = 0;
    gbcDates.fill  = GridBagConstraints.HORIZONTAL;
    gbcDates.weightx = 1;
    datesPanel.add(startDate, gbcDates);

    JLabel endDate = new JLabel("Choose your ending date");
    endDate.setFont(labelSmall);
    gbcDates.gridx = 1;
    gbcDates.gridy = 0;
    gbcDates.fill  = GridBagConstraints.HORIZONTAL;
    gbcDates.weightx = 1;
    datesPanel.add(endDate, gbcDates);

    DatePickerSettings datePickerSettings1 = new DatePickerSettings();
    datePickerSettings1.setFormatForDatesBeforeCommonEra("dd.MM.yyyy");
    datePickerSettings1.setFormatForDatesCommonEra("dd.MM.yyyy");


    startDatePicker = new DatePicker(datePickerSettings1);
    gbcDates.gridx = 0;
    gbcDates.gridy = 2;
    gbcDates.fill  = GridBagConstraints.HORIZONTAL;
    gbcDates.weightx = 1;
    datesPanel.add(startDatePicker, gbcDates);
    startDatePicker.setDateToToday(); 

    DatePickerSettings datePickerSettings2 = new DatePickerSettings();
    datePickerSettings2.setFormatForDatesBeforeCommonEra("dd.MM.yyyy");
    datePickerSettings2.setFormatForDatesCommonEra("dd.MM.yyyy");

    endDatePicker = new DatePicker(datePickerSettings2);
    gbcDates.gridx = 1;
    gbcDates.gridy = 2;
    gbcDates.fill  = GridBagConstraints.HORIZONTAL;
    gbcDates.weightx = 1;
    datesPanel.add(endDatePicker, gbcDates);
    endDatePicker.setDateToToday(); 

    datesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
    datesPanel.setPreferredSize(new Dimension(500, datesPanel.getPreferredSize().height));
    datesPanel.setMaximumSize(new Dimension(500, datesPanel.getPreferredSize().height));


    JLabel payementLabel = new JLabel("Payment method");
    payementLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
    payementLabel.setFont(h4Font);
    

    JPanel paymentOptionsContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
    Border padding1  = BorderFactory.createEmptyBorder(10, 15, 10, 15);
  

    String[] cardsType = {"mastercard","visa", "paypal"};
    for (int i = 0; i < cardsType.length; i++){
      int cardNumber = i;
      CustomButton card = new CustomButton();
      card.setIcon(new ImageIcon(ImgUtil.resizeImage("assets/"+cardsType[cardNumber]+".png", 40)));
      card.setFocusable(false);
      card.setBorder(padding1);
      card.setBorder(1, cardNumber == 0 ? new Color(0x1971C2) : new Color(0 , 0, 0, 0) );
      card.setBorderRadius(12);
      card.setPreferredSize(new Dimension(75, 50));
      card.setEffectColor(new Color(25, 113, 194));
      card.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
          for (Component btn : paymentOptionsContainer.getComponents()) {
            if (btn == card) {
                ((CustomButton) btn).setBorder(1,new Color(0x1971C2));
                btn.repaint();
            } else {
                ((CustomButton) btn).setBorder(1,new Color(0 , 0, 0, 0) );
                btn.repaint();
            }
        }
        }     
      });
      paymentOptionsContainer.add(card);
    }

    
    Border margin  = BorderFactory.createEmptyBorder(0, 15, 0, 0);
    paymentOptionsContainer.setBorder(margin);
    paymentOptionsContainer.setMaximumSize(new Dimension(paymentOptionsContainer.getMaximumSize().width, paymentOptionsContainer.getPreferredSize().height));


    JPanel paymentPanel = new JPanel();
    paymentPanel.setLayout(new GridBagLayout());
    
    
    JLabel cardholderLabel = new JLabel("Cardholder name");
    cardholderLabel.setFont(labelSmall);
    GridBagConstraints gbcPayment = new GridBagConstraints();
    gbcPayment.gridx = 0;
    gbcPayment.gridy = 0;
    gbcPayment.fill  = GridBagConstraints.HORIZONTAL;
    gbcPayment.weightx = 1;
    gbcPayment.insets = new Insets(0, 0, 3, 0);
    paymentPanel.add(cardholderLabel, gbcPayment);

    
    CustomField cardholderFeild = new CustomField();
    gbcPayment.gridx = 0;
    gbcPayment.gridy = 1;
    gbcPayment.fill  = GridBagConstraints.HORIZONTAL;
    gbcPayment.weightx = 1;
    gbcPayment.insets = new Insets(0, 0, 10, 0);
    paymentPanel.add(cardholderFeild, gbcPayment);

    JLabel CardNumberLabel = new JLabel("Card number");
    CardNumberLabel.setFont(labelSmall);
    
    gbcPayment.gridx = 0;
    gbcPayment.gridy = 3;
    gbcPayment.fill  = GridBagConstraints.HORIZONTAL;
    gbcPayment.weightx = 1;
    gbcPayment.insets = new Insets(0, 0, 3, 0);
    paymentPanel.add(CardNumberLabel, gbcPayment);

    
    CustomField cardNumberFeild = new CustomField();
    gbcPayment.gridx = 0;
    gbcPayment.gridy = 4;
    gbcPayment.fill  = GridBagConstraints.HORIZONTAL;
    gbcPayment.weightx = 1;
    gbcPayment.insets = new Insets(0, 0, 0, 5);
    paymentPanel.add(cardNumberFeild, gbcPayment);

    JLabel dateExpLabel = new JLabel("Date");
    dateExpLabel.setFont(labelSmall);
    gbcPayment.gridx = 1;
    gbcPayment.gridy = 3;
    gbcPayment.fill  = GridBagConstraints.HORIZONTAL;
    gbcPayment.weightx = 1;
    gbcPayment.insets = new Insets(0, 0, 3, 0);
    paymentPanel.add(dateExpLabel, gbcPayment);

    
    CustomField dateExpFeild = new CustomField();
    gbcPayment.gridx = 1;
    gbcPayment.gridy = 4;
    gbcPayment.fill  = GridBagConstraints.HORIZONTAL;
    gbcPayment.weightx = 1;
    gbcPayment.insets = new Insets(0, 0, 0, 5);
    paymentPanel.add(dateExpFeild, gbcPayment);

    JLabel ccvLabel = new JLabel("CCV");
    ccvLabel.setFont(labelSmall);
    gbcPayment.gridx = 2;
    gbcPayment.gridy = 3;
    gbcPayment.fill  = GridBagConstraints.HORIZONTAL;
    gbcPayment.weightx = 1;
    gbcPayment.insets = new Insets(0, 0, 3, 0);
    ImageIcon infoIcon = new ImageIcon(ImgUtil.resizeImage("assets/info.png", 15));
    ccvLabel.setHorizontalTextPosition(JLabel.LEFT);
    ccvLabel.setIcon(infoIcon);
    ccvLabel.setToolTipText("<html>CCV is the last 3 digits on<br> the back of your credit card.</html>");
    paymentPanel.add(ccvLabel, gbcPayment);

    
    CustomField ccvFeild = new CustomField();
    gbcPayment.gridx = 2;
    gbcPayment.gridy = 4;
    gbcPayment.fill  = GridBagConstraints.HORIZONTAL;
    gbcPayment.weightx = 1;
    gbcPayment.insets = new Insets(0, 0, 0, 0);
    paymentPanel.add(ccvFeild, gbcPayment);

    JLabel warningLabel = new JLabel("Credit card payments may take up 24h to be processed.");
    warningLabel.setFont(labelSmall);
    gbcPayment.gridx = 0;
    gbcPayment.gridy = 5;
    gbcPayment.fill  = GridBagConstraints.HORIZONTAL;
    gbcPayment.weightx = 1;
    gbcPayment.gridwidth = 3;
    gbcPayment.insets = new Insets(20, 0, 10, 0);
    ImageIcon infoWarning = new ImageIcon(ImgUtil.resizeImage("assets/info-warning.png", 15));
    warningLabel.setIcon(infoWarning);
    // warningLabel.setToolTipText("CCV is the last 3 digits on the back of your credit card.");
    paymentPanel.add(warningLabel, gbcPayment);

    CustomPanel cancellationPolicyPanel = new CustomPanel();
    cancellationPolicyPanel.setBackground(new Color(255, 255, 255));
    cancellationPolicyPanel.setRoundAll(12);
    cancellationPolicyPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
    JLabel text1 = new JLabel();
    text1.setFont(labelSmall);
    text1.setText("<html><b>Cancellation policy</b><br>Free Cancellation before <b>May 30</b><br>After that, the cancellation is non-refundable. <b><u>Learn more</u></b></html>");
    cancellationPolicyPanel.add(text1);

    gbcPayment.gridx = 0;
    gbcPayment.gridy = 6;
    gbcPayment.fill  = GridBagConstraints.HORIZONTAL;
    gbcPayment.weightx = 1;
    gbcPayment.gridwidth = 3;
    gbcPayment.insets = new Insets(0, 0, 0, 0);

    cancellationPolicyPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    paymentPanel.add(cancellationPolicyPanel, gbcPayment);

    
    




    paymentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
    paymentPanel.setPreferredSize(new Dimension(450, paymentPanel.getPreferredSize().height));
    paymentPanel.setMaximumSize(new Dimension(450, paymentPanel.getPreferredSize().height));


    // ADDING COMPONENTS TO MAINPANEL 
    mainPanel.add(leftJustify(datesPanel));
    mainPanel.add(leftJustify(payementLabel));
    mainPanel.add(leftJustify(paymentOptionsContainer));
    mainPanel.add(leftJustify(paymentPanel));











    // ADDING PANELES TO FRAME
    frame.add(headerPanel, BorderLayout.NORTH);
    frame.add(mainPanel, BorderLayout.CENTER);
    frame.add(cancellationPanel, BorderLayout.EAST);



    // ADDING LISTENERS 
    backButton.addActionListener(this);
    startDatePicker.addDateChangeListener(this);
    endDatePicker.addDateChangeListener(this);

    // TO AVOID BUGS
    frame.setVisible(true);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == backButton){
      System.out.println("back icon clicked");
    }
  }



  @Override
  public void dateChanged(DateChangeEvent e) {
    LocalDate firstDate = startDatePicker.getDate();
    LocalDate secondDate = endDatePicker.getDate();
    long diff = ChronoUnit.DAYS.between(firstDate, secondDate);

    if(diff >= 0){
      System.out.println(diff);
    }else{
      System.out.println("invalide");
    }

  }




 


}