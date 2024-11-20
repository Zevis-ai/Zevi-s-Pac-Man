package game.objects.Player;

import javax.swing.*;
import java.awt.*;

public class PacManPlayer extends JPanel {
    private Direction currentDirection;

    public PacManPlayer(){
        setBounds(260, 460, 20, 20);
        setOpaque(true);  
        setBackground(Color.RED);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void setDirection(Direction direction) {
        this.currentDirection = direction;
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public void move(int newX, int newY) {
        setBounds(newX, newY, getWidth(), getHeight());
    }
}
