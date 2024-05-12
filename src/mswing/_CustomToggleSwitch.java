package mswing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;


public class _CustomToggleSwitch extends JComponent {
    private boolean toggled = false;
    private Color onColor = Color.GREEN;
    private Color offColor = Color.RED;
    private Dimension size = new Dimension(50, 30);

    public _CustomToggleSwitch() {
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setOpaque(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                toggled = !toggled;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        // Paint the switch body
        g2d.setColor(toggled ? onColor : offColor);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());

        // Paint the switch knob
        g2d.setColor(Color.WHITE);
        int knobSize = getHeight() - 4;
        int x = toggled ? getWidth() - knobSize - 2 : 2;
        g2d.fillOval(x, 2, knobSize, knobSize);

        g2d.dispose();
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
        repaint();
    }
}
