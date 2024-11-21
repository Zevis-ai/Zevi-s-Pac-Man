package game.objects.monsters;

import game.key.MyKeyListener;
import game.objects.Player.Direction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Orange_Ghost extends JPanel implements Ghost {

    private Image image;
    private Direction oranghGhostDirection;
    private Timer moveTimer;
    private boolean passedTheStartingPoint_OR = false;
    private int currentPathIndex = 0;
    public static int orangeGhostSpeed = 20;

    private static final Point[] PATH_POINTS = {
            new Point(260,260),
            new Point(260,240),
            new Point(260,220),
            new Point(260,200),
    };

    public Orange_Ghost() {
        setBounds(260, 260, 20, 20);
        setOpaque(false);
        loadImage();
        randomDir();
        setupMoveTimer();
    }

    public void randomDir(){
        if (passedTheStartingPoint_OR) {
            // אם סיימנו את המסלול, נבחר כיוון רנדומלי לגמרי
            int direction = (int) (Math.random()*4)+1;
            oranghGhostDirection = switch (direction) {
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
                oranghGhostDirection = Direction.RIGHT;
            } else if (currentPoint.x > targetPoint.x) {
                oranghGhostDirection = Direction.LEFT;
            } else if (currentPoint.y < targetPoint.y) {
                oranghGhostDirection = Direction.DOWN;
            } else if (currentPoint.y > targetPoint.y) {
                oranghGhostDirection = Direction.UP;
            }
        }
    }

    private void setupMoveTimer() {
        moveTimer = new Timer(150, e -> moveGhost());
        moveTimer.start();
    }

    private void moveGhost() {
        if (oranghGhostDirection == null) {
            randomDir();
        }

        if (!passedTheStartingPoint_OR) {
            Point targetPoint = PATH_POINTS[currentPathIndex];

            // בדיקה אם הגענו לנקודה הנוכחית במסלול
            if (Math.abs(getX() - targetPoint.x) < orangeGhostSpeed &&
                    Math.abs(getY() - targetPoint.y) < orangeGhostSpeed) {

                // קידום לנקודה הבאה
                setLocation(targetPoint.x, targetPoint.y);
                currentPathIndex++;

                // בדיקה אם סיימנו את המסלול
                if (currentPathIndex >= PATH_POINTS.length) {
                    passedTheStartingPoint_OR = true;
                    // בחירת כיוון ראשוני - ימינה או שמאלה
                    oranghGhostDirection = (Math.random() < 0.5) ? Direction.LEFT : Direction.RIGHT;
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

        switch (oranghGhostDirection) {
            case LEFT -> newX -= orangeGhostSpeed;
            case RIGHT -> newX += orangeGhostSpeed;
            case UP -> newY -= orangeGhostSpeed;
            case DOWN -> newY += orangeGhostSpeed;
        }

        // בדיקת התנגשות בקירות
        int mapX = newX / 20;
        int mapY = newY / 20;

        if (!canMove(mapX, mapY)) {
            if (!passedTheStartingPoint_OR) {
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



    @Override
    public void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/game/img/orangeGhost.png"));
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
