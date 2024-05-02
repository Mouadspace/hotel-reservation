package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import mswing.CustomAdminSidebar;
import mswing.CustomFrame;

public class AdminHome {
    CustomFrame frame;
    public AdminHome() throws IOException, FontFormatException 
    {
        frame = new CustomFrame();
        frame.setLayout(new BorderLayout());
        
        CustomAdminSidebar sideBar = new CustomAdminSidebar();
        frame.add(sideBar, BorderLayout.WEST);
        frame.setVisible(true);
    }
}
