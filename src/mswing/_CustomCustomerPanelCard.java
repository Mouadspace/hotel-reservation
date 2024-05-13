package mswing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import ui.AdminHome;

public class _CustomCustomerPanelCard extends _CustomRoundedPanel {
    private Color backgroundColor = new Color(240, 240, 240);
    public _CustomCustomerPanelCard(User customer) throws IOException, FontFormatException
    {
        // Import font
        File font_file = new File("Poppins-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        

        setBackground(backgroundColor);
        SetDimensions(new Dimension(480, 230));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        String contentString = "<html>Customer Name : " + customer.getName() 
                            + " (ID: " + customer.getUserID() + ")"
                            + "<br>Email address : " + customer.getEmail()
                            + "<br>Phone Number : " + customer.getPhoneNo()
                            + "</html>";
        // Add JLabel for profile details
        JLabel content = new JLabel(contentString);
        content.setFont(font.deriveFont(21f));
        content.setForeground(Color.BLACK);
        content.setOpaque(false);
        content.setHorizontalAlignment(JLabel.LEFT);
        content.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        content.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(0, 15, 0, 0), // Add left margin of 15px
            content.getBorder()
        ));

        JPanel btnWrapper = new JPanel();
        btnWrapper.setLayout(new BoxLayout(btnWrapper, BoxLayout.Y_AXIS));
        btnWrapper.setBackground(backgroundColor);
        
        btnWrapper.add(Box.createVerticalStrut(10));
        CustomButton resetPassword = new CustomButton();
        resetPassword.setText("Reset Password");
        resetPassword.setFont(font.deriveFont(20f));
        resetPassword.setForeground(Color.WHITE);
        resetPassword.setBackground(Color.BLACK);
        resetPassword.setMaximumSize(new Dimension(200, 50));
        resetPassword.setMinimumSize(new Dimension(200, 50));
        resetPassword.setPreferredSize(new Dimension(200, 50));
        btnWrapper.add(resetPassword);

        btnWrapper.add(Box.createVerticalStrut(10));

        CustomButton deleteUser = new CustomButton();
        deleteUser.setText("Delete User");
        deleteUser.setFont(font.deriveFont(20f));
        deleteUser.setForeground(Color.WHITE);
        deleteUser.setBackground(Color.RED);
        deleteUser.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try
                {
                    customer.Drop();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                AdminHome.RefreshCustomers();
            }
        });
        deleteUser.setMaximumSize(new Dimension(200, 50));
        deleteUser.setMinimumSize(new Dimension(200, 50));
        deleteUser.setPreferredSize(new Dimension(200, 50));
        btnWrapper.add(deleteUser);
        
        btnWrapper.add(Box.createVerticalStrut(10));
        
        // Add margin to the left and right of the grid container
        btnWrapper.setBorder(new EmptyBorder(0, 15, 0, 15)); // Add a little spacing on the bottom


        add(content);
        add(btnWrapper);
    }    
}
