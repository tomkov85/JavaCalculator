import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MySudokuStartView extends JFrame {
	private JPanel mainPanel, centerPanel;
	private JTextField usernameInput;
	private JComboBox<String> difficultyInput;
	private JButton startBtn;
	
	public MySudokuStartView() {
		super("MySudoku");
		setSize(500,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel(new BorderLayout());
		
		JLabel welcomeLabel = new JLabel("Welcome to my sudoku game! Please give your name and select the difficulity");
		mainPanel.add(welcomeLabel, BorderLayout.NORTH);;
		
		centerPanel = new JPanel(new GridLayout(4,1));
		
		JLabel nameLabel = new JLabel("name: ");
		centerPanel.add(nameLabel);
		
		usernameInput = new JTextField(15);
		centerPanel.add(usernameInput);
		
		JLabel difficultyLabel = new JLabel("difficulty: ");
		centerPanel.add(difficultyLabel);
		
		Vector<String> diffElements = new Vector<String>();
		diffElements.add("Easy");
		diffElements.add("Normal");
		diffElements.add("Hard");
		
		difficultyInput = new JComboBox<>(diffElements);
		centerPanel.add(difficultyInput);
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		startBtn = new JButton("Start");
		mainPanel.add(startBtn, BorderLayout.SOUTH);
		add(mainPanel);
	}
	
	public void addStartList(ActionListener al) {
		startBtn.addActionListener(al);
	}
	
	public String getUsername() {
		return usernameInput.getText();
	}
	
	public String getUserDifficulity() {
		return (String) difficultyInput.getSelectedItem();
	}
}
