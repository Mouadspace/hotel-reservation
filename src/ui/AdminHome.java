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
    JPanel cards;
    public AdminHome() throws IOException, FontFormatException 
    {
        setLayout(new BorderLayout());
        
        cards = new JPanel(new CardLayout());

        // Add sidebar to the admin interface
        CustomAdminSidebar sideBar = new CustomAdminSidebar(cards);
        
        add(sideBar, BorderLayout.WEST);

        add(cards, BorderLayout.CENTER);
        try
        {
            CustomAdminRoomAvailability roomAvailabilityPanel = new CustomAdminRoomAvailability();
            cards.add(roomAvailabilityPanel, "Room Availability");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        CustomAdminPriceAndDiscount priceAndDiscount = new CustomAdminPriceAndDiscount();
        cards.add(priceAndDiscount, "Pricing & Discounts");

        CustomAdminCustomers customers = new CustomAdminCustomers();
        cards.add(customers, "Customers");

    }
}
