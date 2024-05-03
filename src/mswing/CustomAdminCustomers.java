package mswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CustomAdminCustomers extends JPanel {
    public CustomAdminCustomers() throws IOException, FontFormatException
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
            new EmptyBorder(0, 15, 0, 0), // Add left margin of 10px
            title.getBorder()
        ));
        add(title, BorderLayout.NORTH);
    }
}
