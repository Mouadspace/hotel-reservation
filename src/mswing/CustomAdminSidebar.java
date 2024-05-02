package mswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class CustomAdminSidebar extends JPanel{
    public CustomAdminSidebar() throws IOException, FontFormatException
    {
        // Import font file
        File font_file = new File("Poppins-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        
        // Setting up the basic properties
        setBounds(0, 0, 200, 550);
        setLayout(new BorderLayout());

        // Loading the icon 
        BufferedImage hotelOriginalImage = ImageIO.read(new File("assets/hotel2.png"));
        Image HotelresizedImage = hotelOriginalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon HotelresizedIcon = new ImageIcon(HotelresizedImage);

        // Set 5 px margin all around
        JPanel borderWest = new JPanel();
        borderWest.setPreferredSize(new Dimension(5, 550));
        JPanel borderEast = new JPanel();
        borderEast.setPreferredSize(new Dimension(5, 550));
        JPanel borderNorth = new JPanel();
        borderNorth.setPreferredSize(new Dimension(200, 5));
        JPanel borderSouth = new JPanel();
        borderSouth.setPreferredSize(new Dimension(200, 5));
        add(borderWest, BorderLayout.WEST);
        add(borderEast, BorderLayout.EAST);
        add(borderNorth, BorderLayout.NORTH);
        add(borderSouth, BorderLayout.SOUTH);

        // Add a JPanel for the main content in the center
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());
        add(mainContent, BorderLayout.CENTER);

        // icon:
        JPanel iconPanel = new JPanel();
        iconPanel.setPreferredSize(new Dimension(40, 40));
        JLabel image = new JLabel(HotelresizedIcon);
        iconPanel.add(image);
        mainContent.add(iconPanel, BorderLayout.NORTH);
        setBackground(Color.black);
    }
}
