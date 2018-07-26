import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MySudokuResultView extends JFrame {
	private JPanel mainPanel, centerPanel;
	private JLabel resultText;
	private JButton startBtn, resetBtn, settingsBtn, quitBtn;
	private boolean wining;

	public MySudokuResultView() {
		super("MySudoku");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel(new BorderLayout());
		centerPanel = new JPanel(new FlowLayout((int) RIGHT_ALIGNMENT));
		resultText = new JLabel();

		if (this.wining) {
			resultText.setText("Bravo, you win!");
		} else {
			resultText.setText("Sorry, you lose!");
			startBtn = new JButton("Try it again");
			centerPanel.add(startBtn);
		}
		mainPanel.add(resultText, BorderLayout.NORTH);

		resetBtn = new JButton("New game");
		centerPanel.add(resetBtn);

		settingsBtn = new JButton("Change settings");
		centerPanel.add(settingsBtn);

		quitBtn = new JButton("Quit");
		centerPanel.add(quitBtn);

		mainPanel.add(centerPanel);
		add(mainPanel);
	}

	public void resWinAddListeners(ActionListener al) {
		startBtn.addActionListener(al);
		resetBtn.addActionListener(al);
		settingsBtn.addActionListener(al);
		quitBtn.addActionListener(al);
	}

	public JButton getQuitBtn() {
		return quitBtn;
	}

	public JButton getStartBtn() {
		return startBtn;
	}

	public JButton getSettingsBtn() {
		return settingsBtn;
	}

	public JButton getResetBtn() {
		return resetBtn;
	}

	public void setWining(boolean wining) {
		this.wining = wining;
	}
}
