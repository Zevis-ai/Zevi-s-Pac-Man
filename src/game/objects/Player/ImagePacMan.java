package game.objects.Player;

import javax.swing.*;
import java.awt.*;

public class ImagePacMan extends JPanel {
    private ImageIcon gifIcon;

    public ImagePacMan(){
        setBounds(0, 0, 10, 10);
        setOpaque(false);
        loadGif();

    }

    public void loadGif() {
        gifIcon = new ImageIcon("C:\\Users\\JBH\\IdeaProjects\\Zevis-Pac-Man\\src\\game\\img\\left.gif");
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
