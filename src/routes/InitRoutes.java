package routes;

import java.awt.FontFormatException;
import java.io.IOException;
import java.sql.SQLException;

import mswing.CustomFrame;
import ui.AdminHome;
import ui.Home;
import ui.Login;
import ui.Register;
import ui.Reservation;
import ui.ReservedRooms;
import utils.navigation.ScreenManager;

public class InitRoutes {
  public InitRoutes() throws FontFormatException, IOException, SQLException{
    CustomFrame frame = new CustomFrame();
    ScreenManager screenManager = new ScreenManager();
    
    // ADD YOUR SCREENS HERE

    // Start here
    screenManager.addScreen(new Login(), "/login");
    screenManager.addScreen(new Register(), "/register");
    screenManager.addScreen(new Reservation(), "/reservation");
    screenManager.addScreen(new Home(), "/home");
    screenManager.addScreen(new AdminHome(), "/admin");
    screenManager.addScreen(new ReservedRooms(), "/reserved_rooms");


    frame.add(screenManager);
    frame.setVisible(true);
  }
}
