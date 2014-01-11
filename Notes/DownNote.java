import java.awt.*;
import javax.swing.*;

public class DownNote extends Note {
	public DownNote(int y) {
		super((new ImageIcon("Graphics/Down.gif")).getImage(), 230, y);
	}
	public DownNote(int y, boolean b) {
		super((new ImageIcon("Graphics/Down (Half).gif")).getImage(), 230, y);
	}

	public String toString() {
		return "s";
	}
}