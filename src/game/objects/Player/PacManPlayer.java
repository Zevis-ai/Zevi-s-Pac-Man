package game.objects.Player;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// הגדרות השחקן וכן החלפת תמונות בעת שינוי כיוון
public class PacManPlayer extends JPanel {
    private Map<Direction, ImageIcon> pacmanImages;
    private Direction currentDirection;
    public static int life = 3;
    private int gifWidth = 20;  // רוחב מותאם
    private int gifHeight = 20; // גובה מותאם
    private Timer timer; // added timer variable

    public PacManPlayer(){
        setBounds(260, 460, 20, 20);
        setOpaque(false);
        loadImages();
        currentDirection = Direction.RIGHT; // ברירת המחדל היא ימינה
    }

    private void loadImages() {
        pacmanImages = new HashMap<>();
        String basePath = "/game/img/";
        try {
            pacmanImages.put(Direction.LEFT, new ImageIcon(getClass().getResource(basePath + "left.gif")));
            pacmanImages.put(Direction.RIGHT, new ImageIcon(getClass().getResource(basePath + "right.gif")));
            pacmanImages.put(Direction.UP, new ImageIcon(getClass().getResource(basePath + "up.gif")));
            pacmanImages.put(Direction.DOWN, new ImageIcon(getClass().getResource(basePath + "down.gif")));
        } catch (Exception e) {
            // אם התמונות לא נמצאו, נשתמש בצורה פשוטה
            setOpaque(true);
            setBackground(Color.YELLOW);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon imageToDraw = pacmanImages.get(currentDirection);
        if (imageToDraw != null) {
            // מציירים את ה-GIF בגודל המותאם
            g.drawImage(imageToDraw.getImage(), 0, 0, gifWidth, gifHeight, this);
        }
    }

    public void setDirection(Direction direction) {
        if (direction != null) {
            this.currentDirection = direction;
            repaint();
        }
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public void move(int newX, int newY) {
        setBounds(newX, newY, getWidth(), getHeight());
    }

    public void stopMovement() {
        // עוצרים את כל התנועה של השחקן
        if (timer != null) {
            timer.stop();
        }
    }
}
