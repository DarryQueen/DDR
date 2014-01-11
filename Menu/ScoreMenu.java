import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ScoreMenu extends JPanel implements ActionListener {
	private int score, maxCombo;
	private JButton goButton;
	private boolean goCheck, buttonCheck;

	//Drawing:
	private Image transition, scoreScreen;
	private int timeCount;

	public ScoreMenu(int s, int mC) {
		//Initialization:
		score = s;
		maxCombo = mC;
		//System.out.println(score + " " + maxCombo);

		setBackground(new Color(17, 53, 99));
		transition = (new ImageIcon("Graphics/Menu Screens/Screen Clear.gif")).getImage();
		scoreScreen = (new ImageIcon("Graphics/Menu Screens/Score Screen.png")).getImage();
		transition.flush();

		//Graphics:
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

		add(Box.createVerticalGlue());
		goButton = new JButton("Go!");
		goButton.setActionCommand("goButton");
		goButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		goButton.addActionListener(this);
		goButton.setFocusable(false);
		add(goButton);

		buttonCheck = true;
	}

	public String update() {
		timeCount++;
		repaint();
		if (goCheck) {
			return "Proceed!";
		} else if (score == -1) {
			return "Incorrect file name!";
		}
		return null;
	}

	public void actionPerformed(ActionEvent e) {
		if ("goButton".equals(e.getActionCommand())) {
			goCheck = true;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (timeCount >= 1800/RunMethod.DELAY) {
			setBackground(new Color(235, 214, 255));
			g.drawImage(scoreScreen, 0, 0, this);

			//Score painting:
			g.setFont(new Font("Monospaced", Font.PLAIN, 20));
			g.drawString(score + "", 325, 485);
			g.drawString(maxCombo + "", 325, 521);
		} else {
			g.drawImage(transition, 0, 0, this);
		}
	}
}