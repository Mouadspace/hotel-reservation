import java.awt.FontFormatException;
import java.io.IOException;

import ui.Register;
// import ui.Login;
// import ui.Home;
import ui.SavedHotels;

public class App {
    
    public App() throws FontFormatException, IOException {
        // new Register();
        // new Home();
        new SavedHotels();
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}
