import java.awt.FontFormatException;
import java.io.IOException;

import routes.InitRoutes;

public class App {
    
    public App() throws FontFormatException, IOException {
        new InitRoutes();
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}