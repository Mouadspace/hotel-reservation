package mswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.Building;

public class CustomAdminRoomAvailability extends JPanel {
    public CustomAdminRoomAvailability() throws IOException, FontFormatException, SQLException
    {
        // Import font file
        File font_file = new File("Poppins-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        
        // Setting up the basic properties
        setBounds(0, 0, 550, 550);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Add JLabel for title
        JLabel title = new JLabel("Room Availability");
        title.setFont(font.deriveFont(40f));
        title.setForeground(Color.BLACK);
        title.setOpaque(false);
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(0, 15, 0, 0), // Add left margin of 10px
            title.getBorder()
        ));
        add(title, BorderLayout.NORTH);
        
        // Setting up a container where we will have all of the content
        JPanel mainContentContainer = new JPanel();
        // Setting basic properties of container
        mainContentContainer.setLayout(new BoxLayout(mainContentContainer, BoxLayout.Y_AXIS));
        mainContentContainer.setOpaque(false);

        // Getting panels from MySQL server and adding them to the array
        ArrayList<String> buildings = Building.GetBuildings();

        // Creating the panels
        for (String buildingName : buildings)
        {
            mainContentContainer.add(new CustomRoomAvailabilityCard(buildingName));
            mainContentContainer.add(Box.createVerticalStrut(10));
        }

        // Wrap the mainContentContainer in a JScrollPane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(mainContentContainer);
        scrollPane.getViewport().setBackground(Color.WHITE); // Set background color of the viewport
        scrollPane.setViewportBorder(null); // Remove the viewport border
        scrollPane.setBorder(null); // Remove the scroll pane border
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setForeground(Color.WHITE);
        
        add(scrollPane, BorderLayout.CENTER);
    }
}
