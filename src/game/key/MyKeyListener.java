package game.key;

import game.Frame.D_Map;
import game.Frame.MyJLayeredPane;
import game.Frame.StartScreen;
import game.messages.Messages;
import game.objects.Player.Direction;
import game.objects.Player.PacManPlayer;
import game.objects.coin.CoinArray;
import game.objects.fruits.EatFruitPrizes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import static game.Frame.Build_a_map.life;
import static game.Frame.Build_a_map.score;
import static game.messages.Messages.ScoreAnnouncement;

// מחלקה מאוד חשובה של קבלת המקשים ותזוזות השחקן
// KeyListener יושת מ
public class MyKeyListener implements KeyListener {

    public static PacManPlayer player;
    public static int MOVE_SPEED = 20;  // מהירות תנועה - תואמת לגודל התא
    public static int delay = 200; // מהירות השחקן
    public static Timer moveTimer;// טיימר למהירות השחקן מוגדר לפי ה delay

    public MyKeyListener(PacManPlayer player) {
        this.player = player;
        setupMoveTimer(delay);
    }

    // הטיימר של הפקמן השחקן
    public static void setupMoveTimer(int delay) {
        moveTimer = new Timer(delay, e -> {
            if (player.getDirection() != null) {
                movePlayer();
            }
        });
        moveTimer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // קבלת המקשים
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                player.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                player.setDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_UP:
                player.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                player.setDirection(Direction.DOWN);
                break;
        }
    }

    // לשמור שימשך לזוז אפילו שלא לוחצים
    @Override
    public void keyReleased(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_LEFT && player.getDirection() == Direction.LEFT) ||
            (e.getKeyCode() == KeyEvent.VK_RIGHT && player.getDirection() == Direction.RIGHT) ||
            (e.getKeyCode() == KeyEvent.VK_UP && player.getDirection() == Direction.UP) ||
            (e.getKeyCode() == KeyEvent.VK_DOWN && player.getDirection() == Direction.DOWN)) {
            player.setDirection(null);
        }
    }

    // פונקציה של תזוזות השחקן
    public static void movePlayer() {
        int currentX = player.getX();
        int currentY = player.getY();
        int newX = currentX;
        int newY = currentY;

        switch (player.getDirection()) {
            case LEFT:
                newX -= MOVE_SPEED;
                break;
            case RIGHT:
                newX += MOVE_SPEED;
                break;
            case UP:
                newY -= MOVE_SPEED;
                break;
            case DOWN:
                newY += MOVE_SPEED;
                break;
        }


        int mapX = newX / D_Map.CELL_SIZE;
        int mapY = newY / D_Map.CELL_SIZE;
//        System.out.println("x = " + newX + " , y = " + newY);
//        ScoreAnnouncement(mapX, mapY);

        cenMove(mapX, mapY, player, newX, newY);

        Messages.livesLabel.setText("חיים: " + life);
        Messages.nameLabel.setText("שם: " + StartScreen.getUserName());

        if (life < 0){
            MyJLayeredPane.playSE(0);
            Messages.gameOver(score);
        }
        // עדכון תמונות החיים
        MyJLayeredPane.getInstance().updateLifeImages();
        
        // בדיקה אם השחקן אכל פרי
        EatFruitPrizes.removeFruit();

        cave(newX,newY);
    }

    // שלא יעלה על הקירות
    public static void cenMove(int mapX, int mapY, JPanel panel, int newX, int newY){
        if (mapX >= 0 && mapX < D_Map.D_Map1[0].length &&
                mapY >= 0 && mapY < D_Map.D_Map1.length &&
                D_Map.D_Map1[mapY][mapX] != 1) {

            panel.move(newX, newY);
            // קריאה להסרת מטבע במיקום החדש
            if (panel instanceof PacManPlayer){
                CoinArray.removeCoin(mapX, mapY);
                ScoreAnnouncement(mapX,mapY);
            }
        }
    }

    // הגדרת מערה
    public static void cave(int newX, int newY){
        if (newX == 520 && newY == 280) {
            player.setLocation(0, 280);
        }
        if (newX == 0 && newY == 280) {
            player.setLocation(520, 280);
        }
    }
}
