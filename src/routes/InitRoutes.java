package routes;

import java.awt.FontFormatException;
import java.io.IOException;
import java.sql.SQLException;

import model.User;
import mswing.CustomFrame;
import ui.AdminHome;
import ui.Home;
import ui.Login;
import ui.Register;
import ui.ReservedRooms;
//import ui.ReservedRooms;
import utils.navigation.ScreenManager;

public class InitRoutes {
  
  public static ScreenManager screenManager;

  public InitRoutes() throws FontFormatException, IOException, SQLException{
    CustomFrame frame = new CustomFrame();
    User client = new User();
    
    //ScreenManager screenManager = new ScreenManager();
    screenManager = new ScreenManager();

    // ADD YOUR SCREENS HERE
    screenManager.addScreen(new ReservedRooms(client), "/reserved_rooms");
    // Start here
    screenManager.addScreen(new Home(client), "/home");
    screenManager.addScreen(new Login(client), "/login");
    screenManager.addScreen(new Register(client), "/register");
    screenManager.addScreen(new AdminHome(), "/admin");
    // screenManager.addScreen(new ReservedRooms(client), "/reserved_rooms");


    frame.add(screenManager);
    frame.setVisible(true);
  }
}
