package game.Frame;

import game.key.MyKeyListener;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private MyJLayeredPane layeredPane;

    public MyFrame(){
        layeredPane = new MyJLayeredPane();
        add(layeredPane);
        
        setTitle("Zevi's Pac-Man");
        setSize(D_Map.MAP_WIDTH_SIZE, D_Map.MAP_HEIGHT_SIZE);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new MyKeyListener(layeredPane.getPlayer()));
        setResizable(false);
        setVisible(true);
        setFocusable(true);
        requestFocus();
    }
    
    public MyJLayeredPane getLayeredPane() {
        return layeredPane;
    }
}
