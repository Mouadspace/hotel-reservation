package mswing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Room;

public class CustomAdminRoomManagement extends JPanel {
    public CustomAdminRoomManagement(Room room, JPanel cards) throws IOException, FontFormatException
    {
        // Import font file
        File font_file = new File("Poppins-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        
        // Setting up the basic properties
        setBounds(0, 0, 550, 550);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Add JLabel for title
        JLabel title = new JLabel("< Back");
        title.setFont(font.deriveFont(25f));
        title.setForeground(Color.BLACK);
        title.setOpaque(false);
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setMaximumSize(new Dimension(40, 20));
        title.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(5, 15, 0, 0), // Add left margin of 10px
            title.getBorder()
        ));
        title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                title.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                title.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Action for Option clicked
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, "Room Availability");
            }
        });

        add(title, BorderLayout.NORTH);




        JLabel placeholder = new JLabel(Integer.toString(room.getRoomID()) + " " + room.getTitle());
        placeholder.setFont(font.deriveFont(25f));
        placeholder.setForeground(Color.BLACK);
        placeholder.setOpaque(false);
        placeholder.setHorizontalAlignment(JLabel.LEFT);
        placeholder.setMaximumSize(new Dimension(40, 20));
        placeholder.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(5, 15, 0, 0), // Add left margin of 10px
            placeholder.getBorder()
        ));

        add(placeholder, BorderLayout.CENTER);
    }
}
