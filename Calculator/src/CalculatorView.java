import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel, buttonPanel;

	private JButton[] buttonContainer;
	private String[] buttonNames;

	private JTextField IOLine;

	public CalculatorView() {
		super("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(270,250);
		
		mainPanel = new JPanel(new BorderLayout());

		IOLine = new JTextField();
		mainPanel.add(IOLine, BorderLayout.NORTH);
		IOLine.setFocusable(false);

		buttonPanel = new JPanel(new GridLayout(4, 4));
		mainPanel.add(buttonPanel, BorderLayout.CENTER);

		buttonNames = new String[] { "1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "*", ".", "0", "+/-", "/",
				"=" };
		buttonContainer = new JButton[buttonNames.length];
		for (int i = 0; i < buttonContainer.length - 1; i++) {
			buttonContainer[i] = new JButton(buttonNames[i]);
			buttonPanel.add(buttonContainer[i]);
		}

		buttonContainer[buttonNames.length - 1] = new JButton(buttonNames[buttonNames.length - 1]);
		mainPanel.add(buttonContainer[buttonNames.length - 1], BorderLayout.SOUTH);

		add(mainPanel);
		this.requestFocus();
		
	}

	public void setActionlistners(ActionListener a) {
		for (int i = 0; i < buttonContainer.length; i++) {
			buttonContainer[i].addActionListener(a);
		}
	}

	public void setKeyListener(KeyListener k) {
		this.addKeyListener(k);
	}

	public String getIOLineValue() {
		return IOLine.getText();
	}

	public void setIOLine(String newText) {
		IOLine.setText(newText);
	}

	public JButton getButton(int i) {
		return buttonContainer[i];
	}
}
