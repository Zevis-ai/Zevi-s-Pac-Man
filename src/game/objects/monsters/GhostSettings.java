package game.objects.monsters;

import game.Frame.D_Map;
import game.objects.Player.Direction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GhostSettings {

    public static Direction getRandomDirection() {
        int direction = (int) (Math.random()*4)+1;
        return switch (direction) {
            case 1 -> Direction.DOWN;
            case 2 -> Direction.LEFT;
            case 3 -> Direction.UP;
            case 4 -> Direction.RIGHT;
            default -> null;
        };
    }

    public static Direction moveGhost(Direction direction, boolean canStart, JPanel jPanel, int speed) {
        if (!canStart) {
            return direction;
        }

        if (direction == null) {
            return getRandomDirection();
        }

        int currentX = jPanel.getX();
        int currentY = jPanel.getY();
        int newX = currentX;
        int newY = currentY;

        switch (direction) {
            case LEFT -> newX -= speed;
            case RIGHT -> newX += speed;
            case UP -> newY -= speed;
            case DOWN -> newY += speed;
        }

        // בדיקה שלא יוצאים מגבולות המפה
        if (canMove(newX / 20, newY / 20)) {
            jPanel.setLocation(newX, newY);
            return direction;
        } else {
            // אם נתקלים בקיר, בוחרים כיוון חדש
            return getRandomDirection();
        }
    }

    public static boolean canMove(int mapX, int mapY) {
        return mapX >= 0 && mapX < D_Map.D_Map1[0].length &&
                mapY >= 0 && mapY < D_Map.D_Map1.length &&
                D_Map.D_Map1[mapY][mapX] != 1;
    }

    public static Image loadImage(JPanel jPanel) {
        Image image = null;
        try {
            image = ImageIO.read(GhostSettings.class.getResource("/game/img/orangeGhost.png"));
        } catch (IOException e) {
            System.err.println("Could not load ghost image: " + e.getMessage());
            jPanel.setOpaque(true);
            jPanel.setBackground(Color.BLUE);
        }
        return image;
    }

}
