package game.key;

import game.Frame.D_Map;
import game.objects.Player.Direction;
import game.objects.Player.PacManPlayer;
import game.objects.coin.CoinArray;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class MyKeyListener implements KeyListener {
    private final PacManPlayer player;
    private Timer moveTimer;
    private static final int MOVE_SPEED = 20;  // מהירות תנועה - תואמת לגודל התא

    public MyKeyListener(PacManPlayer player) {
        this.player = player;
        setupMoveTimer();
    }

    private void setupMoveTimer() {
        moveTimer = new Timer(100, e -> {
            if (player.getDirection() != null) {
                movePlayer();
            }
        });
        moveTimer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

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

    @Override
    public void keyReleased(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_LEFT && player.getDirection() == Direction.LEFT) ||
            (e.getKeyCode() == KeyEvent.VK_RIGHT && player.getDirection() == Direction.RIGHT) ||
            (e.getKeyCode() == KeyEvent.VK_UP && player.getDirection() == Direction.UP) ||
            (e.getKeyCode() == KeyEvent.VK_DOWN && player.getDirection() == Direction.DOWN)) {
            player.setDirection(null);
        }
    }

    private void movePlayer() {
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

        // המרת קואורדינטות המסך לקואורדינטות המפה
        int mapX = newX / D_Map.CELL_SIZE;
        int mapY = newY / D_Map.CELL_SIZE;

        // בדיקת גבולות המפה והתנגשות עם קירות
        if (mapX >= 0 && mapX < D_Map.D_Map1[0].length &&
            mapY >= 0 && mapY < D_Map.D_Map1.length &&
            D_Map.D_Map1[mapY][mapX] != 1) {
            
            player.move(newX, newY);
            // קריאה להסרת מטבע במיקום החדש
            CoinArray.removeCoin(mapX, mapY);
        }
        cave(newX,newY);
    }

    private void cave(int newX, int newY){
        if (newX == 520 && newY == 280) {
            player.setLocation(0, 280);
        }
        if (newX == 0 && newY == 280) {
            player.setLocation(520, 280);
        }
    }

}
