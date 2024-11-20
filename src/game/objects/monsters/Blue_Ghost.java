package game.objects.monsters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Blue_Ghost extends JPanel implements Ghost {

    private Image image;

    public Blue_Ghost() {
        setBounds(240, 280, 20, 20);
        setOpaque(false);
        loadImage();
    }

    @Override
    public void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/game/img/blueGhost.png"));
        } catch (IOException e) {
            // אם התמונה לא נמצאה, נצייר צורה פשוטה במקום
            System.err.println("Could not load ghost image: " + e.getMessage());
            setOpaque(true);
            setBackground(Color.BLUE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.BLUE);
            g.fillOval(0, 0, getWidth(), getHeight());
        }
    }
}
