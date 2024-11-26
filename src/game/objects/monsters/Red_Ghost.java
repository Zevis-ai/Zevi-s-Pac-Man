package game.objects.monsters;

import game.objects.Player.Direction;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Red_Ghost extends JPanel implements Ghost {

    private Image image;
    private Direction redGhostDirection;
    private Timer moveTimer;
    private boolean canStart = false;
    private static final Point EXIT_POINT = new Point(260,200);
    public static int speed = 20;

    public void randomSide(){
      redGhostDirection = GhostSettings.getRandomDirection();
    }

    private void setupMoveTimer(int delay) {
        moveTimer = new Timer(delay, e -> moveGhost());
        moveTimer.start();

        Timer delayTimer = new Timer(1000, e -> {
            canStart = true;
            setLocation(EXIT_POINT.x, EXIT_POINT.y);
            ((Timer)e.getSource()).stop();
        });
        delayTimer.setRepeats(false);
        delayTimer.start();
    }

    private void moveGhost() {
        Direction newDirection = GhostSettings.moveGhost(redGhostDirection,canStart,this,speed);
        if (newDirection != null){
            redGhostDirection = newDirection;
        }
    }

    public Red_Ghost() {
        setBounds(260, 200, 20, 20);
        setOpaque(false);
        loadImage();
        randomSide();
        setupMoveTimer(GhostSettings.delay);
    }

    @Override
    public void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/game/img/redGhost.png"));
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
