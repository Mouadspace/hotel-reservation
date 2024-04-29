package ui;



import javax.swing.JLabel;

import mswing.CustomFrame;

public class Home{
  CustomFrame frame;
  


  
  public Home(){
    frame = new CustomFrame();
    frame.setSize(800,580);
    frame.setLocationRelativeTo(null);

    // HOME --------------------------------------------------
    JLabel foo = new JLabel("Home Screen ...");
    frame.add(foo);
    //--------------------------------------------------------
    




    // TO AVOID BUGS
    frame.setVisible(true);
  }
}