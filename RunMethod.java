import javax.swing.*;
import java.awt.*;

public class RunMethod extends JFrame {
	private Menu menu;
	private UserInterface ui;
	private ScoreMenu scoreMenu;
	private String response;

	//Time:
	private long startTime;
	public static final long DELAY = 20;
	private int timeCount = 0;
	private int slowCount;

	public RunMethod() {
		super("Dance Dance Revolution:");
		setBounds(500, 300, 600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(false);
		setVisible(true);
	}

	//Time:
	public void updateTime() {
		timeCount = 0;
		startTime = System.currentTimeMillis();
	}
	public void sleep(long delay) {
		timeCount++;
		long waitTime = (startTime + timeCount*delay) - System.currentTimeMillis();
		if (waitTime > 0) {
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {}
		} else {
			slowCount++;
			if (slowCount >= 20) {
				System.out.println("Your system is too slow and will experience some lag.");
			}
			Thread.yield();
		}
	}

	public void run() {
		while(true) {
			//Menu:
			menu = new Menu();
			setContentPane(menu);
			((JComponent)getContentPane()).revalidate();

			response = menu.update();
			updateTime();
			while(response == null) {
				response = menu.update();
				sleep(DELAY);
			}

			ui = new UserInterface(menu.getFile(), menu.getSong(), menu.getTempo());
			addKeyListener(ui);
			setContentPane(ui);
			((JComponent)getContentPane()).revalidate();

			//Time frame:
			response = ui.update();
			updateTime();
			while (response == null) {
				response = ui.update();
				sleep(DELAY);
			}
			ui.setVisible(false);

			scoreMenu = new ScoreMenu(ui.getScore(), ui.getMaxCombo());
			//addKeyListener(scoreMenu);
			setContentPane(scoreMenu);
			((JComponent)getContentPane()).revalidate();

			//Score menu:
			updateTime();
			response = scoreMenu.update();
			while (response == null) {
				response = scoreMenu.update();
				sleep(DELAY);
			}
			scoreMenu.setVisible(false);
		}
	}

	public static void main(String[] args) {
		RunMethod run = new RunMethod();
		run.run();
	}
}