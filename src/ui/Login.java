package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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

import constants.COLORS;
import constants.FONTS;
import model.User;
import model.Admin;
import mswing.CustomButton;
import utils.navigation.Screen;


public class Login extends Screen implements ActionListener, MouseListener{

  private JTextField loginField;
  private JTextField passField;
  private CustomButton loginButton;
  private JLabel authErr;
  private JLabel linkLabel;

  private User user ;

  public Login() throws FontFormatException, IOException{
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
      if(event.getSource() == loginButton ){
      try {
        String email = loginField.getText();
        String pass  = passField.getText();

        boolean userExist = user.checkUser(email,pass) ;
        if (userExist) 
        {
          loginField.setText("");
          passField.setText("");
          authErr.setVisible(false);
          //setUser
          navigateTo("/home");
        }
        else if (Admin.checkAdmin(email, pass))
        {
          loginField.setText("");
          passField.setText("");
          authErr.setVisible(false);
          navigateTo("/admin");
        }
        else
        {
          authErr.setText("username or password incorrect");
          authErr.setVisible(true);
        }
              
      } catch (SQLException e) {
          e.printStackTrace();
      } 

  }
  }


  public void initComponents() throws FontFormatException, IOException{
    setLayout(new BorderLayout());

    // FONTS : 
    FONTS font = new FONTS();
  

    JPanel topPanel = new JPanel();
    topPanel.setBackground(COLORS.surface);
    topPanel.setPreferredSize(new Dimension(100,220));
    topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));

    JLabel signupLabel = new JLabel("Log in");
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



    JLabel passLabel = new JLabel("Password");
    passLabel.setFont(font.getLabelBold());
    passLabel.setForeground(COLORS.grey);
    
    passField = new JTextField();
    passField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,COLORS.primary));

    authErr = new JLabel("");
    authErr.setFont(font.getLabel());
    authErr.setForeground(Color.red);
    authErr.setVisible(false);
    
    
    loginButton = new CustomButton();    
    loginButton.setBackground(COLORS.primary);
    loginButton.setForeground(Color.WHITE);
    loginButton.setText("log in");
    loginButton.setFocusable(false);
    loginButton.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));
    loginButton.addActionListener(this);
    JPanel helperPanel = new JPanel();
    helperPanel.setLayout(new GridLayout());
    helperPanel.add(loginButton);


    JLabel bLabel = new JLabel("Don't have an Account?");
    bLabel.setFont(font.getLabelBold());
    bLabel.setForeground(COLORS.grey);

    linkLabel = new JLabel("Sign up");
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
    middle.add(Box.createRigidArea(new Dimension(10, 25)));
    middle.add(leftJustify(passLabel));
    middle.add(passField);
    middle.add(leftJustify(authErr));
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
  public void mouseClicked(MouseEvent e) {
    if (e.getSource() == linkLabel){
      navigateTo("/register");
    }
  }

  // Unimplemented methods ----------------------------------
  @Override
  public void mouseEntered(MouseEvent arg0) {
  }
  @Override
  public void mouseExited(MouseEvent arg0) {
  }
  @Override
  public void mousePressed(MouseEvent arg0) {
  }
  @Override
  public void mouseReleased(MouseEvent arg0) {
  }
// ----------------------------------------------------------

  
}
