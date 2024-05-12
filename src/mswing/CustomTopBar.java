package mswing;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import constants.FONTS;
import model.User;
import utils.ImgUtil;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.IOException;
import java.sql.SQLException;

import utils.navigation.Screen;



public class CustomTopBar extends Screen {
    public CustomTopBar(User client) throws FontFormatException, IOException{
        // User user=new User(); //user instance

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        FONTS font = new FONTS();
        JLabel logo = new JLabel(new ImageIcon(ImgUtil.resizeImage("assets/logo.png", 40)));

        JLabel userReservations = new JLabel(client.isLoggedIn ? "Reservation" : "");
        userReservations.setFont(font.getLabel());
        userReservations.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        userReservations.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (client.isLoggedIn) {
                    navigateTo("/reserved_rooms");
                } 
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        JLabel account=new JLabel(client.isLoggedIn ? "Sign out" : "Sign in");
        account.setFont(font.getLabel());

        account.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (client.isLoggedIn) {
                    try {
                        navigateTo("/home");
                        client.isLoggedIn = false;
                        client.setUser(0, null, null);
                        account.setText("Sign in");
                        userReservations.setText("");
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    navigateTo("/login");
                    client.isLoggedIn = true;
                    account.setText("Sign out");
                    userReservations.setText("Reservations");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        }); 

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
        add(userReservations);
        add(account);
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));


        
    }
}
