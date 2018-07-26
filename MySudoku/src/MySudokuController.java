import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MySudokuController {
	private JTextField currentField;
	private String nickname, difficulty;
	private int currenti, currentj;
	protected int mistakes = 0, correctNumberCounter = 0, temp;
	private int[][] currentGame;
	private boolean saveLoadStatus;
	private MySudokuGameView MySudokuViewObj;
	private MySudokuResultView MySudokuResultWindowObj;
	private MySudokuSaveLoadView mySudokuSaveLoadViewObj;

	public MySudokuController(MySudokuStartView MySudokuStartWindowObj, MySudokuModel mySudokuModelObj) {

		MySudokuStartWindowObj.addStartList(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (MySudokuStartWindowObj.getUsername().equals("")) {
					nickname = "John Doe";
				} else {
					nickname = MySudokuStartWindowObj.getUsername();
				}

				difficulty = MySudokuStartWindowObj.getUserDifficulity();
				MySudokuStartWindowObj.setVisible(false);
				MySudokuStartWindowObj.dispose();
				if (MySudokuViewObj == null) {
					setGameWindow(MySudokuStartWindowObj, mySudokuModelObj);
				}
				MySudokuViewObj.setVisible(true);
				startNewGame(MySudokuViewObj, mySudokuModelObj, true);
			}
		});

	}

	private void startNewGame(MySudokuGameView msView, MySudokuModel msModelObj, boolean RandomIndex) {
		this.currentGame = new int[9][9];
		this.currentField = msView.getField(0, 0);
		this.currenti = 0;
		this.currentj = 0;
		this.correctNumberCounter = 0;
		this.mistakes = 0;

		msView.setVisible(true);
		msView.setTitle("MySudoku" + "    players name: " + nickname + ", difficulty: " + difficulty);
		try {
			msModelObj.setGame(difficulty, RandomIndex);
		} catch (IOException e) {

			e.printStackTrace();
		}

		fillSquares(msView, msModelObj);

	}

	private void fillSquares(MySudokuGameView  msView, MySudokuModel msModelObj) {
		this.currentGame = msModelObj.getGame();

		for (int i = 0; i < this.currentGame.length; i++) {
			for (int j = 0; j < this.currentGame[0].length; j++) {
				if (this.currentGame[i][j] != 0) {
					msView.getField(i, j).setText(this.currentGame[i][j] + "");
					msView.getField(i, j).setFocusable(false);
					msView.getField(i, j).setBackground(Color.WHITE);
					correctNumberCounter++;
				} else {
					msView.getField(i, j).setText("");
					msView.getField(i, j).setBackground(Color.WHITE);
					msView.getField(i, j).setFocusable(true);
				}
			}
		}

		msView.setMistakes(mistakes);
	}

	private boolean checkNumber(int ci, int cj) {
		int number = this.currentGame[ci][cj];
		for (int j = 0; j < this.currentGame.length; j++) {
			if (number == this.currentGame[ci][j]) {
				if (j != cj) {
					return false;
				}
			}
		}

		for (int i = ((int) ci / 3) * 3; i < ((int) ci / 3) * 3 + 3; i++) {
			for (int j = ((int) cj / 3) * 3; j < ((int) cj / 3) * 3 + 3; j++) {
				if (number == this.currentGame[i][j]) {
					if (i != ci) {
						return false;
					}
				}
			}
		}

		for (int i = ci % 3; i <= ci % 3 + 6; i += 3) {
			for (int j = cj % 3; j <= cj % 3 + 6; j += 3) {
				if (number == this.currentGame[i][j]) {
					if (i != ci) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private void setGameWindow(MySudokuStartView MySudokuStartWindowObj, MySudokuModel mySudokuModelObj) {
		MySudokuViewObj = new MySudokuGameView ();

		MySudokuViewObj.addListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				for (int i = 0; i < 9; i++) {
					if (ae.getSource() == MySudokuViewObj.getNumberButton(i)) {
						MySudokuViewObj.setFieldValue(currentField, (i + 1) + "");
						currentField.grabFocus();
						break;
					}
				}
				if (ae.getSource() == MySudokuViewObj.getNewGameMI()) {
					startNewGame(MySudokuViewObj, mySudokuModelObj, true);
				} else if (ae.getSource() == MySudokuViewObj.saveGameMI()) {
					saveLoadStatus = true;
					if (mySudokuSaveLoadViewObj == null) {
						setSaveLoadWindow(MySudokuStartWindowObj, mySudokuModelObj);
					}
					mySudokuSaveLoadViewObj.saveloadWindow(saveLoadStatus);
					mySudokuSaveLoadViewObj.setVisible(true);
				} else if (ae.getSource() == MySudokuViewObj.loadGameMI()) {
					saveLoadStatus = false;
					if (mySudokuSaveLoadViewObj == null) {
						setSaveLoadWindow(MySudokuStartWindowObj, mySudokuModelObj);
					}
					mySudokuSaveLoadViewObj.saveloadWindow(saveLoadStatus);
					mySudokuSaveLoadViewObj.setVisible(true);
				} else if (ae.getSource() == MySudokuViewObj.getSettingsMI()) {
					MySudokuViewObj.setVisible(false);
					MySudokuStartWindowObj.setVisible(true);
				} else if (ae.getSource() == MySudokuViewObj.getExitMI()) {
					MySudokuViewObj.setVisible(false);
					MySudokuViewObj.dispose();
				}
			}
		}, new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent ke) {
				MySudokuViewObj.setFieldValue(currentField, "");
				if (ke.getKeyCode() == KeyEvent.VK_1) {
					MySudokuViewObj.setFieldValue(currentField, "1");
				} else if (ke.getKeyCode() == KeyEvent.VK_2) {
					MySudokuViewObj.setFieldValue(currentField, "2");
				} else if (ke.getKeyCode() == KeyEvent.VK_3) {
					MySudokuViewObj.setFieldValue(currentField, "3");
				} else if (ke.getKeyCode() == KeyEvent.VK_4) {
					MySudokuViewObj.setFieldValue(currentField, "4");
				} else if (ke.getKeyCode() == KeyEvent.VK_5) {
					MySudokuViewObj.setFieldValue(currentField, "5");
				} else if (ke.getKeyCode() == KeyEvent.VK_6) {
					MySudokuViewObj.setFieldValue(currentField, "6");
				} else if (ke.getKeyCode() == KeyEvent.VK_7) {
					MySudokuViewObj.setFieldValue(currentField, "7");
				} else if (ke.getKeyCode() == KeyEvent.VK_8) {
					MySudokuViewObj.setFieldValue(currentField, "8");
				} else if (ke.getKeyCode() == KeyEvent.VK_9) {
					MySudokuViewObj.setFieldValue(currentField, "9");
				} else {
					MySudokuViewObj.setFieldValue(currentField, "");
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

		}, new FocusListener() {

			@Override
			public void focusLost(FocusEvent fll) {
				if (!currentField.getText().equals("")) {
					temp = currentGame[currenti][currentj];
					currentGame[currenti][currentj] = Integer.parseInt(currentField.getText());
					if (checkNumber(currenti, currentj)) {
						currentField.setBackground(Color.white);
						if (temp == 0) {
							correctNumberCounter++;
							if (correctNumberCounter == 81) {
								MySudokuViewObj.setVisible(false);
								if (MySudokuResultWindowObj == null) {
									setResultWindow(MySudokuStartWindowObj, mySudokuModelObj);
								}
								MySudokuResultWindowObj.setVisible(true);
							}
						}
					} else {
						mistakes++;
						MySudokuViewObj.setMistakes(mistakes);
						currentField.setBackground(Color.RED);
						currentGame[currenti][currentj] = 0;
						if (mistakes > 3) {
							MySudokuViewObj.setVisible(false);
							if (MySudokuResultWindowObj == null) {
								setResultWindow(MySudokuStartWindowObj, mySudokuModelObj);
							}
							MySudokuResultWindowObj.setVisible(true);
						}
					}
				} else {
					currentField.setBackground(Color.white);
				}
			}

			@Override
			public void focusGained(FocusEvent flg) {
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (MySudokuViewObj.getField(i, j) == flg.getSource()) {
							currentField = MySudokuViewObj.getField(i, j);
							currentField.setBackground(Color.YELLOW);
							currenti = i;
							currentj = j;
							break;
						}
					}
				}
			}
		});
	}

	private void setResultWindow(MySudokuStartView MySudokuStartWindowObj, MySudokuModel mySudokuModelObj) {
		MySudokuResultWindowObj = new MySudokuResultView();
		MySudokuResultWindowObj.resWinAddListeners(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				if (ae.getSource().equals(MySudokuResultWindowObj.getStartBtn())) {
					MySudokuResultWindowObj.setVisible(false);
					MySudokuViewObj.setVisible(true);
					startNewGame(MySudokuViewObj, mySudokuModelObj, false);
				} else if (ae.getSource().equals(MySudokuResultWindowObj.getResetBtn())) {
					MySudokuResultWindowObj.setVisible(false);
					MySudokuViewObj.setVisible(true);
					startNewGame(MySudokuViewObj, mySudokuModelObj, true);
				} else if (ae.getSource().equals(MySudokuResultWindowObj.getSettingsBtn())) {
					MySudokuResultWindowObj.setVisible(false);
					MySudokuStartWindowObj.setVisible(true);
				} else if (ae.getSource().equals(MySudokuResultWindowObj.getQuitBtn())) {
					MySudokuResultWindowObj.setVisible(true);
					MySudokuResultWindowObj.dispose();
				}
			}
		});
	}

	private void setSaveLoadWindow(MySudokuStartView MySudokuStartWindowObj, MySudokuModel mySudokuModelObj) {
		mySudokuSaveLoadViewObj = new MySudokuSaveLoadView();
		mySudokuSaveLoadViewObj.setList(mySudokuModelObj.getSaveFiles());
		mySudokuSaveLoadViewObj.addListeners(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				if (mySudokuSaveLoadViewObj.getSaveLoadBtn().equals(ae.getSource())) {
					String url = "save\\" + mySudokuSaveLoadViewObj.getSaveInpuField().getText() + ".dat";
					if (saveLoadStatus) {
						try {
							mySudokuModelObj.saveGame(url, nickname, difficulty, mistakes, currentGame);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						mySudokuSaveLoadViewObj.setList(mySudokuModelObj.getSaveFiles());
						mySudokuSaveLoadViewObj.setVisible(false);
					} else {
						try {
							String[] loadArray = mySudokuModelObj.loadGame(url);
							MySudokuViewObj.setTitle(
									"MySudoku" + "    players name: " + loadArray[0] + ", difficulty: " + loadArray[1]);
							mistakes = Integer.parseInt(loadArray[2]);
							fillSquares(MySudokuViewObj, mySudokuModelObj);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						mySudokuSaveLoadViewObj.setList(mySudokuModelObj.getSaveFiles());
						mySudokuSaveLoadViewObj.setVisible(false);
					}

				} else if (mySudokuSaveLoadViewObj.getCancelBtn().equals(ae.getSource())) {
					mySudokuSaveLoadViewObj.setVisible(false);
				} else if (mySudokuSaveLoadViewObj.getDeleteBtn().equals(ae.getSource())) {
					String url = "save\\" + mySudokuSaveLoadViewObj.getSaveInpuField().getText() + ".dat";
					mySudokuModelObj.deleteSaveGame(url);
					mySudokuSaveLoadViewObj.setList(mySudokuModelObj.getSaveFiles());
					mySudokuSaveLoadViewObj.setVisible(true);
				}
			}

		}, new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent ll) {
				mySudokuSaveLoadViewObj.getSaveInpuField().setText(mySudokuSaveLoadViewObj.getListValue());
			}
		});
	}
}
