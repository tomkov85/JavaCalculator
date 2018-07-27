import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

public class CalculatorController {
	CalculatorView theView;
	CalculatorModel theModel;

	public CalculatorController(CalculatorView theView, CalculatorModel theModel) {
		this.theView = theView;
		this.theModel = theModel;
		theView.requestFocus();
		int[] numberButtonIndex = { 0, 1, 2, 4, 5, 6, 8, 9, 10, 13 };
		int[] operationButtonIndex = { 3, 7, 11, 12, 14, 15, 16 };
		theView.setActionlistners(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean buttonUsed = false;
				theView.requestFocus();
				for (int i = 0; i < numberButtonIndex.length; i++) {
					if (e.getSource() == theView.getButton(numberButtonIndex[i])) {
						theView.setIOLine(theModel.addDigit(theView.getIOLineValue(),
								theView.getButton(numberButtonIndex[i]).getText()));
						buttonUsed = true;
						break;
					}
				}
				if (!buttonUsed & !theView.getIOLineValue().equals("")) {
					for (int j = 0; j < operationButtonIndex.length; j++) {
						if (e.getSource() == theView.getButton(operationButtonIndex[j])) {
							theView.setIOLine(
									theModel.operationExecute(operationButtonIndex[j], theView.getIOLineValue()));
							break;
						}
					}
				}
			}
		});

		theView.setKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent k) {
				switch (k.getKeyCode()) {
				case KeyEvent.VK_0:
					theView.setIOLine(theView.getIOLineValue() + "0");
					break;
				case KeyEvent.VK_1:
					theView.setIOLine(theView.getIOLineValue() + "1");
					break;
				case KeyEvent.VK_2:
					theView.setIOLine(theView.getIOLineValue() + "2");
					break;
				case KeyEvent.VK_3:
					theView.setIOLine(theView.getIOLineValue() + "3");
					break;
				case KeyEvent.VK_4:
					theView.setIOLine(theView.getIOLineValue() + "4");
					break;
				case KeyEvent.VK_5:
					theView.setIOLine(theView.getIOLineValue() + "5");
					break;
				case KeyEvent.VK_6:
					theView.setIOLine(theView.getIOLineValue() + "6");
					break;
				case KeyEvent.VK_7:
					theView.setIOLine(theView.getIOLineValue() + "7");
					break;
				case KeyEvent.VK_8:
					theView.setIOLine(theView.getIOLineValue() + "8");
					break;
				case KeyEvent.VK_9:
					theView.setIOLine(theView.getIOLineValue() + "9");
					break;
				case KeyEvent.VK_MINUS:
					theView.setIOLine(theModel.opposite(theView.getIOLineValue()));
					break;
				case KeyEvent.VK_BACK_SPACE:
					theView.setIOLine(theModel.clearDigit(theView.getIOLineValue()));
					break;
				case 46:
					theView.setIOLine(theModel.addDot(theView.getIOLineValue()));
					break;
				default:
					break;
				}
			}
		});
	}

}
