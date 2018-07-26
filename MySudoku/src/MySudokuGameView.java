import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MySudokuGameView extends JFrame {
	private JPanel mainPanel, headerPanel, gamePanel, footerPanel, gameGridPanel;
	private GridLayout gameGridLayout;

	private JTextField[][] gameFields;
	private JButton[] numberButtons;

	JMenuBar mySudokuMenu;
	JMenu gameMenu, optionsMenu, exitMenu;
	JMenuItem newGameMenuItem, saveGameMenuItem, loadGameMenuItem, settingsMenuItem, exitMenuItem;

	JLabel viewMistakes;

	public MySudokuGameView() {
		super("MySudoku");
		setSize(550, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel(new BorderLayout());

		headerPanel = new JPanel(new BorderLayout());
		mainPanel.add(headerPanel, BorderLayout.NORTH);

		mySudokuMenu = new JMenuBar();
		headerPanel.add(mySudokuMenu);

		gameMenu = new JMenu("Game");
		mySudokuMenu.add(gameMenu);

		optionsMenu = new JMenu("Options");
		mySudokuMenu.add(optionsMenu);

		exitMenu = new JMenu("Exit");
		mySudokuMenu.add(exitMenu);

		newGameMenuItem = new JMenuItem("New game");
		gameMenu.add(newGameMenuItem);

		saveGameMenuItem = new JMenuItem("Save game");
		gameMenu.add(saveGameMenuItem);

		loadGameMenuItem = new JMenuItem("Load game");
		gameMenu.add(loadGameMenuItem);

		settingsMenuItem = new JMenuItem("Settings");
		optionsMenu.add(settingsMenuItem);

		exitMenuItem = new JMenuItem("Exit");
		exitMenu.add(exitMenuItem);

		viewMistakes = new JLabel("Mistakes: 0");
		headerPanel.add(viewMistakes, BorderLayout.EAST);

		gameGridLayout = new GridLayout(3, 3);
		gamePanel = new JPanel(gameGridLayout);
		mainPanel.add(gamePanel, BorderLayout.CENTER);

		gameFields = new JTextField[9][9];
		for (int i = 0; i < gameFields.length; i++) {
			gameGridPanel = new JPanel(gameGridLayout);
			for (int j = 0; j < gameFields[i].length; j++) {
				gameFields[i][j] = new JTextField();
				gameFields[i][j].setHorizontalAlignment((int) CENTER_ALIGNMENT);
				gameFields[i][j].setBorder(BorderFactory.createLineBorder(Color.gray, 1));
				gameFields[i][j].setFont(new Font("Verdana", 1, 16));
				gameGridPanel.add(gameFields[i][j]);
			}
			gameGridPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			gamePanel.add(gameGridPanel);
		}

		footerPanel = new JPanel(new GridLayout(1, 9, 4, 2));
		numberButtons = new JButton[9];
		for (int i = 0; i < numberButtons.length; i++) {
			numberButtons[i] = new JButton((i + 1) + "");
			footerPanel.add(numberButtons[i]);
		}

		mainPanel.add(footerPanel, BorderLayout.SOUTH);

		add(mainPanel);
	}

	public JTextField getField(int i, int j) {
		return gameFields[i][j];
	}

	public void setFieldValue(JTextField currentField, String newValue) {
		currentField.setText(newValue);
	}

	public JButton getNumberButton(int i) {
		return numberButtons[i];
	}

	public void setMistakes(int newValue) {
		viewMistakes.setText("Mistakes: " + newValue);
	}

	public void addListeners(ActionListener al, KeyListener kl, FocusListener fl) {
		for (int i = 0; i < gameFields.length; i++) {
			for (int j = 0; j < gameFields.length; j++) {
				gameFields[i][j].addKeyListener(kl);
				gameFields[i][j].addFocusListener(fl);
			}
		}

		for (int i = 0; i < numberButtons.length; i++) {
			numberButtons[i].addActionListener(al);
		}

		newGameMenuItem.addActionListener(al);
		saveGameMenuItem.addActionListener(al);
		loadGameMenuItem.addActionListener(al);
		settingsMenuItem.addActionListener(al);
		exitMenuItem.addActionListener(al);
	}

	public JMenuItem getNewGameMI() {
		return newGameMenuItem;
	}

	public JMenuItem saveGameMI() {
		return saveGameMenuItem;
	}

	public JMenuItem loadGameMI() {
		return loadGameMenuItem;
	}

	public JMenuItem getSettingsMI() {
		return settingsMenuItem;
	}

	public JMenuItem getExitMI() {
		return exitMenuItem;
	}

	public JPanel getGamePanel() {
		return gamePanel;
	}

}
