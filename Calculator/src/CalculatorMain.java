
public class CalculatorMain {

	public static void main(String[] args) {
		CalculatorView cv = new CalculatorView();
		cv.setVisible(true);
		CalculatorModel cm = new CalculatorModel();
		new CalculatorController(cv, cm);
	}
}
