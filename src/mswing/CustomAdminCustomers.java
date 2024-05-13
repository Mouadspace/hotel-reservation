package mswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

import model.User;

public class CustomAdminCustomers extends JPanel {
    public CustomAdminCustomers() throws IOException, FontFormatException, SQLException
    {
        // Import font file
        File font_file = new File("Poppins-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        
        // Setting up the basic properties
        setBounds(0, 0, 550, 550);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Add JLabel for title
        JLabel title = new JLabel("Customers");
        title.setFont(font.deriveFont(40f));
        title.setForeground(Color.BLACK);
        title.setOpaque(false);
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(0, 15, 0, 0), // Add left margin
            title.getBorder()
        ));
        add(title, BorderLayout.NORTH);

        // Container for all the rounded panels
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);

        ArrayList<User> customers = User.GetAllUsers();
        for (User customer : customers)
        {
            _CustomCustomerPanelCard customerCard = new _CustomCustomerPanelCard(customer);
            customerCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            container.add(customerCard);
            container.add(Box.createVerticalStrut(10));
        }

        // Wrap the mainContentContainer in a JScrollPane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(container);
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

        // Add JLabel for title
        JLabel title = new JLabel("Customers");
        title.setFont(font.deriveFont(40f));
        title.setForeground(Color.BLACK);
        title.setOpaque(false);
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(0, 15, 0, 0), // Add left margin
            title.getBorder()
        ));
        add(title, BorderLayout.NORTH);

        // Container for all the rounded panels
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);

        ArrayList<User> customers = User.GetAllUsers();
        for (User customer : customers)
        {
            _CustomCustomerPanelCard customerCard = new _CustomCustomerPanelCard(customer);
            customerCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            container.add(customerCard);
            container.add(Box.createVerticalStrut(10));
        }

        // Wrap the mainContentContainer in a JScrollPane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.getViewport().setBackground(Color.WHITE); // Set background color of the viewport
        scrollPane.setViewportBorder(null); // Remove the viewport border
        scrollPane.setBorder(null); // Remove the scroll pane border
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setForeground(Color.WHITE);

        add(scrollPane, BorderLayout.CENTER);
    }
}
