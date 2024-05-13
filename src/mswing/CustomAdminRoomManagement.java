package mswing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Reservation;
import model.Room;
import ui.AdminHome;

public class CustomAdminRoomManagement extends JPanel {
    private Color submitButtonColor = new Color(0x08a209);
    Room room;
    JPanel cards;
    public CustomAdminRoomManagement(Room room, JPanel cards) throws IOException, FontFormatException, SQLException
    {
        this.room = room;
        this.cards = cards;
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
        
        // Setting up a global container
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setBackground(Color.WHITE);

        // Setting up a container where we will have all of the content concerning the reservation history
        JPanel reservationHistoryContainer = new JPanel();
        // Setting basic properties of container
        reservationHistoryContainer.setLayout(new BorderLayout());
        reservationHistoryContainer.setOpaque(false);

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
        reservationHistoryContainer.add(RoservationHistorySubtitle, BorderLayout.NORTH);
        
        // Add wrapper to wrap the history
        ArrayList<Reservation> reservations = Reservation.getReservationsByRoom(room.getRoomID());
        reservationHistoryContainer.add(Box.createVerticalStrut(10));
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
            reservationHistoryContainer.add(container, BorderLayout.CENTER);
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
            reservationHistoryContainer.add(container, BorderLayout.CENTER);
        }
        mainContainer.add(reservationHistoryContainer);

        // Setting up a container where we will have all of the content concerning the reservation history
        JPanel roomInfoContainer = new JPanel();
        // Setting basic properties of container
        roomInfoContainer.setLayout(new BorderLayout());
        roomInfoContainer.setOpaque(false);

        // Add JLabel for reservation history subtitle
        JLabel roomInfoSubtitle = new JLabel("Information about room " + room.getTitle());
        roomInfoSubtitle.setFont(font.deriveFont(30f));
        roomInfoSubtitle.setForeground(Color.BLACK);
        roomInfoSubtitle.setOpaque(false);
        roomInfoSubtitle.setHorizontalAlignment(JLabel.LEFT);
        roomInfoSubtitle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        roomInfoSubtitle.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(10, 15, 0, 0),
            roomInfoSubtitle.getBorder()
        ));
        roomInfoContainer.add(roomInfoSubtitle, BorderLayout.NORTH);

        // A panel to contain the grid which will allow us greater flexibility
        JPanel gridContainer = new JPanel();
        gridContainer.setBackground(Color.WHITE);
        gridContainer.setLayout(new BorderLayout());

        // Panel to hold text fields, labels, and button
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2, 10, 10)); 
        formPanel.setBackground(Color.WHITE);

        // Add text fields and labels
        String[] labels = { 
            "Room Type:", 
            "Room Price:", 
            "Room Title:", 
            "Max Members: ", 
            "Bathroom:", 
            "Bedrooms:", 
            "Description", 
            "Building" 
        };

        // Information to prefill the textfields
        String[] information = {
            room.getRoomName(),
            Float.toString(room.getPrice()),
            room.getTitle(),
            Integer.toString(room.getMembers()),
            Integer.toString(room.getBathrooms()),
            Integer.toString(room.getBedrooms()),
            room.getDescription(),
            room.getBuilding()
        };

        // Store a reference to the text fields so that we can access their values later
        ArrayList<JTextField> textFields = new ArrayList<JTextField>(); 

        for (int i = 0;i < labels.length; ++i ) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(font.deriveFont(18f));
            label.setForeground(Color.BLACK);
            formPanel.add(label);

            JTextField textField = new JTextField();
            textField.setText(information[i]);
            textField.setPreferredSize(new Dimension(200, 30)); // Set fixed width
            textField.setMargin(new Insets(0, 10, 0, 10)); // Add margin to the left and right
            textFields.add(textField);
            formPanel.add(textField);
        }

        // Add submit button
        CustomButton submitButton = new CustomButton();
        submitButton.setText("Submit");
        submitButton.setFont(font.deriveFont(15f));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(submitButtonColor);
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ArrayList<String> FilledValues = new ArrayList<String>();
                for (JTextField textField : textFields)
                {
                    FilledValues.add(textField.getText());
                }
                try
                {
                room.Update(FilledValues.get(0), FilledValues.get(1), FilledValues.get(2), FilledValues.get(3), FilledValues.get(4), FilledValues.get(5), FilledValues.get(6), FilledValues.get(7));
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                AdminHome.RefreshRoomAvailability();
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, "Room Availability");
            }
        });

        formPanel.add(new JLabel()); // Empty label to align the button
        formPanel.add(submitButton);

        // Add the form panel to its wrapper
        gridContainer.add(formPanel, BorderLayout.CENTER);
        gridContainer.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
        
        // Add margin to the left and right of the grid container
        gridContainer.setBorder(new EmptyBorder(0, 20, 0, 20)); // Add a little spacing on the bottom


        roomInfoContainer.add(gridContainer, BorderLayout.CENTER);

        mainContainer.add(roomInfoContainer);


        // Wrap the reservationHistoryContainer in a JScrollPane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(mainContainer);
        scrollPane.getViewport().setBackground(Color.WHITE); // Set background color of the viewport
        scrollPane.setViewportBorder(null); // Remove the viewport border
        scrollPane.setBorder(null); // Remove the scroll pane border
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setForeground(Color.WHITE);
        
        add(scrollPane, BorderLayout.CENTER);
    }

    public void Refresh() throws IOException, FontFormatException, SQLException
    {
        removeAll();
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
        
        // Setting up a global container
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setBackground(Color.WHITE);

        // Setting up a container where we will have all of the content concerning the reservation history
        JPanel reservationHistoryContainer = new JPanel();
        // Setting basic properties of container
        reservationHistoryContainer.setLayout(new BorderLayout());
        reservationHistoryContainer.setOpaque(false);

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
        reservationHistoryContainer.add(RoservationHistorySubtitle, BorderLayout.NORTH);
        
        // Add wrapper to wrap the history
        ArrayList<Reservation> reservations = Reservation.getReservationsByRoom(room.getRoomID());
        reservationHistoryContainer.add(Box.createVerticalStrut(10));
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
            reservationHistoryContainer.add(container, BorderLayout.CENTER);
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
            reservationHistoryContainer.add(container, BorderLayout.CENTER);
        }
        mainContainer.add(reservationHistoryContainer);

        // Setting up a container where we will have all of the content concerning the reservation history
        JPanel roomInfoContainer = new JPanel();
        // Setting basic properties of container
        roomInfoContainer.setLayout(new BorderLayout());
        roomInfoContainer.setOpaque(false);

        // Add JLabel for reservation history subtitle
        JLabel roomInfoSubtitle = new JLabel("Information about room " + room.getTitle());
        roomInfoSubtitle.setFont(font.deriveFont(30f));
        roomInfoSubtitle.setForeground(Color.BLACK);
        roomInfoSubtitle.setOpaque(false);
        roomInfoSubtitle.setHorizontalAlignment(JLabel.LEFT);
        roomInfoSubtitle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        roomInfoSubtitle.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(10, 15, 0, 0),
            roomInfoSubtitle.getBorder()
        ));
        roomInfoContainer.add(roomInfoSubtitle, BorderLayout.NORTH);

        // A panel to contain the grid which will allow us greater flexibility
        JPanel gridContainer = new JPanel();
        gridContainer.setBackground(Color.WHITE);
        gridContainer.setLayout(new BorderLayout());

        // Panel to hold text fields, labels, and button
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2, 10, 10)); 
        formPanel.setBackground(Color.WHITE);

        // Add text fields and labels
        String[] labels = { 
            "Room Type:", 
            "Room Price:", 
            "Room Title:", 
            "Max Members: ", 
            "Bathroom:", 
            "Bedrooms:", 
            "Description", 
            "Building" 
        };

        // Information to prefill the textfields
        String[] information = {
            room.getRoomName(),
            Float.toString(room.getPrice()),
            room.getTitle(),
            Integer.toString(room.getMembers()),
            Integer.toString(room.getBathrooms()),
            Integer.toString(room.getBedrooms()),
            room.getDescription(),
            room.getBuilding()
        };

        // Store a reference to the text fields so that we can access their values later
        ArrayList<JTextField> textFields = new ArrayList<JTextField>(); 

        for (int i = 0;i < labels.length; ++i ) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(font.deriveFont(18f));
            label.setForeground(Color.BLACK);
            formPanel.add(label);

            JTextField textField = new JTextField();
            textField.setText(information[i]);
            textField.setPreferredSize(new Dimension(200, 30)); // Set fixed width
            textField.setMargin(new Insets(0, 10, 0, 10)); // Add margin to the left and right
            textFields.add(textField);
            formPanel.add(textField);
        }

        // Add submit button
        CustomButton submitButton = new CustomButton();
        submitButton.setText("Submit");
        submitButton.setFont(font.deriveFont(15f));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(submitButtonColor);
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ArrayList<String> FilledValues = new ArrayList<String>();
                for (JTextField textField : textFields)
                {
                    FilledValues.add(textField.getText());
                }
                try
                {
                room.Update(FilledValues.get(0), FilledValues.get(1), FilledValues.get(2), FilledValues.get(3), FilledValues.get(4), FilledValues.get(5), FilledValues.get(6), FilledValues.get(7));
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                AdminHome.RefreshRoomAvailability();
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, "Room Availability");
            }
        });

        formPanel.add(new JLabel()); // Empty label to align the button
        formPanel.add(submitButton);

        // Add the form panel to its wrapper
        gridContainer.add(formPanel, BorderLayout.CENTER);
        gridContainer.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
        
        // Add margin to the left and right of the grid container
        gridContainer.setBorder(new EmptyBorder(0, 20, 0, 20)); // Add a little spacing on the bottom


        roomInfoContainer.add(gridContainer, BorderLayout.CENTER);

        mainContainer.add(roomInfoContainer);


        // Wrap the reservationHistoryContainer in a JScrollPane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(mainContainer);
        scrollPane.getViewport().setBackground(Color.WHITE); // Set background color of the viewport
        scrollPane.setViewportBorder(null); // Remove the viewport border
        scrollPane.setBorder(null); // Remove the scroll pane border
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setForeground(Color.WHITE);
        
        add(scrollPane, BorderLayout.CENTER);
    }
}
