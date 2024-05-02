package mswing;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CustomFrame extends JFrame{

  private void initialize(){
    // Define basic properties of the window
    setSize(800,550);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(new Color(0xFBFBFB));
    // Setting the icon image
    setIconImage(new ImageIcon("assets/hotel2.png").getImage());
    
    // Disabling resizing so that the window keeps the same size no matter what
    setResizable(false);
    setVisible(true);
  }

  public CustomFrame(){
    super();
    initialize();
    
  }


}
