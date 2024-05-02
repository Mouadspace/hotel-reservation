package mswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class CustomAdminSidebar extends JPanel{
    // Some global constants to facilitate manipulation of elements
    private final int iconSize = 80;
    private final int optionSpacing = 12;
    private final float fontSize = 23f;
    public CustomAdminSidebar() throws IOException, FontFormatException
    {
        // Import font file
        File font_file = new File("Poppins-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        
        // Setting up the basic properties
        setBounds(0, 0, 250, 550);
        setLayout(new BorderLayout());

        // Loading the icon 
        BufferedImage hotelOriginalImage = ImageIO.read(new File("assets/hotel2.png"));
        Image HotelresizedImage = hotelOriginalImage.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
        ImageIcon HotelresizedIcon = new ImageIcon(HotelresizedImage);

        // Set 5 px margin all around
        JPanel borderWest = new JPanel();
        borderWest.setPreferredSize(new Dimension(5, 550));
        JPanel borderEast = new JPanel();
        borderEast.setPreferredSize(new Dimension(5, 550));
        JPanel borderNorth = new JPanel();
        borderNorth.setPreferredSize(new Dimension(250, 5));
        JPanel borderSouth = new JPanel();
        borderSouth.setPreferredSize(new Dimension(250, 5));
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
        iconPanel.setPreferredSize(new Dimension(75, 75));
        JLabel image = new JLabel(HotelresizedIcon);
        iconPanel.add(image);
        mainContent.add(iconPanel, BorderLayout.NORTH);

        // Options
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        mainContent.add(optionsPanel, BorderLayout.CENTER);
        
        // Add a margin between the options
        optionsPanel.add(Box.createVerticalStrut(25));

        // Option 1
        JLabel option1 = new JLabel("Room Availability");
        option1.setFont(font.deriveFont(fontSize));
        option1.setForeground(Color.BLACK);
        option1.setOpaque(false);
        option1.setPreferredSize(new Dimension(250, 30));
        option1.setHorizontalAlignment(JLabel.LEFT);
        option1.setVerticalAlignment(JLabel.CENTER);
        option1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Action for Option 1 clicked
                System.out.println("Option 1 clicked");
            }
        });
        option1.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(0, 10, 0, 0), // Add left margin of 10px
            option1.getBorder()
        ));
        option1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        optionsPanel.add(option1);

        // Add a margin between the options
        optionsPanel.add(Box.createVerticalStrut(optionSpacing));

        // Option 2
        JLabel option2 = new JLabel("Price & Discount");
        option2.setFont(font.deriveFont(fontSize));
        option2.setForeground(Color.BLACK);
        option2.setOpaque(false);
        option2.setPreferredSize(new Dimension(250, 30));
        option2.setHorizontalAlignment(JLabel.LEFT);
        option2.setVerticalAlignment(JLabel.CENTER);
        option2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Action for Option 2 clicked
                System.out.println("Option 2 clicked");
            }
        });
        option2.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(0, 10, 0, 0), // Add left margin of 10px
            option2.getBorder()
        ));
        option2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        optionsPanel.add(option2);

        // Add a margin between the options
        optionsPanel.add(Box.createVerticalStrut(optionSpacing));

        // Option 3
        JLabel option3 = new JLabel("Client");
        option3.setFont(font.deriveFont(fontSize));
        option3.setForeground(Color.BLACK);
        option3.setOpaque(false);
        option3.setPreferredSize(new Dimension(250, 30));
        option3.setHorizontalAlignment(JLabel.LEFT);
        option3.setVerticalAlignment(JLabel.CENTER);
        option3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Action for Option 3 clicked
                System.out.println("Option 3 clicked");
            }
        });
        option3.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(0, 10, 0, 0), // Add left margin of 10px
            option3.getBorder()
        ));
        option3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        optionsPanel.add(option3);

    }
}
