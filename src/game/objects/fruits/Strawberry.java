package game.objects.fruits;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// מחזיק את תמונת הענבים
public class Strawberry extends JPanel {
    Image image;

    public Strawberry(){
        setOpaque(false);
        loadImage();
    }

    private void loadImage(){
        try {
            image = ImageIO.read(new File("C:\\Users\\JBH\\IdeaProjects\\Zevis-Pac-Man\\src\\game\\img\\strawberry.png"));
        } catch (IOException e){
            System.out.println("התמונה של התות");
        }
    }

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
