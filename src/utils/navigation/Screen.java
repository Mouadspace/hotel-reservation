package utils.navigation;

import java.awt.CardLayout;

import javax.swing.JPanel;

import routes.InitRoutes;

public class Screen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public void navigateTo(String screenName) {
		CardLayout cl = (CardLayout) InitRoutes.screenManager.getLayout();
		cl.show(InitRoutes.screenManager, screenName);
	}

}