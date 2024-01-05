package com.mitienda.view;
//RoundBorderPanel.java
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

class RoundBorderPanel extends JPanel {
 private int cornerRadius;

 public RoundBorderPanel(Color color, int cornerRadius) {
     super();
     this.cornerRadius = cornerRadius;
     setOpaque(false);
     setBackground(color);
 }

 @Override
 protected void paintComponent(Graphics g) {
     super.paintComponent(g);
     Graphics2D g2d = (Graphics2D) g.create();
     g2d.setColor(getBackground());
     g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));
     g2d.dispose();
 }
}

class RoundBorderTextField extends JTextField {
    private int cornerRadius;

    public RoundBorderTextField(int columns, int cornerRadius) {
        super(columns);
        this.cornerRadius = cornerRadius;
        setOpaque(false);
        setBackground(new Color(64, 66, 73));
        setForeground(Color.WHITE);
        setFont(new Font("Jockey One", Font.PLAIN, 17));
        setColumns(columns);
        setBorder(new EmptyBorder(0, 10, 0, 10)); // Agrega un relleno al texto
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));
        super.paintComponent(g);
        g2d.dispose();
    }
}