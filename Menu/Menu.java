import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Menu extends JPanel implements ActionListener {
	private JButton b1, b2, b3, b4, b5;
	private String fileName, songName;
	private double tempo;
	private Image openingScreen;

	private SongInput customMenu;
	private boolean customVisibility;

	public Menu() {
		customMenu = new SongInput();
		openingScreen = (new ImageIcon("Graphics/Menu Screens/Introduction.png")).getImage();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

		add(Box.createVerticalGlue());
		b1 = new JButton("Play!");
		b1.setActionCommand("b1");
		b1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		b1.addActionListener(this);
		b1.setFocusable(false);
		add(b1);

		add(Box.createRigidArea(new Dimension(0, 10)));
		b2 = new JButton("Play!");
		b2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		b2.setActionCommand("b2");
		b2.addActionListener(this);
		b2.setFocusable(false);
		add(b2);

		add(Box.createRigidArea(new Dimension(0, 10)));
		b3 = new JButton("Play!");
		b3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		b3.setActionCommand("b3");
		b3.addActionListener(this);
		b3.setFocusable(false);
		add(b3);

		add(Box.createRigidArea(new Dimension(0, 10)));
		b4 = new JButton("Play!");
		b4.setAlignmentX(Component.RIGHT_ALIGNMENT);
		b4.setActionCommand("b4");
		b4.addActionListener(this);
		b4.setFocusable(false);
		add(b4);

		add(Box.createRigidArea(new Dimension(0, 10)));
		b5 = new JButton("Play!");
		b5.setAlignmentX(Component.RIGHT_ALIGNMENT);
		b5.setActionCommand("b5");
		b5.addActionListener(this);
		b5.setFocusable(false);
		add(b5);
	}

	public void paintComponent(Graphics g) {
		/*g.setFont(new Font("Monospaced", Font.PLAIN, 20));
		g.drawString("Be For U - Dive", 10, 535);
		g.drawString("DJ Taka - .59", 10, 505);
		g.drawString("STM 200 - Paranoia Eternal", 10, 475);
		g.drawString("Be For U - Break Down", 10, 445);*/
		super.paintComponent(g);
		g.drawImage(openingScreen, 0, 0, this);
	}

	public void actionPerformed(ActionEvent e) {
		if ("b1".equals(e.getActionCommand())) {
			fileName = "Music/Break Down.txt";
			songName = "Music/DDR - Break Down.wav";
			tempo = 190;
		} else if ("b2".equals(e.getActionCommand())) {
			fileName = "Music/Paranoia.txt";
			songName = "Music/DDR - Paranoia Eternal.wav";
			tempo = 200;
		} else if ("b3".equals(e.getActionCommand())) {
			fileName = "Music/.59.txt";
			songName = "Music/DDR - .59.wav";
			tempo = 135;
		} else if ("b4".equals(e.getActionCommand())) {
			fileName = "Music/Dive.txt";
			songName = "Music/DDR - Dive.wav";
			tempo = 155;
		} else if ("b5".equals(e.getActionCommand())) {
			customVisibility = true;
			customMenu.setVisible(customVisibility);
		}
	}
	public String update() {
		if (customVisibility) {
			customVisibility = (customMenu.update() == null);
			if (!customVisibility) {
				fileName = customMenu.getFile();
				songName = customMenu.getSong();
				tempo = customMenu.getTempo();
			}
		}
		if (fileName == null || songName == null || tempo <= 0) {
			return null;
		} else {
			return "Song selected.";
		}
	}

	//Accessors:
	public String getFile() {
		return fileName;
	}
	public String getSong() {
		return songName;
	}
	public double getTempo() {
		return tempo;
	}
}