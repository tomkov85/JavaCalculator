
public class CalculatorModel {
	private double result;
	private int operationIndex;

	public CalculatorModel() {
		this.result = 0;
		this.operationIndex = 3;
	}

	public String addDigit(String actualNumber, String digit) {
		return actualNumber + digit;
	}

	public String operationExecute(int inputOperationIndex, String inputNumberString) {
		String operationResult = inputNumberString;

		double inputNumber = Double.parseDouble(inputNumberString);
		operation(inputNumber);
		if (inputOperationIndex == 12) {
			operationResult = addDot(inputNumberString);
		} else if (inputOperationIndex == 14) {
			operationResult = opposite(inputNumberString);
		} else {
			operationResult = "";
			operation(inputNumber);
		}

		if (inputOperationIndex == 16) {
			operationResult = this.result + "";
			if (operationResult.substring(operationResult.length() - 2).equals(".0")) {
				operationResult = operationResult.substring(0, operationResult.length() - 2);
			}
			this.operationIndex = 3;
			this.result = 0;
		} else {
			this.operationIndex = inputOperationIndex;
		}

		return operationResult;
	}

	public void operation(double inputNumber) {
		switch (operationIndex) {
		case 3:
			this.result += inputNumber;
			break;
		case 7:
			this.result -= inputNumber;
			break;
		case 11:
			this.result *= inputNumber;
			break;
		case 15:
			this.result /= inputNumber;
			break;
		default:
			break;
		}
	}

	public String addDot(String actualNumber) {
		if (actualNumber.equals("")) {
			return "0.";
		} else if (actualNumber.indexOf(".") == -1) {
			return actualNumber + ".";
		}
		return actualNumber;
	}

	public String opposite(String inputNumberString) {
		if (!inputNumberString.equals("") & !inputNumberString.equals("0")) {
			if (Double.parseDouble(inputNumberString) > 0) {
				return "-" + inputNumberString;
			} else {
				return inputNumberString.substring(1);
			}
		} else {
			return inputNumberString;
		}
	}

	public String clearDigit(String inputNumber) {
		if (!inputNumber.equals("")) {
			return inputNumber.substring(0, inputNumber.length() - 1);
		} else {
			return inputNumber;
		}
	}

	public double getResult() {
		return result;
	}

	public void setOperationIndex(int operationIndex) {
		this.operationIndex = operationIndex;
	}
}
