package game.objects.coin;

import javax.swing.*;
import java.awt.*;

// מחלקה של המטבעות הגדולים על המפה GIF מהבהב
public class Coin extends JPanel {
    private ImageIcon gifIcon;

    public Coin() {
        setBounds(50, 50, 20, 20);
        setOpaque(false);
        loadGif();
    }

    public void loadGif() {
        gifIcon = new ImageIcon("C:\\Users\\JBH\\Downloads\\gifmaker_me.gif");
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