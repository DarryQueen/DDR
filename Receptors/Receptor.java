import java.awt.*;
import javax.swing.*;

public abstract class Receptor {
	private Image image, pressImage;
	private CoordinateSystem coordinates, pressCoordinates;
	private boolean downPress;

	public Receptor(Image i1, Image i2, int x) {
		image = i1;
		pressImage = i2;
		downPress = false;
		coordinates = new CoordinateSystem(x, 80, i1);
		pressCoordinates = new CoordinateSystem(x, 80, i2);
	}

	public void keyPress() {
		downPress = true;
	}
	public void keyRelease() {
		downPress = false;
	}

	public void draw(Graphics g) {
		if (downPress) {
			pressCoordinates.drawImage(g, pressImage);
		} else {
			coordinates.drawImage(g, image);
		}
	}
}