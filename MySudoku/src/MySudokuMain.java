import java.io.IOException;

public class MySudokuMain {

	public static void main(String[] args) throws InterruptedException, IOException {		
		MySudokuStartView MySudokuStartWindowObj = new MySudokuStartView();
		MySudokuModel mySudokuModelObj = new MySudokuModel();
		MySudokuStartWindowObj.setVisible(true);
		new MySudokuController(MySudokuStartWindowObj, mySudokuModelObj);
	}

}
