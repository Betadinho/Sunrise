package game;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.InputStream;

public class SoundThread extends Thread {

    private String path;
    
    public SoundThread(String path) {
        this.path = path;
    }
    
    @Override
    public void run() {
            try
            {
                // get the sound file as a resource out of my jar file;
                // the sound file must be in the same directory as this class file.
                // the input stream portion of this recipe comes from a javaworld.com article.
                InputStream inputStream = getClass().getResourceAsStream(path);
                AudioStream audioStream = new AudioStream(inputStream);
                AudioPlayer.player.start(audioStream);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
    }
}
