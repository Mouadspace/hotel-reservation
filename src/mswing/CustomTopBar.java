package mswing;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomTopBar extends JPanel {
    public CustomTopBar() throws IOException, FontFormatException {

        // font for profile : 
        File font_file = new File("Poppins-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        Font profileFont = font.deriveFont(Font.BOLD,14f);
        
        setBounds(0, 0, 800, 50);
        setLayout(new BorderLayout());
        setBackground(Color.white);
        // For margins
        JPanel borderWest = new JPanel();
        borderWest.setPreferredSize(new Dimension(30, 50));
        JPanel borderEast = new JPanel();
        borderEast.setPreferredSize(new Dimension(45, 50));
        JPanel borderNorth = new JPanel();
        borderNorth.setPreferredSize(new Dimension(800, 5));
        JPanel borderSouth = new JPanel();
        borderSouth.setPreferredSize(new Dimension(800, 5));
        add(borderWest, BorderLayout.WEST);
        add(borderEast, BorderLayout.EAST);
        add(borderNorth, BorderLayout.NORTH);
        add(borderSouth, BorderLayout.SOUTH);
        
        // Here I'm trying to resize the images/icons to use them latter
        BufferedImage hotelOriginalImage = ImageIO.read(new File("assets/hotel2.png"));
        Image HotelresizedImage = hotelOriginalImage.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon HotelresizedIcon = new ImageIcon(HotelresizedImage);


        // The Main Content of the topbar
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());
        add(mainContent, BorderLayout.CENTER);
        // icon:
        JPanel iconPanel = new JPanel();
        iconPanel.setPreferredSize(new Dimension(40, 40));
        JLabel image = new JLabel(HotelresizedIcon);
        iconPanel.add(image);
        mainContent.add(iconPanel, BorderLayout.WEST);

        // Elements (Profile, Saved Icon, Language)
        JPanel profileSavedLanguePanel = new JPanel();

        JLabel profileLabel = new JLabel("Log out");
        profileLabel.setFont(profileFont);
        profileLabel.setPreferredSize(new Dimension(80, 40));


        profileSavedLanguePanel.setLayout(new BorderLayout());
        profileSavedLanguePanel.setPreferredSize(new Dimension(160, 40));
        profileSavedLanguePanel.add(profileLabel, BorderLayout.EAST);
    
        mainContent.add(profileSavedLanguePanel, BorderLayout.EAST);

        // Now For some Actions!!

        // Go Back Home
        image.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Define the action to perform when the label is clicked
                JOptionPane.showMessageDialog(null, "Clicked on image!");
            }
        });


        // Profile Page
        profileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Define the action to perform when the label is clicked
                JOptionPane.showMessageDialog(null, "Clicked on Profile");
            }
        });
    }
}
