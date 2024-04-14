import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
public class AudioManager {
private static AudioManager instance;
public static AudioManager getInstance() {
if (instance == null)
instance = new AudioManager();
return instance;
}
private AudioManager() {
}
public void play(String filename) {
try {
InputStream in = new FileInputStream(filename);
AudioStream sound = new AudioStream(in);
AudioPlayer.player.start(sound);
} catch (FileNotFoundException e1) {
e1.printStackTrace();
} catch (IOException e1) {
e1.printStackTrace();
}
}
}
