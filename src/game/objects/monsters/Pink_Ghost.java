package game.objects.monsters;

import game.Frame.Build_a_map;
import game.key.MyKeyListener;
//import game.objects.CollisionManager;
import game.messages.Messages;
import game.objects.Player.Direction;
import game.objects.Player.PacManPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Pink_Ghost extends JPanel implements Ghost {

    private Image image;
    private Direction pinkGhostDirection;
    private Timer moveTimer;
    private Timer chaseTimer;  // טיימר למעבר למצב רדיפה
    private PacManPlayer player;
    public static int pinkGhostSpeed = 20;

    private enum GhostState {
        FOLLOWING_PATH,    // הולך במסלול
        RANDOM_MOVEMENT,   // תנועה רנדומלית
        CHASING_PLAYER    // רודף אחרי השחקן
    }

    // הגדרת נקודות המסלול
    private static final Point[] PATH_POINTS = {
            new Point(280, 280), // התחלה
            new Point(260, 280),
            new Point(260, 240),
            new Point(260, 220),
    };

    private boolean hasPassedRestrictedPoint = false; // דגל שמציין אם הרוח כבר עברה בנקודה האסורה
    private static final Point RESTRICTED_POINT = new Point(260, 240); // הנקודה האסורה
    private int currentPathIndex = 0;
    private GhostState currentState = GhostState.FOLLOWING_PATH;
    private Direction randomDirection = null;

    public Pink_Ghost() {
        setBounds(280, 280, 20, 20);
        setOpaque(false);
        loadImage();
        pinkGhostDirection = Direction.LEFT;  // Start moving left
        setupMoveTimer();
        setupChaseTimer();
    }

    private void setupChaseTimer() {
        chaseTimer = new Timer(6000, e -> {
            currentState = GhostState.CHASING_PLAYER;
            chaseTimer.stop();  // עוצר את הטיימר כי צריך להפעיל אותו רק פעם אחת
        });
        chaseTimer.setRepeats(false);
        chaseTimer.start();
    }

    public void setPlayer(PacManPlayer player) {
        this.player = player;
    }

    public void randomDir() {
        switch (currentState) {
            case FOLLOWING_PATH:
                followPath();
                break;
            case RANDOM_MOVEMENT:
                moveRandomly();
                break;
            case CHASING_PLAYER:
                chasePlayer();
                break;
        }
    }

    private void followPath() {
        if (currentPathIndex < PATH_POINTS.length) {
            Point targetPoint = PATH_POINTS[currentPathIndex];

            // בדיקה אם הנקודה הבאה היא הנקודה האסורה
            if (targetPoint.equals(RESTRICTED_POINT)) {
                if (hasPassedRestrictedPoint) {
                    // אם כבר עברנו בנקודה זו, נעבור לנקודה הבאה
                    currentPathIndex++;
                    return;
                } else {
                    // אם זו הפעם הראשונה, נסמן שעברנו בה
                    hasPassedRestrictedPoint = true;
                }
            }

            int dx = targetPoint.x - getX();
            int dy = targetPoint.y - getY();

            // אם הגענו לנקודה הנוכחית
            if (Math.abs(dx) < 5 && Math.abs(dy) < 5) {
                currentPathIndex++;
                if (currentPathIndex >= PATH_POINTS.length) {
                    currentState = GhostState.RANDOM_MOVEMENT;
                    // בחירה רנדומלית בין ימינה ושמאלה
                    randomDirection = Math.random() < 0.5 ? Direction.RIGHT : Direction.LEFT;
                    return;
                }
            }

            // בחירת הכיוון לנקודה הבאה
            if (Math.abs(dx) > Math.abs(dy)) {
                pinkGhostDirection = dx > 0 ? Direction.RIGHT : Direction.LEFT;
            } else {
                pinkGhostDirection = dy > 0 ? Direction.DOWN : Direction.UP;
            }
        }
    }

    private void moveRandomly() {
        if (randomDirection == null) {
            // בחירה רנדומלית בין ימינה ושמאלה
            randomDirection = Math.random() < 0.5 ? Direction.RIGHT : Direction.LEFT;
        }

        // בדיקה שהתנועה הרנדומלית לא תוביל לנקודה האסורה
        Point potentialNextLocation = calculateNextLocation(getLocation(), randomDirection);
        if (hasPassedRestrictedPoint && potentialNextLocation.equals(RESTRICTED_POINT)) {
            // אם התנועה תוביל לנקודה האסורה, נבחר כיוון אחר
            randomDirection = getNewRandomDirection();
            return;
        }

        pinkGhostDirection = randomDirection;
    }

    private void chasePlayer() {
        if (player != null) {
            int dx = player.getX() - getX();
            int dy = player.getY() - getY();

            // נסיון ראשון - הכיוון המועדף
            Direction preferredDirection;
            if (Math.abs(dx) > Math.abs(dy)) {
                preferredDirection = dx > 0 ? Direction.RIGHT : Direction.LEFT;
            } else {
                preferredDirection = dy > 0 ? Direction.DOWN : Direction.UP;
            }

            // בדיקה שהרדיפה לא תוביל לנקודה האסורה
            Point potentialChaseLocation = calculateNextLocation(getLocation(), preferredDirection);
            if (hasPassedRestrictedPoint && potentialChaseLocation.equals(RESTRICTED_POINT)) {
                // אם הרדיפה תוביל לנקודה האסורה, נבחר כיוון אחר
                preferredDirection = getAlternativeDirection(preferredDirection);
            }

            // בדיקה אם אפשר ללכת בכיוון המועדף
            int newX = getX();
            int newY = getY();
            switch (preferredDirection) {
                case LEFT -> newX -= 20;
                case RIGHT -> newX += 20;
                case UP -> newY -= 20;
                case DOWN -> newY += 20;
            }

            // אם אי אפשר ללכת בכיוון המועדף, ננסה כיוונים אחרים
            if (!canMove(newX / 20, newY / 20)) {
                // ננסה את כל הכיוונים האפשריים
                Direction[] allDirections = {Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT};
                boolean foundDirection = false;

                for (Direction dir : allDirections) {
                    if (dir == preferredDirection) continue; // כבר בדקנו את הכיוון הזה

                    newX = getX();
                    newY = getY();
                    switch (dir) {
                        case LEFT -> newX -= 20;
                        case RIGHT -> newX += 20;
                        case UP -> newY -= 20;
                        case DOWN -> newY += 20;
                    }

                    if (canMove(newX / 20, newY / 20)) {
                        pinkGhostDirection = dir;
                        foundDirection = true;
                        break;
                    }
                }

                // אם לא מצאנו כיוון אפשרי, ננסה לחזור אחורה
                if (!foundDirection) {
                    Direction oppositeDirection = getOppositeDirection(pinkGhostDirection);
                    newX = getX();
                    newY = getY();
                    switch (oppositeDirection) {
                        case LEFT -> newX -= 20;
                        case RIGHT -> newX += 20;
                        case UP -> newY -= 20;
                        case DOWN -> newY += 20;
                    }
                    if (canMove(newX / 20, newY / 20)) {
                        pinkGhostDirection = oppositeDirection;
                    }
                }
            } else {
                pinkGhostDirection = preferredDirection;
            }
        }
    }

    private Direction getOppositeDirection(Direction dir) {
        return switch (dir) {
            case LEFT -> Direction.RIGHT;
            case RIGHT -> Direction.LEFT;
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
        };
    }

    private void setupMoveTimer() {
        moveTimer = new Timer(250, e -> moveGhost());
        moveTimer.start();
    }

    private void moveGhost() {
        if (player == null) return;

        switch (currentState) {
            case FOLLOWING_PATH:
                followPath();
                break;
            case RANDOM_MOVEMENT:
                moveRandomly();
                break;
            case CHASING_PLAYER:
                chasePlayer();
                break;
        }



//        // בדיקת התנגשות עם השחקן
//        if (CollisionManager.checkCollision(player, this)) {
//            Ghost[] ghosts = {this}; // במקרה זה יש לנו רק את הרוח הוורודה
//            CollisionManager.handleCollision(player, ghosts);
//        }

        // עדכון המיקום בפועל
        int currentX = getX();
        int currentY = getY();
        int newX = currentX;
        int newY = currentY;

        Messages.livesLabel.setText("חיים: " + PacManPlayer.life);
        if (player.getX() == newX && player.getY() == newY){
            PacManPlayer.life--;
        }
        if (PacManPlayer.life == 0){
            Messages.gameOver(Build_a_map.score);
        }

        switch (pinkGhostDirection) {
            case LEFT -> newX -= pinkGhostSpeed;
            case RIGHT -> newX += pinkGhostSpeed;
            case UP -> newY -= pinkGhostSpeed;
            case DOWN -> newY += pinkGhostSpeed;
        }

        // בדיקת התנגשות בקירות
        int mapX = newX / 20;
        int mapY = newY / 20;

        if (!canMove(mapX, mapY)) {
            // אם נתקענו בקיר, ננסה כיוון אחר
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
            image = ImageIO.read(getClass().getResource("/game/img/pinkGhost.png"));
        } catch (IOException e) {
            System.err.println("Could not load ghost image: " + e.getMessage());
            setOpaque(true);
            setBackground(Color.PINK);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.PINK);
            g.fillOval(0, 0, getWidth(), getHeight());
        }
    }

    // מתודת עזר לחישוב המיקום הבא לפי כיוון
    private Point calculateNextLocation(Point current, Direction direction) {
        Point next = new Point(current);
        switch (direction) {
            case UP:
                next.y -= pinkGhostSpeed;
                break;
            case DOWN:
                next.y += pinkGhostSpeed;
                break;
            case LEFT:
                next.x -= pinkGhostSpeed;
                break;
            case RIGHT:
                next.x += pinkGhostSpeed;
                break;
        }
        return next;
    }

    // מתודת עזר לבחירת כיוון חלופי כשהכיוון המקורי מוביל לנקודה האסורה
    private Direction getAlternativeDirection(Direction originalDirection) {
        Direction[] possibleDirections = Direction.values();
        for (Direction newDirection : possibleDirections) {
            if (newDirection != originalDirection) {
                Point potentialLocation = calculateNextLocation(getLocation(), newDirection);
                if (!potentialLocation.equals(RESTRICTED_POINT)) {
                    return newDirection;
                }
            }
        }
        return originalDirection; // אם אין ברירה, נשאר עם הכיוון המקורי
    }

    // מתודת עזר לבחירת כיוון רנדומלי חדש
    private Direction getNewRandomDirection() {
        return Math.random() < 0.5 ? Direction.UP : Direction.DOWN;
    }

    // מתודה חדשה להתחלת רדיפה
    public void startChasing() {
        currentState = GhostState.CHASING_PLAYER;
        // עצירת הטיימר הקיים אם הוא פעיל
        if (chaseTimer != null && chaseTimer.isRunning()) {
            chaseTimer.stop();
        }
    }
}
