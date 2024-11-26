package game.objects.fruits;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cherry extends JPanel {
    private ImageIcon gifIcon;
    private Timer appearanceTimer;

    public Cherry() {
        setBounds(260, 340, 20, 20);
        setOpaque(false);
        loadGif();
        setVisible(false);  // Start invisible
        
        // Timer to make cherry appear after 15 seconds
        appearanceTimer = new Timer(15000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                ((Timer)e.getSource()).stop();
            }
        });
        appearanceTimer.setRepeats(false);
        appearanceTimer.start();
    }

    public void loadGif() {
        gifIcon = new ImageIcon("C:\\Users\\JBH\\IdeaProjects\\Zevis-Pac-Man\\src\\game\\img\\Fruit.jpg");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gifIcon != null) {
            gifIcon.paintIcon(this, g, 0, 0);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(gifIcon.getIconWidth(), gifIcon.getIconHeight());
    }
}
