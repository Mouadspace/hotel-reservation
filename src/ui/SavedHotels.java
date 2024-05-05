package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontFormatException;
import java.io.IOException;
import java.time.chrono.JapaneseDate;

import javax.swing.JPanel;

import mswing.CustomFrame;
import mswing.CustomTopBar;


public class SavedHotels {
    CustomFrame frame;

    public SavedHotels() throws IOException, FontFormatException {
        frame = new CustomFrame();
        frame.setLayout(new BorderLayout());
        CustomTopBar topbar = new CustomTopBar();
        frame.add(topbar, BorderLayout.NORTH);

        // You have to create the cards for the EAST side
        // Create a card component

        // Any clicked card should be set in the WEST side
        // Probably by default you set the first card as the clicked one.
        
        
        frame.setVisible(true);
      
    }
}
