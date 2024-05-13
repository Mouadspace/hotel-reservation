package mswing;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Room;

public class CustomRoomAvailabilityCard extends _CustomRoundedPanel{
    private float legendTextSize = 18f;
    private int circleSize = 12;
    private Color backgroundColor = new Color(240, 240, 240);
    private Color openColor = new Color(0x08a209);
    private Color occupiedColor = Color.RED;
    private float roomTitleFontSize = 23f;
    private int gridGap = 15;
    private int rowHeight = gridGap + 98;
    private int numberOfRoomsPerRow = 4;
    public CustomRoomAvailabilityCard(String building, JPanel cards) throws IOException, FontFormatException, SQLException
    {
        ArrayList<Room> rooms = Room.GetRoomInBuilding(building);
        if (rooms.size() == 0)
        {
            return;
        }
        else
        {
            int currHeight = (int)(34 + Math.ceil(rooms.size() / (double)numberOfRoomsPerRow) * rowHeight);
            this.SetDimensions(new Dimension(480, currHeight));
        }

        setLayout(new BorderLayout());

        File font_file = new File("Poppins-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        
        JLabel title = new JLabel("Building " + building.toUpperCase());
        title.setFont(font.deriveFont(25f));
        title.setForeground(Color.BLACK);
        title.setOpaque(false);
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(0, 15, 0, 0), // Add left margin of 10px
            title.getBorder()
        ));

        setBackground(backgroundColor);
        // Create a panel for the title and occupied indicator
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setOpaque(false);
        titlePanel.add(title);

        // Create a panel for the occupied indicator
        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        legendPanel.setOpaque(false);

        JLabel greenCircle = new JLabel("<html><span style='font-size: " + circleSize + "px;'>&#x2B24;</span></html>"); // Unicode for a filled circle
        greenCircle.setForeground(openColor);
        legendPanel.add(greenCircle);
        JLabel freeLabel = new JLabel("Open  ");
        freeLabel.setFont(font.deriveFont(legendTextSize));
        legendPanel.add(freeLabel);

        // Create a red circle
        JLabel redCircle = new JLabel("<html><span style='font-size: " + circleSize + "px;'>&#x2B24;</span></html>"); // Unicode for a filled circle
        redCircle.setForeground(occupiedColor);
        // Add the red circle and the text "occupied"
        legendPanel.add(redCircle);
        JLabel occupiedLabel = new JLabel("Occupied");
        occupiedLabel.setFont(font.deriveFont(legendTextSize));
        legendPanel.add(occupiedLabel);

        titlePanel.add(legendPanel);
        
        setBackground(backgroundColor);
        
        // Add the title panel to the main panel
        add(titlePanel, BorderLayout.NORTH);

        // Create a panel for the grid of _CustomRoundedPanel
        JPanel gridContainer = new JPanel(new BorderLayout());
        gridContainer.setOpaque(false);

        // Create a panel for the grid of _CustomRoundedPanel
        JPanel gridPanel = new JPanel(new GridLayout((int)Math.ceil(rooms.size() / (double)numberOfRoomsPerRow), 4, gridGap, gridGap));
        gridPanel.setOpaque(false);

        for (int i = 0; i < rooms.size(); i++) 
        {
            _CustomRoundedPanel roundedPanel = new _CustomRoundedPanel(); // Create small panel to diplay room
            CustomAdminRoomManagement roomCard = new CustomAdminRoomManagement(rooms.get(i), cards); // Create the interface for this room management
            cards.add(roomCard, Integer.toString(rooms.get(i).getRoomID()));

            if (rooms.get(i).IsRoomCurrentlyReserved()) // Set background color of the rounded panel
            {
                roundedPanel.setBackground(occupiedColor);
            }
            else
            {
                roundedPanel.setBackground(openColor);
            }

            // Create a label for the room name
            JLabel roomLabel = new JLabel(rooms.get(i).getTitle());
            roomLabel.setFont(roomLabel.getFont().deriveFont(roomTitleFontSize)); // Set font size to 20
            roomLabel.setForeground(Color.WHITE); // Set text color to white
            roomLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally

            roundedPanel.setLayout(new BorderLayout()); // Set layout to BorderLayout
            roundedPanel.add(roomLabel, BorderLayout.CENTER); // Add the label to the center of the rounded panel
        
            final int final_i = i; // Workaround to make i final and use it inside of a class

            // Add a MouseListener to change cursor
            roundedPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    roundedPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    roundedPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }

                
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    // Action for Option clicked
                    CardLayout cardLayout = (CardLayout) cards.getLayout();
                    cardLayout.show(cards, Integer.toString(rooms.get(final_i).getRoomID()));
                }
            });


            gridPanel.add(roundedPanel);
        }
        
        if (rooms.size() % 4 != 0)
        {
            // To avoid problem we fill the rest of the row with transparent cells 
            for (int i = 0; i < (4 - rooms.size() % 4); ++i)
            {
                _CustomRoundedPanel roundedPanel = new _CustomRoundedPanel();
                roundedPanel.setBackground(backgroundColor); // Set background color of the rounded panel
                gridPanel.add(roundedPanel);
            }
        }

        gridContainer.add(gridPanel, BorderLayout.CENTER);
        // Add margin to the left and right of the grid container
        gridContainer.setBorder(new EmptyBorder(0, 20, 10, 20));

        // Add the grid container to the main panel
        add(gridContainer, BorderLayout.CENTER);
    }
}
