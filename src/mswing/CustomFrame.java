package mswing;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CustomFrame extends JFrame{

  private void initialize(){
    
    setSize(800,550);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(new Color(0xFBFBFB));
    setIconImage(new ImageIcon("assets/hotel2.png").getImage());
    // setUndecorated(true);

    setVisible(true);
  }

  public CustomFrame(){
    super();
    initialize();
    
  }


}
