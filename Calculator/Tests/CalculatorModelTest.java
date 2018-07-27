import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorModelTest {
	private CalculatorModel CalculatorModelTestObject = new CalculatorModel(); 
	
	@Test
	void testOperation() {
		double inputNumber = 12.0;
		CalculatorModelTestObject.setOperationIndex(3);
		
		double expectedResult = 12.0;
		CalculatorModelTestObject.operation(inputNumber);
		double actualResult = CalculatorModelTestObject.getResult();
		
		assertEquals(expectedResult, actualResult);
		
		
		inputNumber = 9.0;
		CalculatorModelTestObject.setOperationIndex(7);
		
		expectedResult = 3.0;
		CalculatorModelTestObject.operation(inputNumber);
		actualResult = CalculatorModelTestObject.getResult();
		
		assertEquals(expectedResult, actualResult);
		
		
		inputNumber = 9.0;
		CalculatorModelTestObject.setOperationIndex(11);
		
		expectedResult = 27.0;
		CalculatorModelTestObject.operation(inputNumber);
		actualResult = CalculatorModelTestObject.getResult();
		
		assertEquals(expectedResult, actualResult);
		
		
		inputNumber = 3.0;
		CalculatorModelTestObject.setOperationIndex(15);
		
		expectedResult = 9.0;
		CalculatorModelTestObject.operation(inputNumber);
		actualResult = CalculatorModelTestObject.getResult();
		
		assertEquals(expectedResult, actualResult);
	}
	
	
	@Test
	void testAddDigit() {
		String inputNumberString = "1234";
		String digit = "5";
		
		String expectedResult = "12345";
		String actualResult = CalculatorModelTestObject.addDigit(inputNumberString, digit);
		
		assertEquals(expectedResult, actualResult);
		
		
		inputNumberString = "9";
		digit = "";
		
		expectedResult = "9";
		actualResult = CalculatorModelTestObject.addDigit(inputNumberString, digit);
		
		assertEquals(expectedResult, actualResult);
		
		
		inputNumberString = "";
		digit = "8";
		
		expectedResult = "8";
		actualResult = CalculatorModelTestObject.addDigit(inputNumberString, digit);
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void testOpposite() {
		String inputNumberString = "1234";		
		String expectedResult = "-1234";
		String actualResult = CalculatorModelTestObject.opposite(inputNumberString);
		
		assertEquals(expectedResult, actualResult);
		
		
		inputNumberString = "-1234";		
		expectedResult = "1234";
		actualResult = CalculatorModelTestObject.opposite(inputNumberString);
		
		assertEquals(expectedResult, actualResult);
		
		
		inputNumberString = "";	
		expectedResult = "";
		actualResult = CalculatorModelTestObject.opposite(inputNumberString);
		
		assertEquals(expectedResult, actualResult);
		
		
		inputNumberString = "0";	
		expectedResult = "0";
		actualResult = CalculatorModelTestObject.opposite(inputNumberString);
		
		assertEquals(expectedResult, actualResult);
	}
	
	
	@Test
	void testAddDot() {
		String inputNumberString = "1234";
		
		String expectedResult = "1234.";
		String actualResult = CalculatorModelTestObject.addDot(inputNumberString);
		
		assertEquals(expectedResult, actualResult);
		
		
		inputNumberString = "";
		
		expectedResult = "0.";
		actualResult = CalculatorModelTestObject.addDot(inputNumberString);
		
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testClearDigit() {
		String inputNumberString = "1234";		
		String expectedResult = "123";
		String actualResult = CalculatorModelTestObject.clearDigit(inputNumberString);
		
		assertEquals(expectedResult, actualResult);
		
		
		inputNumberString = "";		
		expectedResult = "";
		actualResult = CalculatorModelTestObject.clearDigit(inputNumberString);
		
		assertEquals(expectedResult, actualResult);
	}
}
