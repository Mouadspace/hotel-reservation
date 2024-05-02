package utils;


import java.awt.Image;
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
  
}
