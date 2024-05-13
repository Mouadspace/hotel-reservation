package ui;

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

import constants.COLORS;
import constants.FONTS;
import model.Room;
import model.User;
import mswing.CustomButton;
import mswing.CustomField;
import mswing.CustomPanel;
import routes.InitRoutes;
import utils.ImgUtil;
import utils.navigation.Screen;
import utils.navigation.ScreenManager;

public class Reservation extends Screen implements ActionListener, DateChangeListener {
  private CustomButton backButton;
  private DatePicker startDatePicker;
  private DatePicker endDatePicker;
  private JLabel checkInDate;
  private JLabel checkOutDate; 
  private JLabel prices;
  private JLabel calc;
  private JLabel totalPrices;
  private JLabel taxesPrice;
  private CustomButton confirmPayButton;
  private Room room;
  private User client;
  private float total;



  private JPanel leftJustify(Component component )  {
    JPanel b = new JPanel();
    b.setLayout(new BoxLayout(b, BoxLayout.X_AXIS));   
    b.add(component);
    b.add(Box.createHorizontalGlue());
    b.setMaximumSize(new Dimension(b.getMaximumSize().width, b.getPreferredSize().height));
    return b;
  }



  public Reservation(Room selectedRoom, User client) throws FontFormatException, IOException{
    
    this.client = client;
    System.out.println(client.getEmail() + " - " +client.getUserID());
    room = selectedRoom;
    total = room.getPrice();
    setLayout(new BorderLayout());
    setBackground(COLORS.background);

    // FONTS : 
    FONTS font = new FONTS();

    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    headerPanel.setPreferredSize(new Dimension(0, 50));
    headerPanel.setBackground(COLORS.background);
    headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, COLORS.lightGrey));




    JPanel backIconHelper = new JPanel();
    backIconHelper.setBackground(COLORS.transparent);
    backIconHelper.setBorder(BorderFactory.createEmptyBorder(0 ,0, 0, 0));

    backButton = new CustomButton();
    backButton.setIcon(new ImageIcon(ImgUtil.resizeImage("assets/back.png", 20, 20)));
    backButton.setFocusable(false);
    backButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    backButton.setBorderRadius(100);

    
    // ADDING COMPONENTS TO HEADER
    backIconHelper.add(backButton);
    headerPanel.add(backIconHelper);


    // RIGHT SIDE PANEL
    JPanel summaryPanel = new JPanel();
    summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
    summaryPanel.setBackground(COLORS.surface);
    summaryPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, COLORS.lightGrey));
    summaryPanel.setPreferredSize(new Dimension(320, 0));

    JLabel hotelImage = new JLabel();
    hotelImage.setIcon(new ImageIcon(ImgUtil.makeRounedImage("assets/" + room.getImage(), 12, 280)));
    hotelImage.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
    hotelImage.setAlignmentX(0.0f);

    
    JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    statusPanel.setBackground(new Color(0, 0, 0, 0));
    JLabel statusLabel = new JLabel("available");
    statusLabel.setFont(font.getLabel());
    statusLabel.setForeground(COLORS.success);
    statusPanel.setAlignmentX(0.0f);
    
    CustomPanel circle = new CustomPanel();
    circle.setRoundAll(100);
    circle.setPreferredSize(new Dimension(10, 10));
    circle.setBackground(COLORS.success);
    
    statusPanel.add(circle);
    statusPanel.add(statusLabel);
    statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
    statusPanel.setMaximumSize(new Dimension(320, statusPanel.getPreferredSize().height));

    
    JLabel reservationText = new JLabel("Your reservation summary");
    reservationText.setFont(font.getH5());
    reservationText.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    
    JPanel reservationPanel = new JPanel(new GridBagLayout());
    reservationPanel.setAlignmentX(0.0f);
    reservationPanel.setBackground(COLORS.transparent);
    GridBagConstraints gbcResrvation = new GridBagConstraints();

    JLabel checkIn = new JLabel("Check-in");
    checkIn.setFont(font.getLabel());
    gbcResrvation.gridx = 0;
    gbcResrvation.gridy = 0;
    gbcResrvation.fill  = GridBagConstraints.HORIZONTAL;
    gbcResrvation.weightx = 1;
    reservationPanel.add(checkIn, gbcResrvation);

    JLabel checkOut = new JLabel("Check-out");
    checkOut.setFont(font.getLabel());
    gbcResrvation.gridx = 0;
    gbcResrvation.gridy = 1;
    gbcResrvation.fill  = GridBagConstraints.HORIZONTAL;
    gbcResrvation.weightx = 1;
    reservationPanel.add(checkOut, gbcResrvation);

    checkInDate = new JLabel();
    checkInDate.setFont(font.getLabelBold());
    gbcResrvation.gridx = 1;
    gbcResrvation.gridy = 0;
    gbcResrvation.fill  = GridBagConstraints.HORIZONTAL;
    gbcResrvation.weightx = 0;
    reservationPanel.add(checkInDate, gbcResrvation);

    checkOutDate = new JLabel();
    checkOutDate.setFont(font.getLabelBold());
    gbcResrvation.gridx = 1;
    gbcResrvation.gridy = 1;
    gbcResrvation.fill  = GridBagConstraints.HORIZONTAL;
    gbcResrvation.weightx = 0;
    reservationPanel.add(checkOutDate, gbcResrvation);
    

    JPanel pricingPanel = new JPanel(new GridBagLayout());
    pricingPanel.setAlignmentX(0.0f);
    pricingPanel.setBackground(new Color(0, 0, 0, 0));
    GridBagConstraints gbcPricing= new GridBagConstraints();

    JLabel pricingText = new JLabel("Pricing breakdown");
    pricingText.setFont(font.getH6());
    gbcPricing.gridx = 0;
    gbcPricing.gridy = 0;
    gbcPricing.fill  = GridBagConstraints.HORIZONTAL;
    gbcPricing.weightx = 1;
    pricingPanel.add(pricingText, gbcPricing);


    prices = new JLabel((int)room.getPrice() + "$");
    prices.setFont(font.getLabel());
    gbcPricing.gridx = 0;
    gbcPricing.gridy = 1;
    gbcPricing.fill  = GridBagConstraints.HORIZONTAL;
    gbcPricing.weightx = 1;
    gbcPricing.insets = new Insets(0, 0, 0, 0);
    pricingPanel.add(prices, gbcPricing);

    JLabel taxes = new JLabel("Taxes 10%");
    taxes.setFont(font.getLabel());
    gbcPricing.gridx = 0;
    gbcPricing.gridy = 2;
    gbcPricing.fill  = GridBagConstraints.HORIZONTAL;
    gbcPricing.weightx = 1;
    pricingPanel.add(taxes, gbcPricing);

    calc = new JLabel();
    calc.setFont(font.getLabelBold());
    gbcPricing.gridx = 1;
    gbcPricing.gridy = 1;
    gbcPricing.fill  = GridBagConstraints.HORIZONTAL;
    gbcPricing.weightx = 0;
    pricingPanel.add(calc, gbcPricing);

    taxesPrice = new JLabel();
    taxesPrice.setFont(font.getLabelBold());
    gbcPricing.gridx = 1;
    gbcPricing.gridy = 2;
    gbcPricing.fill  = GridBagConstraints.HORIZONTAL;
    gbcPricing.weightx = 0;
    pricingPanel.add(taxesPrice, gbcPricing);

    JLabel totalPricesLabel = new JLabel("Total Price");
    totalPricesLabel.setFont(font.getLabelBold());
    totalPricesLabel.setForeground(COLORS.success);
    gbcPricing.gridx = 0;
    gbcPricing.gridy = 3;
    gbcPricing.fill  = GridBagConstraints.HORIZONTAL;
    gbcPricing.weightx = 0;
    pricingPanel.add(totalPricesLabel, gbcPricing);

    totalPrices = new JLabel((int)room.getPrice() + "$");
    totalPrices.setFont(font.getLabelBold());
    totalPrices.setForeground(COLORS.success);
    gbcPricing.gridx = 1;
    gbcPricing.gridy = 3;
    gbcPricing.fill  = GridBagConstraints.HORIZONTAL;
    gbcPricing.weightx = 0;
    gbcPricing.insets = new Insets(5, 0, 0, 0);
    pricingPanel.add(totalPrices, gbcPricing);

    reservationPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
    reservationPanel.setMaximumSize(new Dimension(320, reservationPanel.getPreferredSize().height));
    
    pricingPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
    pricingPanel.setMaximumSize(new Dimension(320, pricingPanel.getPreferredSize().height));
    

    JPanel helperButtom = new JPanel(new GridLayout());
    helperButtom.setBackground(new Color(0, 0, 0, 0));
    helperButtom.setAlignmentX(0.0f);
    confirmPayButton = new CustomButton();
    confirmPayButton.setBackground(COLORS.primary);
    confirmPayButton.setForeground(COLORS.surface);
    confirmPayButton.setText("Confirm & pay 200$");
    confirmPayButton.setFont(font.getH6());
    confirmPayButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    helperButtom.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
    confirmPayButton.setFocusable(false);
    helperButtom.setMaximumSize(new Dimension(320, helperButtom.getPreferredSize().height));
    helperButtom.add(confirmPayButton);


    // ADDING COMPONENTS TO SUMMARY PANEL
    summaryPanel.add(hotelImage);
    summaryPanel.add(statusPanel);
    summaryPanel.add(reservationText);
    summaryPanel.add(reservationPanel);
    summaryPanel.add(pricingPanel);
    summaryPanel.add(Box.createVerticalGlue());
    summaryPanel.add(helperButtom);



    // LEFT SIDE PANEL
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    
    GridBagConstraints gbcDates = new GridBagConstraints();
    
    JPanel datesPanel = new JPanel();
    datesPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
    datesPanel.setLayout(new GridBagLayout());
    
    JLabel startDate = new JLabel("Choose your starting date");
    startDate.setFont(font.getLabel());
    gbcDates.gridx = 0;
    gbcDates.gridy = 0;
    gbcDates.fill  = GridBagConstraints.HORIZONTAL;
    gbcDates.weightx = 1;
    datesPanel.add(startDate, gbcDates);

    JLabel endDate = new JLabel("Choose your ending date");
    endDate.setFont(font.getLabel());
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
    payementLabel.setFont(font.getH4());
    

    JPanel paymentOptionsContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
    Border padding1  = BorderFactory.createEmptyBorder(10, 15, 10, 15);
  

    String[] cardsType = {"mastercard","visa", "paypal"};
    for (int i = 0; i < cardsType.length; i++){
      int cardNumber = i;
      CustomButton card = new CustomButton();
      card.setIcon(new ImageIcon(ImgUtil.resizeImage("assets/"+cardsType[cardNumber]+".png", 40)));
      card.setFocusable(false);
      card.setBorder(padding1);
      card.setBorder(1, cardNumber == 0 ? COLORS.secondary : COLORS.transparent );
      card.setBorderRadius(12);
      card.setPreferredSize(new Dimension(75, 50));
      card.setEffectColor(COLORS.secondary);
      card.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
          for (Component btn : paymentOptionsContainer.getComponents()) {
            if (btn == card) {
                ((CustomButton) btn).setBorder(1, COLORS.secondary);
                btn.repaint();
            } else {
                ((CustomButton) btn).setBorder(1, COLORS.transparent);
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
    cardholderLabel.setFont(font.getLabel());
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
    CardNumberLabel.setFont(font.getLabel());
    
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
    dateExpLabel.setFont(font.getLabel());
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
    ccvLabel.setFont(font.getLabel());
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
    warningLabel.setFont(font.getLabel());
    gbcPayment.gridx = 0;
    gbcPayment.gridy = 5;
    gbcPayment.fill  = GridBagConstraints.HORIZONTAL;
    gbcPayment.weightx = 1;
    gbcPayment.gridwidth = 3;
    gbcPayment.insets = new Insets(20, 0, 10, 0);
    ImageIcon infoWarning = new ImageIcon(ImgUtil.resizeImage("assets/info-warning.png", 15));
    warningLabel.setIcon(infoWarning);
    paymentPanel.add(warningLabel, gbcPayment);

    CustomPanel cancellationPolicyPanel = new CustomPanel();
    cancellationPolicyPanel.setBackground(new Color(255, 255, 255));
    cancellationPolicyPanel.setRoundAll(12);
    cancellationPolicyPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
    JLabel text1 = new JLabel();
    text1.setFont(font.getLabel());
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
    add(headerPanel, BorderLayout.NORTH);
    add(mainPanel, BorderLayout.CENTER);
    add(summaryPanel, BorderLayout.EAST);



    // ADDING LISTENERS 
    backButton.addActionListener(this);
    startDatePicker.addDateChangeListener(this);
    endDatePicker.addDateChangeListener(this);
    confirmPayButton.addActionListener(this);

  }



  @Override
  public void actionPerformed(ActionEvent e) {
    ScreenManager sm =  InitRoutes.screenManager;
    if (e.getSource() == backButton){
      navigateTo("/home");
      sm.remove(this);
    }else if  (e.getSource() == confirmPayButton){
      try {
        model.Reservation resv = new model.Reservation();
        resv.saveReservationToDb(client.getUserID(), room.getRoomID(),startDatePicker.getDate(),endDatePicker.getDate(), total);
      } catch (Exception e1) {
        e1.printStackTrace();
      }

    }
  }



  @Override
  public void dateChanged(DateChangeEvent e) {
    LocalDate firstDate = startDatePicker.getDate();
    LocalDate secondDate = endDatePicker.getDate();
    long diff = ChronoUnit.DAYS.between(firstDate, secondDate);

    int price = (int) room.getPrice();
    if(diff > 0){
      confirmPayButton.setEnabled(true);
      confirmPayButton.setEffectColor(new Color(252, 255, 255));

      checkOutDate.setText(secondDate.toString());
      checkInDate.setText(firstDate.toString());
      repaint();


      total = (int) (price * diff);
      int  taxes = (int) (total * 0.1);

      prices.setText(price + "$ x "+ diff + " nights");
      calc.setText(total  + "$");
      taxesPrice.setText(taxes + "$");

      total += taxes;
      
      totalPrices.setText(total + "$");
      confirmPayButton.setText("Confirm & pay " + total + "$");
    }else{
      confirmPayButton.setText("Invalide Dates");
      confirmPayButton.setEffectColor(new Color(252, 98, 34, 80));
      confirmPayButton.setEnabled(false);
      prices.setText(price + "$");
      calc.setText("");
      taxesPrice.setText("");
      totalPrices.setText("");
    }

  }




 


}