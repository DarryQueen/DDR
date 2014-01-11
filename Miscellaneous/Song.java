import java.util.*;
import java.awt.*;

public class Song {
	private ArrayList<Note> notes;
	private double tempo;
	private int currentNote = 0;

	public Song(String fileName, double t) {
		notes = SheetReader.readFile(fileName);
		tempo = t;
	}

	//Give response under certain circumstance:
	public String act() {
		String response = null;
		for (int i = currentNote; i < notes.size(); i++) {
			Note note = notes.get(i);
			if (note != null) {
				note.step(tempo);
				if (note.getPosition() < -31 && note.isVisible()) {
					note.setVisible(false);
					currentNote++;
				} else if (note.getPosition() < 19 && note.isVisible() && note.isAlive()) {
					response = "Note killed.";
				}
			}
		}
		return response;
	}

	public Note get(int i) {
		if (i >= 0 && i < notes.size()) {
			return notes.get(i);
		} else {
			return null;
		}
	}
	public int getNote(String key, int bounds) {
		for (int i = currentNote; i < Math.min(currentNote + 20, notes.size()); i++) {
			Note note = notes.get(i);
			if (note != null && note.toString().equals(key) && note.getPosition() > bounds) {
				return i;
			}
		}
		return -1;
	}
	public void killNote(int i) {
		if (i >= 0 && i <= notes.size()) {
			notes.get(i).destroy();
			//notes.remove(i);
			//notes.add(i, null);
		}
	}

	public void draw(Graphics g) {
		for (int i = currentNote; i < Math.min(currentNote + 20, notes.size()); i++) {
			Note note = notes.get(i);
			if (note != null && note.isVisible()) {
				note.draw(g);
			}
		}
	}
}