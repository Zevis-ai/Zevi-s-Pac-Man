package game.objects.fruits;

import javax.swing.*;
import java.awt.*;

// מחזיק את תמונת הדובדבן
public class Cherry extends JPanel {
    private ImageIcon gifIcon;
    private Timer appearanceTimer;

    public Cherry() {
        setOpaque(false);
        setPreferredSize(new Dimension(20,20));
        loadGif();
    }

    public void loadGif() {
        gifIcon = new ImageIcon("C:\\Users\\JBH\\IdeaProjects\\Zevis-Pac-Man\\src\\game\\img\\cherry-removebg-preview.png");
        Image img = gifIcon.getImage();
        Image newimg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        gifIcon = new ImageIcon(newimg);
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
