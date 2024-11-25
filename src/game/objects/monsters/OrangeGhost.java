package game.objects.monsters;

import game.objects.Player.Direction;
import javax.swing.*;
import java.awt.*;

public class OrangeGhost extends JPanel {

    private Image image;
    private Direction orangeGhostDirection;
    private Timer moveTimer;
    boolean canStart = false;
    private static final Point START_POINT = new Point(260,260);
    private static final Point EXIT_POINT = new Point(260,200);
    private int speed = 20;

    public void RandomSide(){
        orangeGhostDirection = GhostSettings.getRandomDirection();
    }

    private void setupMoveTimer() {
        moveTimer = new Timer(150, e -> moveGhost());
        moveTimer.start();


        Timer delayTimer = new Timer(5000, e -> {
            canStart = true;
            setLocation(EXIT_POINT.x, EXIT_POINT.y);
            ((Timer)e.getSource()).stop();
        });
        delayTimer.setRepeats(false);
        delayTimer.start();
    }

    private void moveGhost() {
        Direction newDirection = GhostSettings.moveGhost(orangeGhostDirection, canStart, this, speed);
        if (newDirection != null) {
            orangeGhostDirection = newDirection;
        }
    }

    public OrangeGhost()  {
        setBounds(240, 280, 20, 20);
        setOpaque(false);
        loadImage();
        RandomSide();  
        setupMoveTimer();
    }

    public void loadImage() {
        image  = GhostSettings.loadImage(this);
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