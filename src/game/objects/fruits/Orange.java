package game.objects.fruits;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// מחזיק את תמונת התפוז
public class Orange extends JPanel {
    private Image image;

    public Orange(){
        setOpaque(false);
        loadImage();
    }

    private void loadImage(){
        try {
            image = ImageIO.read(new File("src\\game\\img\\orange.png"));
        } catch (IOException e){
            System.out.println("התמונה של התפוז");
        }
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
        return new Dimension(30, 30);  // הגדלנו ל-30x30 פיקסלים
    }
}
