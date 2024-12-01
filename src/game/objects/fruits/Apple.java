package game.objects.fruits;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// מחזיק את תמונת התפוח
public class Apple extends JPanel {
    private Image image;

    public Apple() {
        setOpaque(false);
        loadImage();
    }

    private void loadImage() {
        try {
            image = ImageIO.read(new File("C:\\Users\\JBH\\IdeaProjects\\Zevis-Pac-Man\\src\\game\\img\\Apple.png"));
        } catch (IOException e) {
            System.err.println("התמונה של התפוח");
            setOpaque(true);
            setBackground(Color.RED);
        }
        setSize(100, 100);
setMinimumSize(new Dimension(100, 100));
setMaximumSize(new Dimension(100, 100));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);  // הגדלנו ל-30x30 פיקסלים
    }
}
