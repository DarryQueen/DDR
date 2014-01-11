import java.awt.*;
import javax.swing.*;

public class LeftNote extends Note {
	public LeftNote(int y) {
		super((new ImageIcon("Graphics/Left.gif")).getImage(), 91, y);
	}
	public LeftNote(int y, boolean b) {
		super((new ImageIcon("Graphics/Left (Half).gif")).getImage(), 91, y);
	}

	public String toString() {
		return "a";
	}
}