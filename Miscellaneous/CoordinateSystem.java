import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

public class CoordinateSystem {
	private Image picture;
	private AffineTransform cTransform;

	public CoordinateSystem(int x, int y, Image i) {
		picture = i;
		cTransform = new AffineTransform();

		int w = picture.getWidth(null) / 2;
		int h = picture.getHeight(null) / 2;
		cTransform.translate(x - w, y - h);
	}

	public void shift(double x, double y) {
		cTransform.translate(x, y);
	}

	public void drawImage(Graphics g, Image i) {
		((Graphics2D)g).drawImage(i, cTransform, null);
	}
}