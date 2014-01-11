import java.io.*;
import javax.sound.sampled.*;
import sun.audio.*;

public class MusicPlayer {
	public static double play(String fileName) throws IOException, InterruptedException {
		AudioStream stream = null;
		InputStream input = null;
		double duration = 0;

		try {
			input = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("Incorrect file name!");
			return 0;
		}
		try {
			stream = new AudioStream(input);
		} catch (IOException e) {
			System.out.println("Incorrect file name!");
			return 0;
		} catch (NullPointerException e) {
			System.out.println("Incorrect file name!");
			return 0;
		}

		AudioPlayer.player.start(stream);

		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(fileName));
			duration = (double)audioInputStream.getFrameLength()/audioInputStream.getFormat().getFrameRate();
		} catch (UnsupportedAudioFileException e) {
			System.out.println("Incorrect file name!");
			return 0;
		}

		return duration;
	}
}