package game.objects.monsters;

import game.key.MyKeyListener;
import game.objects.Player.Direction;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;



public class Blue_Ghost extends JPanel implements Ghost {

    private Image image;
    private Direction blouGhostDirection;
    private Timer moveTimer;
    private boolean passedTheStartingPoint = false;
    private int currentPathIndex = 0;
    public static int blueGhostSpeed = 20;

    private static final Point[] PATH_POINTS = {
            new Point(240,280),
            new Point(260,280),
            new Point(260,240),
            new Point(260,220),
            new Point(260,200),
    };

    public void randomDir(){
        if (passedTheStartingPoint) {
            // אם סיימנו את המסלול, נבחר כיוון רנדומלי לגמרי
            int direction = (int) (Math.random()*4)+1;
            blouGhostDirection = switch (direction) {
                case 1 -> Direction.DOWN;
                case 2 -> Direction.LEFT;
                case 3 -> Direction.UP;
                case 4 -> Direction.RIGHT;
                default -> null;
            };
        } else {
            // מציאת הכיוון לנקודה הבאה במסלול
            Point currentPoint = new Point(getX(), getY());
            Point targetPoint = PATH_POINTS[currentPathIndex];

            if (currentPoint.x < targetPoint.x) {
                blouGhostDirection = Direction.RIGHT;
            } else if (currentPoint.x > targetPoint.x) {
                blouGhostDirection = Direction.LEFT;
            } else if (currentPoint.y < targetPoint.y) {
                blouGhostDirection = Direction.DOWN;
            } else if (currentPoint.y > targetPoint.y) {
                blouGhostDirection = Direction.UP;
            }
        }
    }


    private void setupMoveTimer() {
        moveTimer = new Timer(150, e -> moveGhost());
        moveTimer.start();
    }

    private void moveGhost() {
        if (blouGhostDirection == null) {
            randomDir();
        }

        if (!passedTheStartingPoint) {
            Point targetPoint = PATH_POINTS[currentPathIndex];

            // בדיקה אם הגענו לנקודה הנוכחית במסלול
            if (Math.abs(getX() - targetPoint.x) < blueGhostSpeed &&
                    Math.abs(getY() - targetPoint.y) < blueGhostSpeed) {

                // קידום לנקודה הבאה
                setLocation(targetPoint.x, targetPoint.y);
                currentPathIndex++;

                // בדיקה אם סיימנו את המסלול
                if (currentPathIndex >= PATH_POINTS.length) {
                    passedTheStartingPoint = true;
                    // בחירת כיוון ראשוני - ימינה או שמאלה
                    blouGhostDirection = (Math.random() < 0.5) ? Direction.LEFT : Direction.RIGHT;
                    return;
                }

                randomDir();
                return;
            }
        }

        int currentX = getX();
        int currentY = getY();
        int newX = currentX;
        int newY = currentY;

        switch (blouGhostDirection) {
            case LEFT -> newX -= blueGhostSpeed;
            case RIGHT -> newX += blueGhostSpeed;
            case UP -> newY -= blueGhostSpeed;
            case DOWN -> newY += blueGhostSpeed;
        }

        // בדיקת התנגשות בקירות
        int mapX = newX / 20;
        int mapY = newY / 20;

        if (!canMove(mapX, mapY)) {
            if (!passedTheStartingPoint) {
                // אם נתקענו במהלך המסלול, נחזור להתחלה
                setLocation(PATH_POINTS[0].x, PATH_POINTS[0].y);
                currentPathIndex = 0;
            }
            randomDir();
            return;
        }

        MyKeyListener.cenMove(mapX, mapY, this, newX, newY);
    }

    private boolean canMove(int mapX, int mapY) {
        return mapX >= 0 && mapX < game.Frame.D_Map.D_Map1[0].length &&
                mapY >= 0 && mapY < game.Frame.D_Map.D_Map1.length &&
                game.Frame.D_Map.D_Map1[mapY][mapX] != 1;
    }

    public Blue_Ghost() {
        setBounds(240, 280, 20, 20);
        setOpaque(false);
        loadImage();
        randomDir();
        setupMoveTimer();
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
