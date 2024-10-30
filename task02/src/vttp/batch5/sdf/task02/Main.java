package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {

		// Check for correct parameter
		if ((args.length < 1) || (args.length > 1)) {
			System.out.println(
					"Please provide TTT board as a parameter on the command line. Note: There should only be 1 parameter.");
		}

		// Read TTT/board.txt
		String fileInput = args[0];
		System.out.println("Processing: " + fileInput);
		File tttBoardConfig = new File(fileInput);

		ArrayList<String> boardRows = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(tttBoardConfig))) {
			String line;
			while ((line = reader.readLine()) != null) {
				boardRows.add(line);
			}
		}

		String row0 = boardRows.get(0);
		String row1 = boardRows.get(1);
		String row2 = boardRows.get(2);
		char[] charArray0 = row0.toCharArray();
		char[] charArray1 = row1.toCharArray();
		char[] charArray2 = row2.toCharArray();

		// Create TicTacToe object
		TicTacToe game = new TicTacToe();

		// Create empty board
		game.createBoard();

		// Copy TTT file board
		game.board[0][0] = charArray0[0];
		game.board[0][1] = charArray0[1];
		game.board[0][2] = charArray0[2];
		game.board[1][0] = charArray1[0];
		game.board[1][1] = charArray1[1];
		game.board[1][2] = charArray1[2];
		game.board[2][0] = charArray2[0];
		game.board[2][1] = charArray2[1];
		game.board[2][2] = charArray2[2];

		// Print board
		game.printBoard();

		// Calculate utility
		game.utility();
	}
}
