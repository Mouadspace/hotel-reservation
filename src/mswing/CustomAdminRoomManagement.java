package mswing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
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

import model.Reservation;
import model.Room;
import ui.AdminHome;

public class CustomAdminRoomManagement extends JPanel {
    public CustomAdminRoomManagement(Room room, JPanel cards) throws IOException, FontFormatException, SQLException
    {
        // Import font file
        File font_file = new File("Poppins-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        
        // Setting up the basic properties
        setBounds(0, 0, 550, 550);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Create a top panel "title panel" for the back and delete buttons
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setOpaque(false);

        // Add JLabel for Back Button
        JLabel backLabel = new JLabel("< Back");
        backLabel.setFont(font.deriveFont(25f));
        backLabel.setForeground(Color.BLACK);
        backLabel.setOpaque(false);
        backLabel.setHorizontalAlignment(JLabel.LEFT);
        backLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        backLabel.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(5, 15, 0, 0), // Add left margin of 10px
            backLabel.getBorder()
        ));
        // Set behavior for hover and click
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                backLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Action for Option clicked
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, "Room Availability"); // Navigate back to the previous panel
            }
        });
        titlePanel.add(backLabel);

        // Add JLabel for Back Button
        JLabel deleteLabel = new JLabel("Delete");
        deleteLabel.setFont(font.deriveFont(25f));
        deleteLabel.setForeground(Color.RED);
        deleteLabel.setOpaque(false);
        deleteLabel.setHorizontalAlignment(JLabel.RIGHT);
        deleteLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        deleteLabel.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(5, 0, 0, 15), // Add left margin of 10px
            deleteLabel.getBorder()
        ));
        // Set behavior for hover and click
        deleteLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                deleteLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                deleteLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Action for Option clicked
                try
                {
                    room.Drop();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                AdminHome.RefreshRoomAvailability();
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, "Room Availability"); // Navigate back to the previous panel
            }
        });

        titlePanel.add(deleteLabel);

        add(titlePanel, BorderLayout.NORTH);

        // Setting up a container where we will have all of the content
        JPanel mainContentContainer = new JPanel();
        // Setting basic properties of container
        mainContentContainer.setLayout(new BorderLayout());
        mainContentContainer.setOpaque(false);

        // Add JLabel for reservation history subtitle
        JLabel RoservationHistorySubtitle = new JLabel("Reservation history of room " + room.getTitle());
        RoservationHistorySubtitle.setFont(font.deriveFont(30f));
        RoservationHistorySubtitle.setForeground(Color.BLACK);
        RoservationHistorySubtitle.setOpaque(false);
        RoservationHistorySubtitle.setHorizontalAlignment(JLabel.LEFT);
        RoservationHistorySubtitle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        RoservationHistorySubtitle.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(10, 15, 0, 0),
            RoservationHistorySubtitle.getBorder()
        ));
        mainContentContainer.add(RoservationHistorySubtitle, BorderLayout.NORTH);
        
        // Add wrapper to wrap the history
        ArrayList<Reservation> reservations = Reservation.getReservationsByRoom(room.getRoomID());
        mainContentContainer.add(Box.createVerticalStrut(10));
        if (reservations.size() == 0) // There are/were no reservations made to this room
        {
            JPanel container = new JPanel();
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
            container.setBackground(Color.WHITE);
            // Add JLabel for reservation history subtitle
            JLabel reservationHistoryContent = new JLabel("There are no reservations made to this room!");
            reservationHistoryContent.setFont(font.deriveFont(15f));
            reservationHistoryContent.setForeground(Color.BLACK);
            reservationHistoryContent.setOpaque(false);
            reservationHistoryContent.setHorizontalAlignment(JLabel.CENTER);
            reservationHistoryContent.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            container.add(reservationHistoryContent);
            mainContentContainer.add(container, BorderLayout.CENTER);
        }
        else
        {
            JPanel container = new JPanel();
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
            container.setBackground(Color.WHITE);
            for (int i = 0; i < reservations.size(); ++i)
            {
                _CustomAdminRoomReservationCard reservationCard = new _CustomAdminRoomReservationCard(reservations.get(i));
                reservationCard.setAlignmentX(Component.CENTER_ALIGNMENT);
                container.add(reservationCard);
                container.add(Box.createVerticalStrut(10));
            }
            mainContentContainer.add(container, BorderLayout.CENTER);
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
