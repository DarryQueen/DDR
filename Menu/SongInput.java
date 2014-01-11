import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SongInput extends JFrame implements ActionListener {
	private JTextField songInput, fileInput, tempoInput;
	private String song, file;
	private int tempo;
	private boolean inputCheck;
	private JButton goButton;

	public SongInput() {
		super("Custom Song");
		setBounds(550, 350, 300, 160);

		songInput = new JTextField("Song File", 20);
		fileInput = new JTextField("Steps File", 20);
		tempoInput = new JTextField("Tempo", 20);

		goButton = new JButton("Confirm!");
		goButton.setActionCommand("go");
		goButton.addActionListener(this);
		goButton.setFocusable(false);

		Box box1 = Box.createVerticalBox();
		box1.add(songInput);
		box1.add(Box.createVerticalStrut(10));
		box1.add(fileInput);
		box1.add(Box.createVerticalStrut(10));
		box1.add(tempoInput);
		box1.add(Box.createVerticalStrut(10));
		box1.add(goButton);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(box1);

		setVisible(false);
		setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {
		if ("go".equals(e.getActionCommand())) {
			song = songInput.getText();
			file = fileInput.getText();
			try {
				tempo = Integer.parseInt(tempoInput.getText());
			} catch (NumberFormatException ex) {
				tempo = 0;
			}

			if (song != null && file != null && tempo > 0) {
				inputCheck = true;
			}
		}
	}

	public String update() {
		if (inputCheck) {
			setVisible(false);
			return "Checked!";
		}

		return null;
	}
	public String getSong() {
		return song;
	}
	public String getFile() {
		return file;
	}
	public int getTempo() {
		return tempo;
	}

	public static void main(String[] args) {
		SongInput window = new SongInput();
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(false);
	}
}