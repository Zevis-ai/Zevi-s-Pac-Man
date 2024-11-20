package game.Frame;

import game.key.MyKeyListener;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame(){
        MyJPanel gamePanel = new MyJPanel();
        add(gamePanel);
        
        setTitle("Zevi's Pac-Man");
        setSize(D_Map.MAP_WIDTH_SIZE , D_Map.MAP_HEIGHT_SIZE* 2);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new MyKeyListener());
        setResizable(false);
        setVisible(true);
        setFocusable(true);
        requestFocus();
    }
}
