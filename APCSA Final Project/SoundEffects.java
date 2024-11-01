import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class SoundEffects {
      public static void main(String[] args) throws Exception {
      URL url = SoundEffects.class.getResource("test.wav");
      AudioClip clip = Applet.newAudioClip(url);
      clip.play();
      Thread.sleep(60000);
      clip.stop();
   }
}