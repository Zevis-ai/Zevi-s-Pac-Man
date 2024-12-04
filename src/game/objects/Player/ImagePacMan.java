package game.objects.Player;

import javax.swing.*;
import java.awt.*;

// תמונת השחקן pac man
public class ImagePacMan extends JPanel {
    private ImageIcon gifIcon;

    public ImagePacMan(){
        setOpaque(false);
        setPreferredSize(new Dimension(20, 20));  // קביעת גודל קבוע
        loadGif();

    }

    public void loadGif() {
        gifIcon = new ImageIcon("src\\game\\img\\pacmanleft.jpg");
        // התאמת גודל התמונה
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
