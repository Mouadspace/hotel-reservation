package mswing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.time.temporal.ChronoUnit;

import model.Reservation;

public class _CustomAdminRoomReservationCard extends _CustomRoundedPanel {
    private Color backgroundColor = new Color(240, 240, 240);
    public _CustomAdminRoomReservationCard(Reservation reservation) throws IOException, FontFormatException
    {
        // Import font
        File font_file = new File("Poppins-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        

        setBackground(backgroundColor);
        SetDimensions(new Dimension(480, 160));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        String contentString = "<html>Name of the person : " + reservation.getClientName() 
                            + " (ID: " + reservation.getClientID() + ")"
                            + "<br>Total price of reservation : " + reservation.getTotalPrice()
                            + "<br>Checkin date : " + reservation.getCheckIn().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) 
                            + "<br>Checkout date : " + reservation.getCheckOut().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) 
                            + "<br>Duration of stay : " + ChronoUnit.DAYS.between(reservation.getCheckIn(), reservation.getCheckOut()) + " nights"
                            + "</html>";
        // Add JLabel for reservation history subtitle
        JLabel content = new JLabel(contentString);
        content.setFont(font.deriveFont(21f));
        content.setForeground(Color.BLACK);
        content.setOpaque(false);
        content.setHorizontalAlignment(JLabel.LEFT);
        content.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        content.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(0, 15, 0, 0), // Add left margin of 10px
            content.getBorder()
        ));
        add(content);
    }    
}
