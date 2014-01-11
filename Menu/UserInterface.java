import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;

public class UserInterface extends JPanel implements KeyListener {
	//Assets:
	private Song song;

	//Time counters:
	private double duration;
	private int kill1, kill2, kill3, kill4, timeCount;
	private boolean keyDown1, keyDown2, keyDown3, keyDown4;

	//Properties:
	private int receptorY = 80, bounds = 30;
	private String accuracy;
	private int score;
	private int combo, maxCombo, comboTimer;
	private boolean comboBreak;

	//Receptors:
	private Receptor leftR = new LeftReceptor(), downR = new DownReceptor(), upR = new UpReceptor(), rightR = new RightReceptor();

	//Flashers:
	private Image image1, image2, image3, image4;
	private CoordinateSystem coordinates1, coordinates2, coordinates3, coordinates4;
	private boolean songPlaying, killNote1, killNote2, killNote3, killNote4;

	public UserInterface(String fileName, String songName, double tempo) {
		setBackground(new Color(17, 53, 99));
		song = new Song(fileName, 120/(1000/RunMethod.DELAY*60/tempo));
		try {
			double d = MusicPlayer.play(songName);
			if (d == 0) {
				duration = 0;
				score = -1;
			} else {
				duration = d*1000/RunMethod.DELAY;
				//duration = 10;
			}
		} catch (IOException e) {
		} catch (InterruptedException e) {
		}
		songPlaying = true;

		//Images:
		image1 = (new ImageIcon("Graphics/Left Destroyed.png")).getImage();
		image2 = (new ImageIcon("Graphics/Down Destroyed.png")).getImage();
		image3 = (new ImageIcon("Graphics/Up Destroyed.png")).getImage();
		image4 = (new ImageIcon("Graphics/Right Destroyed.png")).getImage();

		//Coordinates:
		coordinates1 = new CoordinateSystem(91, 80, image1);
		coordinates2 = new CoordinateSystem(230, 80, image2);
		coordinates3 = new CoordinateSystem(370, 80, image3);
		coordinates4 = new CoordinateSystem(509, 80, image4);

		addKeyListener(this);
	}

	public String update() {
		String response = song.act();
		if (response != null) {
			if (response.equals("Note killed.")) {
				accuracy = "Boo!";
				maxCombo = Math.max(maxCombo, combo);
				if (combo > 30)
					comboBreak = true;
				combo = 0;
			}
		}
		timeCount++;
		if (timeCount >= duration) {
			return "Song finished!";
		}
		repaint();
		return null;
	}

	private boolean collisionCheck(String key) {
		int index = song.getNote(key, receptorY - bounds);
		Note note = song.get(index);

		if (note != null && note.isVisible() && accuracyCheck(note) && note.toString().equals(key)) {
			song.killNote(index);
			return true;
		}
		return false;
	}
	private boolean accuracyCheck(Note note) {
		double mLimits = bounds/7.0, pLimits = 2.0*bounds/3.0;
		combo++;

		if (note.getPosition() <= receptorY + mLimits && note.getPosition() >= receptorY - mLimits) {
			accuracy = "Marvellous";
			score = score + combo*4;
			return true;
		} else if (note.getPosition() <= receptorY + pLimits && note.getPosition() >= receptorY - pLimits) {
			accuracy = "Perfect";
			score = score + combo*2;
			return true;
		} else if (note.getPosition() <= receptorY + bounds && note.getPosition() >= receptorY - bounds) {
			accuracy = "Great";
			score = score + combo;
			return true;
		}

		maxCombo = Math.max(maxCombo, combo - 1);
		combo = Math.max(0, combo - 2);
		accuracy = "Miss";
		return false;
	}

	private void lightUp(String key) {
		if (key.toString().equals("a")) {
			killNote1 = true;
		} else if (key.toString().equals("s")) {
			killNote2 = true;
		} else if (key.toString().equals("w")) {
			killNote3 = true;
		} else if (key.toString().equals("d")) {
			killNote4 = true;
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (!keyDown1 && collisionCheck("a")) {
				lightUp("a");
			} else {
				leftR.keyPress();
			}
			keyDown1 = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (!keyDown2 && collisionCheck("s")) {
				lightUp("s");
			} else {
				downR.keyPress();
			}
			keyDown2 = true;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (!keyDown3 && collisionCheck("w")) {
				lightUp("w");
			} else {
				upR.keyPress();
			}
			keyDown3 = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (!keyDown4 && collisionCheck("d")) {
				lightUp("d");
			} else {
				rightR.keyPress();
			}
			keyDown4 = true;
		}
	}
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftR.keyRelease();
			keyDown1 = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downR.keyRelease();
			keyDown2 = false;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			upR.keyRelease();
			keyDown3 = false;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightR.keyRelease();
			keyDown4 = false;
		}
	}

	public void keyTyped(KeyEvent e) {}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		leftR.draw(g);
		downR.draw(g);
		upR.draw(g);
		rightR.draw(g);
		song.draw(g);

		//Time-based:
		destroy(g);
		drawAccuracy(g);
		drawComboBreak(g);

		//Markers:
		g.setColor(Color.WHITE);
		//g.drawLine(0, highBound - 31, 600, highBound - 31);
		//g.drawLine(0, lowBound + 31, 600, lowBound + 31);
	}

	//Accessors:
	public int getScore() {
		return score;
	}
	public int getMaxCombo() {
		return maxCombo;
	}

	//Draw methods:
	private void destroy(Graphics g) {
		if (killNote1) {
			kill1++;
			if (kill1 < 5) {
				coordinates1.drawImage(g, image1);
			} else {
				kill1 = 0;
				killNote1 = false;
			}
		}
		if (killNote2) {
			kill2++;
			if (kill2 < 5) {
				coordinates2.drawImage(g, image2);
			} else {
				kill2 = 0;
				killNote2 = false;
			}
		}
		if (killNote3) {
			kill3++;
			if (kill3 < 5) {
				coordinates3.drawImage(g, image3);
			} else {
				kill3 = 0;
				killNote3 = false;
			}
		}
		if (killNote4) {
			kill4++;
			if (kill4 < 5) {
				coordinates4.drawImage(g, image4);
			} else {
				kill4 = 0;
				killNote4 = false;
			}
		}
	}
	private void drawAccuracy(Graphics g) {
		Image image = null;

		if (accuracy != null) {
			if (accuracy.equals("Marvellous"))
				image = (new ImageIcon("Graphics/Text/Marvellous.png")).getImage();
			if (accuracy.equals("Perfect"))
				image = (new ImageIcon("Graphics/Text/Perfect.png")).getImage();
			if (accuracy.equals("Great"))
				image = (new ImageIcon("Graphics/Text/Great.png")).getImage();
			if (accuracy.equals("Miss"))
				image = (new ImageIcon("Graphics/Text/Miss.png")).getImage();
			if (accuracy.equals("Boo!"))
				image = (new ImageIcon("Graphics/Text/Boo.png")).getImage();
		}
		if (image != null)
			g.drawImage(image, 300 - image.getWidth(null)/2, 300 - image.getHeight(null)/2, null);
	}
	private void drawComboBreak(Graphics g) {
		if (comboBreak && comboTimer < 200) {
			Image image = (new ImageIcon("Graphics/Text/Combo Broken.png")).getImage();
			g.drawImage(image, 300 - image.getWidth(null)/2, 350 - image.getHeight(null)/2, null);
			comboTimer++;
		} else if (comboBreak) {
			comboTimer = 0;
			comboBreak = false;
		}
	}
}