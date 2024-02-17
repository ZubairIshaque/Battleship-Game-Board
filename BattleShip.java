import java.util.Arrays;
import java.util.Scanner;

public class BattleShip {
	private char[][] board;
	private int boardSize = 20;

	public BattleShip() {
		board = new char[boardSize][boardSize];
		for (char[] row : board) {
			Arrays.fill(row, '0');
		}
	}

	public void printBoard() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				System.out.print(board[i][j] + " "); 
			}
			System.out.println();
		}
	}

	public boolean placeShip(int x, int y, String type, String direction) {
		if (x < 0 || x >= boardSize || y < 0 || y >= boardSize) {
			System.out.println("Invalid starting coordinates. Ship placement failed.");
			return false;
		}

		int shipLength = 0;
		switch (type) {
		case "Battleship":
			shipLength = 8;
			break;
		case "Destroyer":
			shipLength = 2;
			break;
		case "Cruiser":
			shipLength = 4;
			break;
		default:
			System.out.println("Invalid ship type. Ship placement failed.");
			return false;
		}

		if (direction.equals("vertical")) {
			if (x + shipLength > boardSize) {
				System.out.println("Ship placement runs off the edge of the board. Ship placement failed.");
				return false;
			}
			for (int i = x; i < x + shipLength; i++) {
				if (board[i][y] == '-') {
					System.out.println("Ships overlap. Ship placement failed.");
					return false;
				}
				board[i][y] = '-';
			}
		} else if (direction.equals("horizontal")) {
			if (y + shipLength > boardSize) {
				System.out.println("Ship placement runs off the edge of the board. Ship placement failed.");
				return false;
			}
			for (int j = y; j < y + shipLength; j++) {
				if (board[x][j] == '-') {
					System.out.println("Ships overlap. Ship placement failed.");
					return false;
				}
				board[x][j] = '-';
			}
		} else {
			System.out.println("Invalid direction. Ship placement failed.");
			return false;
		}
		return true;
	}

	public void fire(int x, int y) {
		if (x < 0 || x >= boardSize || y < 0 || y >= boardSize) {
			System.out.println("Invalid coordinates. Fire failed.");
		} else if (board[x][y] == '-') {
			System.out.println("Hit!");
			board[x][y] = 'X';
		} else {
			System.out.println("Miss!");
			board[x][y] = '?';
		}
	}

	public static void main(String[] args) {
		BattleShip game = new BattleShip();
		game.createBoard();

		game.placeShip(0, 0, "Battleship", "horizontal");
		game.placeShip(5, 5, "Destroyer", "vertical");
		game.placeShip(10, 10, "Cruiser", "horizontal");
		game.placeShip(12, 12, "Cruiser", "vertical");

		System.out.println("Initial Board:");
		game.printBoard();

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Enter target coordinates (x y): ");
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			System.out.println("Firing at (" + x + ", " + y + "):");
			game.fire(x, y);
			game.printBoard();
		}
	}

	private void createBoard() {
		// Initialize the game board
		for (char[] row : board) {
			Arrays.fill(row, '0');
		}
	}
}

