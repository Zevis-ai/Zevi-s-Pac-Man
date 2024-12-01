package game.Frame;

import game.key.MyKeyListener;
import javax.swing.*;

// מחלקה היורשת מגיפראם ובה הגדרות הפריים של המשחק

public class MyFrame extends JFrame {

    // השכבה
    private MyJLayeredPane layeredPane;

    // הבנאי של הגדרות הפריים
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
