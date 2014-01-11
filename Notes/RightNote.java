import java.awt.*;
import javax.swing.*;

public class RightNote extends Note {
	public RightNote(int y) {
		super((new ImageIcon("Graphics/Right.gif")).getImage(), 509, y);
	}
	public RightNote(int y, boolean b) {
		super((new ImageIcon("Graphics/Right (Half).gif")).getImage(), 509, y);
	}

	public String toString() {
		return "d";
	}
}