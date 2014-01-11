import java.awt.*;
import javax.swing.*;

public class UpNote extends Note {
	public UpNote(int y) {
		super((new ImageIcon("Graphics/Up.gif")).getImage(), 370, y);
	}
	public UpNote(int y, boolean b) {
		super((new ImageIcon("Graphics/Up (Half).gif")).getImage(), 370, y);
	}

	public String toString() {
		return "w";
	}
}