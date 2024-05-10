package utils;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImgUtil {
  public static Image resizeImage(String imagePath, int width, int height) {
    ImageIcon icon = new ImageIcon(imagePath);
    Image image = icon.getImage();
    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return scaledImage;
  }

  public static Image resizeImage(String imagePath, double scale) {
    ImageIcon icon = new ImageIcon(imagePath);
    Image image = icon.getImage();
    int width = (int) (image.getWidth(null) * scale);
    int height = (int) (image.getHeight(null) * scale);
    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return scaledImage;
  } 
  public static Image resizeImage(String imagePath, int width) {
    ImageIcon icon = new ImageIcon(imagePath);
    Image image = icon.getImage();
    int imageWidth = image.getWidth(null);
    int height = (int) (image.getHeight(null) * width) / imageWidth;
    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return scaledImage;
  }
  public static Image resizeImage(Image image, int width) {
    int imageWidth = image.getWidth(null);
    int height = (int) (image.getHeight(null) * width) / imageWidth;
    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return scaledImage;
  } 
  public static Image resizeImage(Image image, int width, int height) {
    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return scaledImage;
  } 

  public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
    int w = image.getWidth();
    int h = image.getHeight();
    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2 = output.createGraphics();
    g2.setComposite(AlphaComposite.Src);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.WHITE);
    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
    g2.setComposite(AlphaComposite.SrcAtop);
    g2.drawImage(image, 0, 0, null);
    
    g2.dispose();
    
    return output;
}

  public static Image makeRounedImage(String imagePath,  int cornerRadius, int width) throws IOException{
    File file = new File(imagePath);
    BufferedImage bufferedImage = ImageIO.read(file);
    bufferedImage = makeRoundedCorner(bufferedImage, ((cornerRadius * bufferedImage.getWidth()) / width ));
    Image image = new ImageIcon(bufferedImage).getImage();
    return resizeImage(image, width);
  }
  public static Image makeRounedImage(String imagePath,  int cornerRadius, int width, int height) throws IOException{
    File file = new File(imagePath);
    BufferedImage bufferedImage = ImageIO.read(file);
    bufferedImage = makeRoundedCorner(bufferedImage, ((cornerRadius * bufferedImage.getWidth()) / width ));
    Image image = new ImageIcon(bufferedImage).getImage();
    return resizeImage(image, width, height);
  }
  
}
