package game;
import game.Frame.MyJLayeredPane;
import static game.Frame.StartScreen.createWelcomeFrame;


// המחלקה הראשית הקוראת לפונקציית הפתיחה
public class Game {
    public static void main(String[] args) throws InterruptedException {

        //new MyFrame();
        createWelcomeFrame();
//     הודעת פתיחה
//        SwingUtilities.invokeLater(() -> {
//            StartScreen startScreen = new StartScreen();
//            startScreen.setVisible(true);
//        });
        // הפעלת סאונד של תחילת המשחק
        MyJLayeredPane.playSE(1);
        Thread.sleep(5000);

    }
}
