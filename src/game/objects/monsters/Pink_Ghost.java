package game.objects.monsters;

import game.Frame.Build_a_map;
import game.objects.Player.Direction;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Pink_Ghost extends JPanel {

    public static Image image;
    public static Direction pinkGhostDirection;
    public static Timer moveTimer;
    public static boolean canStart = false;
    public static final Point START_POINT = new Point(280, 280);
    public static final Point EXIT_POINT = new Point(260, 200);
    public static int speed = 20;

    public Pink_Ghost(){
        setBounds(START_POINT.x, START_POINT.y, 20, 20);

        Timer scoreTimer = new Timer(100, e -> {
            if (Build_a_map.countEatingCoins > 177 && !canStart) {
                Timer delayTimer = new Timer(1000, e2 -> {
                    canStart = true;
                    setLocation(EXIT_POINT.x, EXIT_POINT.y);
                    moveTimer.start();
                    ((Timer)e2.getSource()).stop();
                });
                delayTimer.setRepeats(false);
                delayTimer.start();
                ((Timer)e.getSource()).stop();
            }
        });

        scoreTimer.start();
        setOpaque(false);
        loadImage();
        RandomSide();
        setupMoveTimer(GhostSettings.delay, this);
    }

    public void RandomSide(){
        pinkGhostDirection = GhostSettings.getRandomDirection();
    }

    public static void setupMoveTimer(int delay, JPanel ghost) {
        if (moveTimer != null) {
            moveTimer.stop();
        }
        moveTimer = new Timer(delay, e -> {
            if (canStart) {
                Pink_Ghost.moveGhost(ghost);
            }
        });
    }

    public static void moveGhost(JPanel ghost) {
        Direction newDirection = GhostSettings.moveGhost(pinkGhostDirection, canStart, ghost, speed);
        if (newDirection != null) {
            pinkGhostDirection = newDirection;
        }
    }

    public void loadImage() {
        File file = new File("C:\\Users\\JBH\\IdeaProjects\\Zevis-Pac-Man\\src\\game\\img\\pinkGhost.png");
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

    public static void setImage(Image image) {
        Pink_Ghost.image = image;
    }
}