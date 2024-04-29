package ui;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


import model.User;
import mswing.CustomButton;
import mswing.CustomFrame;


public class Register implements ActionListener, MouseListener{

  CustomFrame frame;
  JTextField loginField;
  JTextField passField;
  CustomButton signupButton;
  JLabel emailErr,passwordErr;

  User user;

  public Register() throws FontFormatException, IOException{
    initComponents();
    user = new User();
    
  }

  private void setMargin(JComponent cmp,int top, int left, int bottom, int right){
      Border border = cmp.getBorder();
      Border margin = new EmptyBorder(top,left,bottom,right);
      cmp.setBorder(new CompoundBorder(border, margin));
  }
  private Component leftJustify( JLabel label )  {
    Box  b = Box.createHorizontalBox();
    b.add( label );
    b.add( Box.createHorizontalGlue() );
    return b;
  }


  @Override
  public void actionPerformed(ActionEvent event) {
      if(event.getSource() == signupButton ){
      try {
        String email = loginField.getText();
        String pass  = passField.getText();
        boolean isEmail = isEmail(email);
        boolean isWeakPass = isWeakPassword(pass);

       if (!isEmail){
          emailErr.setText("not an email");
          emailErr.setVisible(true);
          passwordErr.setVisible(false);
        }else if (isWeakPass) {
          emailErr.setVisible(false);
          passwordErr.setVisible(true);
        }else{ 
          boolean userExist = user.checkUser(email) ;
          if (!userExist) {
            user.setUser(email, pass);
            loginField.setText("");
            passField.setText("");
            emailErr.setVisible(false);
            passwordErr.setVisible(false);
            frame.dispose();
            new Home();
            

          }else{
            emailErr.setText("email already exist");
            emailErr.setVisible(true);
            passwordErr.setVisible(false);
          }
        }         
      } catch (SQLException e) {
          e.printStackTrace();
      }

  }
  }

  public static boolean patternMatches(String expression, String regexPattern) {
    return Pattern.compile(regexPattern)
      .matcher(expression)
      .matches();
  }

  public boolean isEmail(String email){
    String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    return patternMatches(email, emailPattern);
  }
  public boolean isWeakPassword(String password){
    String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    return !patternMatches(password, passwordPattern);
  }
  


  public void initComponents() throws FontFormatException, IOException{
    frame = new CustomFrame();


    // FONTS : 
    File font_file = new File("Poppins-Regular.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
    Font h5Font = font.deriveFont(Font.BOLD,24f);
    Font labelSmall = font.deriveFont(12f);
    Font labelMedium = font.deriveFont(Font.BOLD,12f);



    JPanel topPanel = new JPanel();
    topPanel.setBackground(new Color(0xFBFBFB));
    topPanel.setPreferredSize(new Dimension(100,220));
    topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
    // topPanel.setBackground(Color.pink);

    JLabel signupLabel = new JLabel("Sign up");
    signupLabel.setFont(h5Font);
    signupLabel.setForeground(new Color(0x6A70E0));
    signupLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    setMargin(signupLabel, 60, 0, 0, 0);
    
    JLabel joinLabel = new JLabel("Join the community today!");
    joinLabel.setFont(labelSmall);
    joinLabel.setForeground(new Color(0xC7C7C7));
    joinLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    setMargin(joinLabel, 0, 0, 25, 0);

        
    CustomButton googleSignupButton = new CustomButton();    
    googleSignupButton.setBackground(new Color(0x6A70E0));
    googleSignupButton.setForeground(Color.WHITE);
    googleSignupButton.setText("Use Google Account");
    googleSignupButton.setFocusable(false);
    googleSignupButton.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));
    googleSignupButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    ImageIcon icon = new ImageIcon("assets/google-logo.png");   
    googleSignupButton.setIcon(icon);
    googleSignupButton.setIconTextGap(10);

    JLabel orLabel = new JLabel("or");
    orLabel.setFont(labelSmall);
    orLabel.setForeground(new Color(0xC7C7C7));
    orLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    setMargin(orLabel, 25, 0, 25, 0);


    JPanel middlePanel = new JPanel();
    middlePanel.setBackground(new Color(0xFBFBFB));


    JPanel middle = new JPanel();
    middle.setAlignmentX(JPanel.LEFT_ALIGNMENT);
    middle.setPreferredSize(new Dimension(300, 220));
    middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
    middle.setBackground(new Color(0xFBFBFB));
    setMargin(middle, 20, 0, 0, 0);

    

    JLabel loginLabel = new JLabel("Email");
    loginLabel.setFont(labelMedium);
    loginLabel.setForeground(new Color(0xC7C7C7));


    loginField = new JTextField();
    loginField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,new Color(0x6A70E0)));



    emailErr = new JLabel("not an email");
    emailErr.setFont(labelSmall);
    emailErr.setForeground(Color.red);
    emailErr.setVisible(false);

    JLabel passLabel = new JLabel("Password");
    passLabel.setFont(labelMedium);
    passLabel.setForeground(new Color(0xC7C7C7));
    
    passField = new JTextField();
    passField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,new Color(0x6A70E0)));

    passwordErr = new JLabel("weak password");
    passwordErr.setFont(labelSmall);
    passwordErr.setForeground(Color.red);
    passwordErr.setVisible(false);
    
    

    signupButton = new CustomButton();    
    signupButton.setBackground(new Color(0x6A70E0));
    signupButton.setForeground(Color.WHITE);
    signupButton.setText("Sign up");
    signupButton.setFocusable(false);
    signupButton.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));
    
    signupButton.addActionListener(this);
    JPanel helperPanel = new JPanel();
    helperPanel.setLayout(new GridLayout());
    helperPanel.add(signupButton);

    JLabel bLabel = new JLabel("Already a member?");
    bLabel.setFont(labelMedium);
    bLabel.setForeground(new Color(0xC7C7C7));

    JLabel linkLabel = new JLabel("Sign in");
    linkLabel.setFont(labelMedium);
    linkLabel.setForeground(new Color(0x6A70E0));
    linkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    linkLabel.addMouseListener(this);
    


    JPanel bottomPanel = new JPanel();
    bottomPanel.setBackground(new Color(0xFBFBFB));
    bottomPanel.setPreferredSize(new Dimension(100,40));
    


    // ADDING COMPONENTS TO TOP PANEL
    topPanel.add(signupLabel);
    topPanel.add(joinLabel);
    topPanel.add(googleSignupButton);
    topPanel.add(orLabel);

    // ADDING COMPONENTS TO MIDDLE PANEL
    middle.add(leftJustify(loginLabel));
    middle.add(loginField);
    middle.add(leftJustify(emailErr));
    middle.add(Box.createRigidArea(new Dimension(10, 25)));
    middle.add(leftJustify(passLabel));
    middle.add(passField);
    middle.add(leftJustify(passwordErr));
    middle.add(Box.createRigidArea(new Dimension(10, 25)));
    middle.add(helperPanel);
    
    middlePanel.add(middle);

    // ADDING COMPONENTS TO BOTTOM PANEL
    bottomPanel.add(bLabel);
    bottomPanel.add(linkLabel);


    // ADDING COMPONENTS TO FRAME 
    frame.add(topPanel,BorderLayout.NORTH);
    frame.add(middlePanel,BorderLayout.CENTER);
    frame.add(bottomPanel,BorderLayout.SOUTH);
    

    // TO AVOID BUGS
    frame.setVisible(true);
  }

  @Override
  public void mouseClicked(java.awt.event.MouseEvent arg0) {
    frame.dispose();
    try {
      new Login();
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }
  }

  // Unimplemented method ----------------------------------
  @Override
  public void mouseEntered(java.awt.event.MouseEvent arg0) {
  }
  @Override
  public void mouseExited(java.awt.event.MouseEvent arg0) {
  }
  @Override
  public void mousePressed(java.awt.event.MouseEvent arg0) {
  }
  @Override
  public void mouseReleased(java.awt.event.MouseEvent arg0) {
  }
// ----------------------------------------------------------
  
}
