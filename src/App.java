import java.awt.FontFormatException;
import java.io.IOException;

import ui.Home;
import ui.Login;
import ui.SavedHotels;

public class App {
    
    public App() throws FontFormatException, IOException {
        new  Login();
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}