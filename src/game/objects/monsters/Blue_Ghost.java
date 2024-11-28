package game.objects.monsters;

import game.Frame.Build_a_map;
import game.key.MyKeyListener;
import game.objects.Player.Direction;
import game.objects.coin.CoinArray;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Blue_Ghost extends JPanel implements Ghost {

    public static Image image1;
    public static Direction blouGhostDirection;
    public static Timer moveTimer;
    public static boolean canStart = false;
    public static final Point EXIT_POINT = new Point(260,200);
    public static Point START = new Point(240, 280);
    public static int speed = 20;

    public void randomSide(){
        blouGhostDirection = GhostSettings.getRandomDirection();
    }

    public static void setupMoveTimer(int delay, JPanel ghost) {
        if (moveTimer != null) {
            moveTimer.stop();
        }
        moveTimer = new Timer(delay, e -> {
            if (canStart) {
                Blue_Ghost.moveGhost(ghost);
            }
        });
    }

    public static void moreSpeed(JPanel ghost){
        Blue_Ghost.setupMoveTimer(GhostSettings.delay, ghost );
    }

    public static void moveGhost(JPanel ghost) {
        Direction newDirection = GhostSettings.moveGhost(blouGhostDirection,canStart,ghost,speed);
        if (newDirection != null){
            blouGhostDirection = newDirection;
        }
    }

    public Blue_Ghost() {
        setBounds(240, 280, 20, 20);

        Timer scoreTimer = new Timer(100, e -> {
            if (Build_a_map.countEatingCoins > 59 && !canStart) {
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


    @Override
    public void loadImage() {
        try {
            image1 = ImageIO.read(getClass().getResource("/game/img/blueGhost.png"));
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
        if (image1 != null) {
            g.drawImage(image1, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.BLUE);
            g.fillOval(0, 0, getWidth(), getHeight());
        }
    }

    public static void setImage(Image image) {
        image1 = image;
    }
}
