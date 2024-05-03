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

public class Home implements ActionListener, DateChangeListener {
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



  public Home() throws FontFormatException, IOException{
    frame = new CustomFrame();
    frame.getContentPane().setBackground(new Color(0xF3F3F3));

    // FONTS : 
    File font_file = new File("Poppins-Regular.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
    Font h4Font = font.deriveFont(Font.BOLD,24f);
    Font h5Font = font.deriveFont(Font.BOLD,20f);
    Font h6Font = font.deriveFont(Font.BOLD,16f);

    Font labelSmall = font.deriveFont(12f);
    Font labelMedium = font.deriveFont(Font.BOLD,12f);

    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    headerPanel.setPreferredSize(new Dimension(0, 50));
    headerPanel.setBackground(new Color(0xF3F3F3));
    headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 51)));




    JPanel backIconHelper = new JPanel();
    backIconHelper.setBackground(new Color(0, 0, 0, 0));
    backIconHelper.setBorder(BorderFactory.createEmptyBorder(0 ,0, 0, 0));

    backButton = new CustomButton();
    backButton.setIcon(new ImageIcon(ImgUtil.resizeImage("assets/back.png", 20, 20)));
    backButton.setFocusable(false);
    backButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    backButton.setBorderRadius(100);
    backButton.setForeground(Color.red);

    
    // ADDING COMPONENTS TO HEADER
    backIconHelper.add(backButton);
    headerPanel.add(backIconHelper);


    // RIGHT SIDE PANEL
    JPanel summaryPanel = new JPanel();
    summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
    summaryPanel.setBackground(new Color(255, 255, 255));
    summaryPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(0, 0, 0, 51)));
    summaryPanel.setPreferredSize(new Dimension(320, 0));

    JLabel hotelImage = new JLabel();
    hotelImage.setIcon(new ImageIcon(ImgUtil.makeRounedImage("assets/connecting-rooms.jpg", 12, 280)));
    hotelImage.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
    hotelImage.setAlignmentX(0.0f);

    
    JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    statusPanel.setBackground(new Color(0, 0, 0, 0));
    JLabel statusLabel = new JLabel("available");
    statusLabel.setFont(labelSmall);
    statusLabel.setForeground(new Color(0x2B8A3E));
    statusPanel.setAlignmentX(0.0f);
    
    CustomPanel circle = new CustomPanel();
    circle.setRoundAll(100);
    circle.setPreferredSize(new Dimension(10, 10));
    circle.setBackground(new Color(0x2B8A3E));
    
    statusPanel.add(circle);
    statusPanel.add(statusLabel);
    statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
    statusPanel.setMaximumSize(new Dimension(320, statusPanel.getPreferredSize().height));

    
    JLabel reservationText = new JLabel("Your reservation summary");
    reservationText.setFont(h5Font);
    reservationText.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    
    JPanel reservationPanel = new JPanel(new GridBagLayout());
    reservationPanel.setAlignmentX(0.0f);
    reservationPanel.setBackground(new Color(0, 0, 0, 0));
    GridBagConstraints gbcResrvation = new GridBagConstraints();

    JLabel checkIn = new JLabel("Check-in");
    checkIn.setFont(labelSmall);
    gbcResrvation.gridx = 0;
    gbcResrvation.gridy = 0;
    gbcResrvation.fill  = GridBagConstraints.HORIZONTAL;
    gbcResrvation.weightx = 1;
    reservationPanel.add(checkIn, gbcResrvation);

    JLabel checkOut = new JLabel("Check-out");
    checkOut.setFont(labelSmall);
    gbcResrvation.gridx = 0;
    gbcResrvation.gridy = 1;
    gbcResrvation.fill  = GridBagConstraints.HORIZONTAL;
    gbcResrvation.weightx = 1;
    reservationPanel.add(checkOut, gbcResrvation);

    JLabel checkInDate = new JLabel("12-09-2024");
    checkInDate.setFont(labelMedium);
    gbcResrvation.gridx = 1;
    gbcResrvation.gridy = 0;
    gbcResrvation.fill  = GridBagConstraints.HORIZONTAL;
    gbcResrvation.weightx = 0;
    reservationPanel.add(checkInDate, gbcResrvation);

    JLabel checkOutDate = new JLabel("12-09-2024");
    checkOutDate.setFont(labelMedium);
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
    pricingText.setFont(h6Font);
    gbcPricing.gridx = 0;
    gbcPricing.gridy = 0;
    gbcPricing.fill  = GridBagConstraints.HORIZONTAL;
    gbcPricing.weightx = 1;
    pricingPanel.add(pricingText, gbcPricing);


    JLabel prices = new JLabel("$100 x 2 nights");
    prices.setFont(labelSmall);
    gbcPricing.gridx = 0;
    gbcPricing.gridy = 1;
    gbcPricing.fill  = GridBagConstraints.HORIZONTAL;
    gbcPricing.weightx = 1;
    gbcPricing.insets = new Insets(0, 0, 0, 0);
    pricingPanel.add(prices, gbcPricing);

    JLabel taxes = new JLabel("Taxes 10%");
    taxes.setFont(labelSmall);
    gbcPricing.gridx = 0;
    gbcPricing.gridy = 2;
    gbcPricing.fill  = GridBagConstraints.HORIZONTAL;
    gbcPricing.weightx = 1;
    pricingPanel.add(taxes, gbcPricing);

    JLabel calc = new JLabel("200$");
    calc.setFont(labelMedium);
    gbcPricing.gridx = 1;
    gbcPricing.gridy = 1;
    gbcPricing.fill  = GridBagConstraints.HORIZONTAL;
    gbcPricing.weightx = 0;
    pricingPanel.add(calc, gbcPricing);

    JLabel taxesPrice = new JLabel("20$");
    taxesPrice.setFont(labelMedium);
    gbcPricing.gridx = 1;
    gbcPricing.gridy = 2;
    gbcPricing.fill  = GridBagConstraints.HORIZONTAL;
    gbcPricing.weightx = 0;
    pricingPanel.add(taxesPrice, gbcPricing);

    JLabel totalPricesLabel = new JLabel("Total Price");
    totalPricesLabel.setFont(labelMedium);
    totalPricesLabel.setForeground(new Color(0x2B8A3E));
    gbcPricing.gridx = 0;
    gbcPricing.gridy = 3;
    gbcPricing.fill  = GridBagConstraints.HORIZONTAL;
    gbcPricing.weightx = 0;
    pricingPanel.add(totalPricesLabel, gbcPricing);

    JLabel totalPrices = new JLabel("220$");
    totalPrices.setFont(labelMedium);
    totalPrices.setForeground(new Color(0x2B8A3E));
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
    CustomButton confirmPayButton = new CustomButton();
    confirmPayButton.setBackground(new Color(0xFC6222));
    confirmPayButton.setForeground(new Color(0xFFFFFF));
    confirmPayButton.setText("Confirm & pay 200$");
    confirmPayButton.setFont(h6Font);
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
    frame.add(summaryPanel, BorderLayout.EAST);



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