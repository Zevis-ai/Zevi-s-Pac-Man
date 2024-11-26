package game.objects.monsters;

import game.Frame.Build_a_map;
import game.Frame.D_Map;
import game.key.MyKeyListener;
import game.messages.Messages;
import game.objects.Player.Direction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static game.Frame.Build_a_map.life;

public class GhostSettings {

    public static int delay = 150;
    public static boolean a = false;

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

    /**
     * פונקציה האחראית לתזוזה רנדומלית של הרוחות .
     *
     * הפונקציה בודקת אם ניתן להזיז את הרוח,
     * ומעדכנת את מיקום הרוח (jPanel) בהתאם לכיוון הנוכחי.
     * אם הרוח פוגעת בקיר או שאין כיוון מוגדר, נבחר כיוון חדש רנדומלי.
     *
     * @param direction הכיוון הנוכחי של הרוח (LEFT, RIGHT, UP, DOWN)
     * @param canStart משתנה בוליאני המגדיר האם ניתן להתחיל להזיז את הרוח
     * @param jPanel הפאנל המייצג את הרוח, עליו מתבצעת התנועה
     * @param speed 20המהירות שבה הרוח זזה  (בפיקסלים)
     * @return מחזיר את כיוון הרוח החדש
     *
     * הפונקציה מתאימה לעדכון מיקום הרוחות במהלך המשחק ומבטיחה שהרוחות יישארו
     * בתוך גבולות המפה.על ידי שימוש בפונקציה canMove
     */
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

        if (a){
            jPanel.setLocation(Blue_Ghost.START);
        }

        death(jPanel);

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

    public static Image loadImage(JPanel jPanel, File file) {
        Image image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.err.println("Could not load ghost image: " + e.getMessage());
            jPanel.setOpaque(true);
            jPanel.setBackground(Color.BLUE);
        }
        return image;
    }

    public static void death(JPanel player){
        if (MyKeyListener.player != null){
            int x = Math.abs(player.getX() - MyKeyListener.player.getX());
            int y = Math.abs(player.getY() - MyKeyListener.player.getY());

            if (x < 20 && y < 20){
//                player.setLocation(Blue_Ghost.START);

                if (player instanceof Blue_Ghost){
                    System.out.println("blue");
                }
                if (player instanceof OrangeGhost){
                    System.out.println("orange");
                }
                if (player instanceof Red_Ghost){
                    System.out.println("RED");
                }
                if (player instanceof Pink_Ghost){
                    System.out.println("PINK");
                }
                life--;

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                MyKeyListener.player.setLocation(260,460);
            }
        }
    }

}
