package constants;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FONTS {
  private Font h1;
  private Font h2;
  private Font h3;
  private Font h4;
  private Font h5;
  private Font h6;  
  private Font label;
  private Font labelBold;

  public FONTS() throws FontFormatException, IOException{
    File font_file = new File("Poppins-Regular.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
    
    h1 = font.deriveFont(Font.BOLD,40f);
    h2 = font.deriveFont(Font.BOLD,36f);
    h3 = font.deriveFont(Font.BOLD,32f);
    h4 = font.deriveFont(Font.BOLD,24f);
    h5 = font.deriveFont(Font.BOLD,20f);
    h6 = font.deriveFont(Font.BOLD,16f);  
    label = font.deriveFont(12f);
    labelBold = font.deriveFont(Font.BOLD,12f); 
  }

  public Font getH1() {
    return h1;
  }

  public Font getH2() {
    return h2;
  }

  public Font getH3() {
    return h3;
  }

  public Font getH4() {
    return h4;
  }

  public Font getH5() {
    return h5;
  }

  public Font getH6() {
    return h6;
  }

  public Font getLabel() {
    return label;
  }

  public Font getLabelBold() {
    return labelBold;
  }
}
