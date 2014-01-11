import java.io.*;
import java.util.*;

public class SheetReader {
	private SheetReader() {}

	public static ArrayList<Note> readFile(String fileName) {
		ArrayList<Note> notes = new ArrayList<Note>();
		boolean firstLine = true;
		int position = 531, distance = 120;

		try {
			FileInputStream stream = new FileInputStream(fileName);
			DataInputStream input = new DataInputStream(stream);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String line;

			while ((line = reader.readLine()) != null) {
				//Delay:
				if (firstLine) {
					try {
						position = position + Integer.parseInt(line);
					} catch (NumberFormatException e) {}
					firstLine = false;
				}

				position = position + distance;
				int pos = position;
				boolean b = line.charAt(0) == '0' || line.charAt(0) == '5';
				if (b) {
					pos = pos - distance/2;
					position = position - distance;
				}
				if (line.indexOf('a') != -1) {
					if (b)
						notes.add(new LeftNote(pos, true));
					else
						notes.add(new LeftNote(pos));
				}
				if (line.indexOf('s') != -1) {
					if (b)
						notes.add(new DownNote(pos, true));
					else
						notes.add(new DownNote(pos));
				}
				if (line.indexOf('w') != -1) {
					if (b)
						notes.add(new UpNote(pos, true));
					else
						notes.add(new UpNote(pos));
				}
				if (line.indexOf('d') != -1) {
					if (b)
						notes.add(new RightNote(pos, true));
					else
						notes.add(new RightNote(pos));
				}
			}
			input.close();
		} catch (Exception e) {
			System.err.println("Incorrect file name!");
		}

		return notes;
	}
}