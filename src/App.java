import java.awt.FontFormatException;
import java.io.IOException;

import ui.Register;
// import ui.Login;
// import ui.Home;

public class App {
    
    public App() throws FontFormatException, IOException {
        new Register();
        // new Home();
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}
