package game.objects.monsters;

import game.Frame.Build_a_map;
import game.objects.Player.Direction;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class OrangeGhost extends JPanel {

    public static Image image;
    public static Direction orangeGhostDirection;
    public static Timer moveTimer;
    public static boolean canStart = false;
    public static final Point START_POINT = new Point(260,260);
    public static final Point EXIT_POINT = new Point(260,200);
    public static int speed = 20;

    public void randomSide(){
        orangeGhostDirection = GhostSettings.getRandomDirection();
    }

    private static void setupMoveTimer(int delay, JPanel ghost) {
        if (moveTimer != null) {
            moveTimer.stop();
        }
        moveTimer = new Timer(delay, e -> {
            if (canStart) {
                OrangeGhost.moveGhost(ghost);
            }
        });
    }

    public static void moveGhost(JPanel ghost) {
        Direction newDirection = GhostSettings.moveGhost(orangeGhostDirection, canStart,ghost, speed);
        if (newDirection != null) {
            orangeGhostDirection = newDirection;
        }
    }

    public OrangeGhost()  {
        setBounds(240, 280, 20, 20);

        Timer scoreTimer = new Timer(100, e -> {
            if (Build_a_map.score > 580 && !canStart) {
                Timer delayTimer = new Timer(1000, e2 -> {
                    canStart = true;
                    setLocation(EXIT_POINT.x, EXIT_POINT.y);
                    moveTimer.start();
                    ((Timer)e2.getSource()).stop();
                });
                delayTimer.setRepeats(false);
                delayTimer.start();
                ((Timer)e.getSource()).stop();  // Stop checking score after ghost starts moving
            }
        });

        scoreTimer.start();
        setOpaque(false);
        loadImage();
        randomSide();
        setupMoveTimer(GhostSettings.delay, this);
    }

    public void loadImage() {
        File file = new File("C:\\Users\\JBH\\IdeaProjects\\Zevis-Pac-Man\\src\\game\\img\\orangeGhost.png");
        image  = GhostSettings.loadImage(this, file);
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