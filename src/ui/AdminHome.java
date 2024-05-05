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
import mswing.CustomFrame;

public class AdminHome {
    CustomFrame frame;
    JPanel cards;
    public AdminHome() throws IOException, FontFormatException 
    {
        frame = new CustomFrame();
        frame.setLayout(new BorderLayout());
        
        cards = new JPanel(new CardLayout());

        // Add sidebar to the admin interface
        CustomAdminSidebar sideBar = new CustomAdminSidebar(cards);
        
        frame.add(sideBar, BorderLayout.WEST);

        frame.add(cards, BorderLayout.CENTER);
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

        frame.setVisible(true);
    }
}
