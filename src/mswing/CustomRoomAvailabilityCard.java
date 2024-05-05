package mswing;

import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CustomRoomAvailabilityCard extends _CustomRoundedPanel{
    private float legendTextSize = 18f;
    private int circleSize = 12;
    public CustomRoomAvailabilityCard(String building) throws IOException, FontFormatException
    {

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

        setBackground(new Color(240, 240, 240));
        // Create a panel for the title and occupied indicator
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setOpaque(false);
        titlePanel.add(title);

        // Create a panel for the occupied indicator
        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        legendPanel.setOpaque(false);

        JLabel greenCircle = new JLabel("<html><span style='font-size: " + circleSize + "px;'>&#x2B24;</span></html>"); // Unicode for a filled circle
        greenCircle.setForeground(Color.GREEN);
        legendPanel.add(greenCircle);
        JLabel freeLabel = new JLabel("Open  ");
        freeLabel.setFont(font.deriveFont(legendTextSize));
        legendPanel.add(freeLabel);

        // Create a red circle
        JLabel redCircle = new JLabel("<html><span style='font-size: " + circleSize + "px;'>&#x2B24;</span></html>"); // Unicode for a filled circle
        redCircle.setForeground(Color.RED);
        // Add the red circle and the text "occupied"
        legendPanel.add(redCircle);
        JLabel occupiedLabel = new JLabel("Occupied");
        occupiedLabel.setFont(font.deriveFont(legendTextSize));
        legendPanel.add(occupiedLabel);

        titlePanel.add(legendPanel);
        
        setBackground(new Color(240, 240, 240));
        
        // Add the title panel to the main panel
        add(titlePanel, BorderLayout.NORTH);
    }
}
