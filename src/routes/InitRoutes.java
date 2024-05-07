package routes;

import java.awt.FontFormatException;
import java.io.IOException;

import mswing.CustomFrame;
import ui.AdminHome;
import ui.Home;
import ui.Login;
import ui.Register;
import ui.Reservation;
import utils.navigation.ScreenManager;

public class InitRoutes {
  public InitRoutes() throws FontFormatException, IOException{
    CustomFrame frame = new CustomFrame();
    ScreenManager screenManager = new ScreenManager();
    
    // ADD YOUR SCREENS HERE
    screenManager.addScreen(new Login(), "/login");
    screenManager.addScreen(new Register(), "/register");
    screenManager.addScreen(new Home(), "/home");
    screenManager.addScreen(new Reservation(), "/reservation");
    screenManager.addScreen(new AdminHome(), "/admin");






    frame.add(screenManager);
    frame.setVisible(true);
  }
}
