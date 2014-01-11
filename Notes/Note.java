import java.awt.*;
import javax.swing.*;

public abstract class Note {
	private Image image;
	private CoordinateSystem coordinates;
	private boolean destroyed, isVisible = true;;
	private double position;

	public Note(Image i, int x, int y) {
		image = i;
		position = y;
		coordinates = new CoordinateSystem(x, y, i);
	}

	public double getPosition() {
		return position;
	}

	public void destroy() {
		destroyed = true;
	}
	public void setVisible(boolean b) {
		isVisible = b;
	}
	public boolean isAlive() {
		return !destroyed;
	}
	public boolean isVisible() {
		return isVisible;
	}

	public void step(double scale) {
		coordinates.shift(0, -1*scale);
		position = position - scale;
	}

	public void draw(Graphics g) {
		if (!destroyed && isVisible) {
			coordinates.drawImage(g, image);
		}
	}

	public abstract String toString();
}