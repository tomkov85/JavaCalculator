import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

public class MySudokuModel {
	private int[][] base;
	private int gameIndex;
	private FileInputStream fis;
	
	public MySudokuModel() {
		this.base = new int[9][9];
		this.gameIndex = 0;
	}
	
	public void setGame(String difficulty, boolean randomIndex) throws IOException {
		
		File gameFile = new File("easy.dat");
		if (difficulty.equals("Easy")) {
			gameFile = new File("easyGames.dat");
		} else if(difficulty.equals("Normal")) {
			gameFile = new File("normalGames.dat");
		} else { 
			gameFile = new File("hardGames.dat");
		}	
		
		fis = new FileInputStream(gameFile);
		if (randomIndex) {
			int gameNumber = fis.read();		
			this.gameIndex = (int) (Math.random() * gameNumber + 10);
		} else {
			fis.read();
		}
		
		//DirectoryStream<String> dis = new DirectoryStream<>("C:\\Users\\Artisan\\eclipse-workspace\\MySudoku\\save");

		while(this.gameIndex != fis.read()) {
			fis.read();
		}
		fillArray(fis);
	}
	
	public int[][] getGame() {
		return this.base;
	}
	
	public void fillArray(FileInputStream fis) throws IOException {
		for (int i = 0; i < this.base.length; i++) {
			for (int j = 0; j < base[i].length; j++) {
				this.base[i][j] = fis.read();
			}		
		}
		fis.close();
	}
	
	public void saveGame(String url, String nickname, String difficulty, int actualMistakesNumber, int[][] actualGame) throws IOException {
		FileOutputStream fos = new FileOutputStream(url);
		
		for (int i = 0; i < nickname.length(); i++) {
			fos.write(nickname.codePointAt(i));
		}
		fos.write(",".codePointAt(0));
		
		for (int i = 0; i < difficulty.length(); i++) {
			fos.write(difficulty.codePointAt(i));
		}
		fos.write(",".codePointAt(0));
		
		fos.write(actualMistakesNumber);
		for (int i = 0; i < actualGame.length; i++) {
			for (int j = 0; j < actualGame[i].length; j++) {
				fos.write(actualGame[i][j]);
			}
		}
		
		fos.flush();
		fos.close();
	}
	
	public String[] loadGame(String url) throws IOException {
		fis = new FileInputStream(url);
		String[] resultArray = new String[3];
		
		StringBuilder sb = new StringBuilder();
		int ch;
		while((ch = fis.read()) != 44) {
			sb.append((char)ch);
		}
		resultArray[0] = sb.toString();
		
		sb = new StringBuilder();
		while((ch = fis.read()) != 44) {
			sb.append((char)ch);
		}
		resultArray[1] = sb.toString();
		
		
		resultArray[2] = fis.read() + "";
		fillArray(fis);
		return resultArray;
	}
	
	public Vector<String> getSaveFiles() {
		File url = new File("save");
		Vector<String> saveFileList = new Vector<>();
		if (url.isDirectory()) {
			for(File saveFiles: url.listFiles())
				saveFileList.add(saveFiles.getName().substring(0, saveFiles.getName().length() - 4));
		}
		return saveFileList;
	}
	
	public void deleteSaveGame(String url) {
		File file = new File(url);
		file.delete();
	}
	
	public void setNewGameData(String url) throws IOException {
		FileOutputStream fos = new FileOutputStream(url);
		
		fos.write(3);
		fos.write(10);
		int[][] matrix = {{5,0,7,0,4,0,3,0,0},{0,0,0,8,1,0,0,4,5},{0,0,0,0,0,0,0,7,0},{1,0,0,0,3,4,9,0,0},{0,0,2,0,0,0,3,0,0},{0,0,7,9,6,0,0,0,2},{0,6,0,0,0,0,0,0,0},{1,3,0,0,2,4,0,0,0},{0,0,4,0,3,0,6,0,9}}; 
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				fos.write(matrix[i][j]);
			}
		}
		fos.write(11);
		int[][] matrix1 = {{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}}; 
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				fos.write(matrix1[i][j]);
			}
		}
		fos.write(12);
		int[][] matrix2 = {{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}}; 
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				fos.write(matrix2[i][j]);
			}
		}
		
		// int[][] matrix = {{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}}; 
		fos.flush();
		fos.close();
	}
}
