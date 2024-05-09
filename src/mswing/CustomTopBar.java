package mswing;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.FONTS;
import utils.ImgUtil;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.IOException;

public class CustomTopBar extends JPanel {
    public CustomTopBar() throws FontFormatException, IOException{

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        FONTS font = new FONTS();
        JLabel logo = new JLabel(new ImageIcon(ImgUtil.resizeImage("assets/logo.png", 40)));
        
        JLabel account = new JLabel("Sign in");
        account.setFont(font.getLabel());

        CustomPanel searchPanel = new CustomPanel();
        searchPanel.setLayout(new GridLayout());
        
        CustomField search = new CustomField();
        
        searchPanel.add(search);
        searchPanel.setPreferredSize(new Dimension(200, 20));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10 , 0));
        
        add(logo);
        add(Box.createHorizontalGlue());
        add(searchPanel);
        add(Box.createHorizontalGlue());
        add(account);
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));


        
    }
}
