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
        
        
        frame.setVisible(true);
      
    }
}
