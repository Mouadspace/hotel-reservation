package mswing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class _CustomRoundedPanel extends JPanel {
    private int arcWidth = 20; // Width of the arc
    private int arcHeight = 20; // Height of the arc
    private Dimension dimensions;

    public _CustomRoundedPanel()
    {
        this (new Dimension(480, 300));
    }

    public _CustomRoundedPanel(Dimension dim) {
        setOpaque(false); // Make the panel transparent so that the rounded corners are visible
        this.dimensions = dim;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cast Graphics to Graphics2D
        Graphics2D g2d = (Graphics2D) g.create();

        // Set rendering hints for better quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded rectangle
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        // Dispose of the Graphics2D object
        g2d.dispose();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return dimensions; // Set a default size
    }

    @Override
    public Dimension getMinimumSize() {
        return dimensions; // Set a default size
    }

    @Override
    public Dimension getMaximumSize() {
        return dimensions; // Set a default size
    }

}
