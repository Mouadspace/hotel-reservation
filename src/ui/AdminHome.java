package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FontFormatException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JPanel;

import mswing.CustomAdminCustomers;
import mswing.CustomAdminRoomAvailability;
import mswing.CustomAdminSidebar;
import utils.navigation.Screen;

public class AdminHome extends Screen{
    static JPanel cards;
    static CustomAdminSidebar sideBar;
    static CustomAdminRoomAvailability roomAvailabilityPanel;
    static CustomAdminCustomers customers;
    public AdminHome() throws IOException, FontFormatException 
    {
        // Set the main layout
        setLayout(new BorderLayout());
        
        // Use cards system to navigate between screens
        cards = new JPanel(new CardLayout());

        // Add sidebar to the admin interface
        sideBar = new CustomAdminSidebar(cards);
        
        add(sideBar, BorderLayout.WEST);

        add(cards, BorderLayout.CENTER);
        try // Initialize the first card
        {
            roomAvailabilityPanel = new CustomAdminRoomAvailability(cards);
            cards.add(roomAvailabilityPanel, "Room Availability");
            CardLayout cardLayout = (CardLayout) cards.getLayout();
            cardLayout.show(cards, "Room Availability");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        try // Initialize the second card
        {
            customers = new CustomAdminCustomers();
            cards.add(customers, "Customers");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    // Setting is as static is just a workaround to make it possible to refresh a card
    // THIS CLASS IS NOT SUPPOSED TO BE USED AS A STATIC CLASS!!
    public static void RefreshRoomAvailability()
    {
        try
        {
            roomAvailabilityPanel.Refresh();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Setting is as static is just a workaround to make it possible to refresh a card
    // THIS CLASS IS NOT SUPPOSED TO BE USED AS A STATIC CLASS!!
    public static void RefreshCustomers()
    {
        try
        {
            customers.Refresh();
            CardLayout cardLayout = (CardLayout) cards.getLayout();
            cardLayout.show(cards, "Room Availability"); // Navigating back and forth fixes a weird bug
            cardLayout.show(cards, "Customers");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
