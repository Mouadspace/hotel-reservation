package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FontFormatException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JPanel;

import mswing.CustomAdminCustomers;
import mswing.CustomAdminPriceAndDiscount;
import mswing.CustomAdminRoomAvailability;
import mswing.CustomAdminSidebar;
import utils.navigation.Screen;

public class AdminHome extends Screen{
    static JPanel cards;
    static CustomAdminSidebar sideBar;
    static CustomAdminRoomAvailability roomAvailabilityPanel;
    static CustomAdminPriceAndDiscount priceAndDiscount;
    static CustomAdminCustomers customers;
    public AdminHome() throws IOException, FontFormatException 
    {
        setLayout(new BorderLayout());
        
        cards = new JPanel(new CardLayout());

        // Add sidebar to the admin interface
        sideBar = new CustomAdminSidebar(cards);
        
        add(sideBar, BorderLayout.WEST);

        add(cards, BorderLayout.CENTER);
        try
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

        priceAndDiscount = new CustomAdminPriceAndDiscount();
        cards.add(priceAndDiscount, "Pricing & Discounts");

        try
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
}
