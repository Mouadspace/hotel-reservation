package ui;

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

import com.mysql.cj.xdevapi.Client;

import constants.COLORS;
import constants.FONTS;
import model.User;
import mswing.CustomButton;
import utils.navigation.Screen;


public class Register extends Screen implements ActionListener, MouseListener{

  private JTextField loginField;
  private JTextField passField;
  private CustomButton signupButton;
  private JLabel emailErr,passwordErr;
  private JLabel linkLabel;
  private User client;


  public Register(User client) throws FontFormatException, IOException{
    initComponents();
    this.client = client;
    
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
          boolean userExist = client.checkUser(email) ;
          if (!userExist) {
            client.saveUserToDb(email, pass);
            client.setUser(client.getUserIDFromDB(email), email, pass);
            loginField.setText("");
            passField.setText("");
            emailErr.setVisible(false);
            passwordErr.setVisible(false);
            navigateTo("/home");
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
    setLayout(new BorderLayout());


    // FONTS : 
    FONTS font = new FONTS();
    



    JPanel topPanel = new JPanel();
    topPanel.setBackground(COLORS.surface);
    topPanel.setPreferredSize(new Dimension(100,220));
    topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));

    JLabel signupLabel = new JLabel("Sign up");
    signupLabel.setFont(font.getH4());
    signupLabel.setForeground(COLORS.primary);
    signupLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    setMargin(signupLabel, 60, 0, 0, 0);
    
    JLabel joinLabel = new JLabel("Join the community today!");
    joinLabel.setFont(font.getLabel());
    joinLabel.setForeground(COLORS.grey);
    joinLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    setMargin(joinLabel, 0, 0, 25, 0);

        
    CustomButton googleSignupButton = new CustomButton();    
    googleSignupButton.setBackground(COLORS.primary);
    googleSignupButton.setForeground(Color.WHITE);
    googleSignupButton.setText("Use Google Account");
    googleSignupButton.setFocusable(false);
    googleSignupButton.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));
    googleSignupButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    ImageIcon icon = new ImageIcon("assets/google-logo.png");   
    googleSignupButton.setIcon(icon);
    googleSignupButton.setIconTextGap(10);

    JLabel orLabel = new JLabel("or");
    orLabel.setFont(font.getLabel());
    orLabel.setForeground(COLORS.grey);
    orLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    setMargin(orLabel, 25, 0, 25, 0);


    JPanel middlePanel = new JPanel();
    middlePanel.setBackground(COLORS.surface);


    JPanel middle = new JPanel();
    middle.setAlignmentX(JPanel.LEFT_ALIGNMENT);
    middle.setPreferredSize(new Dimension(300, 220));
    middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
    middle.setBackground(COLORS.surface);
    setMargin(middle, 20, 0, 0, 0);

    

    JLabel loginLabel = new JLabel("Email");
    loginLabel.setFont(font.getLabelBold());
    loginLabel.setForeground(COLORS.grey);


    loginField = new JTextField();
    loginField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,COLORS.primary));



    emailErr = new JLabel("not an email");
    emailErr.setFont(font.getLabel());
    emailErr.setForeground(Color.red);
    emailErr.setVisible(false);

    JLabel passLabel = new JLabel("Password");
    passLabel.setFont(font.getLabelBold());
    passLabel.setForeground(COLORS.grey);
    
    passField = new JTextField();
    passField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,COLORS.primary));

    passwordErr = new JLabel("weak password");
    passwordErr.setFont(font.getLabel());
    passwordErr.setForeground(Color.red);
    passwordErr.setVisible(false);
    
    

    signupButton = new CustomButton();    
    signupButton.setBackground(COLORS.primary);
    signupButton.setForeground(Color.WHITE);
    signupButton.setText("Sign up");
    signupButton.setFocusable(false);
    signupButton.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));
    
    signupButton.addActionListener(this);
    JPanel helperPanel = new JPanel();
    helperPanel.setLayout(new GridLayout());
    helperPanel.add(signupButton);

    JLabel bLabel = new JLabel("Already a member?");
    bLabel.setFont(font.getLabelBold());
    bLabel.setForeground(COLORS.grey);

    linkLabel = new JLabel("Sign in");
    linkLabel.setFont(font.getLabelBold());
    linkLabel.setForeground(COLORS.primary);
    linkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    linkLabel.addMouseListener(this);
    


    JPanel bottomPanel = new JPanel();
    bottomPanel.setBackground(COLORS.surface);
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
    add(topPanel,BorderLayout.NORTH);
    add(middlePanel,BorderLayout.CENTER);
    add(bottomPanel,BorderLayout.SOUTH);
    

  }

  @Override
  public void mouseClicked(java.awt.event.MouseEvent e) {
    if (e.getSource() == linkLabel){
      navigateTo("/login");
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
