import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

public class MySudokuSaveLoadView extends JFrame {
	private JPanel slMainPanel, slHeaderPanel, slCenterPanel, slBtnPanel;
	private JLabel headText;
	private JList<String> files;
	private JButton saveLoadBtn, cancelBtn, deleteBtn;
	private JTextField saveInpuField;

	public MySudokuSaveLoadView() {
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("MySudoku");

		slMainPanel = new JPanel(new BorderLayout());

		slHeaderPanel = new JPanel(new FlowLayout((int) RIGHT_ALIGNMENT));
		headText = new JLabel("Save game");
		slHeaderPanel.add(headText);

		slMainPanel.add(slHeaderPanel, BorderLayout.NORTH);

		slCenterPanel = new JPanel(new BorderLayout());
		saveInpuField = new JTextField();
		slCenterPanel.add(saveInpuField, BorderLayout.SOUTH);

		files = new JList<String>();
		slCenterPanel.add(files);
		slMainPanel.add(slCenterPanel, BorderLayout.CENTER);

		slBtnPanel = new JPanel(new FlowLayout());
		slMainPanel.add(slBtnPanel, BorderLayout.SOUTH);

		saveLoadBtn = new JButton("Save");
		slBtnPanel.add(saveLoadBtn);

		deleteBtn = new JButton("Delete");
		slBtnPanel.add(deleteBtn);

		cancelBtn = new JButton("Cancel");
		slBtnPanel.add(cancelBtn);

		add(slMainPanel);
	}

	public void addListeners(ActionListener al, ListSelectionListener ll) {
		saveLoadBtn.addActionListener(al);
		cancelBtn.addActionListener(al);
		deleteBtn.addActionListener(al);
		files.addListSelectionListener(ll);
	}

	public JButton getSaveLoadBtn() {
		return saveLoadBtn;
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	public JButton getCancelBtn() {
		return cancelBtn;
	}

	public String getListValue() {
		return files.getSelectedValue();
	}

	public void setList(Vector<String> List) {
		slCenterPanel.removeAll();
		files = new JList<String>(List);
		slCenterPanel.add(files);
		saveInpuField = new JTextField();
		slCenterPanel.add(saveInpuField, BorderLayout.SOUTH);
		slMainPanel.add(slCenterPanel, BorderLayout.CENTER);
	}

	public JTextField getSaveInpuField() {
		return saveInpuField;
	}

	public void saveloadWindow(boolean saveLoad) {
		if (saveLoad) {
			headText.setText("Save game");
			saveLoadBtn.setText("Save");
		} else {
			headText.setText("Load game");
			saveLoadBtn.setText("Load");
		}
	}
}
