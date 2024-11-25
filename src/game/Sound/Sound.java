package game.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[5];
    private boolean isPlaying = false;

    public Sound(){
        soundURL[0] = getClass().getResource("/game/sound/death.wav");
        soundURL[1] = getClass().getResource("/game/sound/newGame.wav");
        soundURL[2] = getClass().getResource("/game/sound/nomnom.wav");
    }

    public void setFile(int i){
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            
            // הוספת מאזין לסיום הצליל
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        isPlaying = false;
                    }
                }
            });
            
        } catch (Exception e){
            System.out.println("Error loading sound: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void play(){
        if (clip != null && !isPlaying) {
            isPlaying = true;
            clip.setMicrosecondPosition(0);
            clip.start();
        }
    }

    public void loop(){
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop(){
        if (clip != null) {
            clip.stop();
            isPlaying = false;
        }
    }
}