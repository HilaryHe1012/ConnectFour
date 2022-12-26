public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;
	/* 
	 * The board object must contain the board state in some manner.
	 * You must decide how you will do this.
	 * 
	 * You may add addition private/public methods to this class is you wish.
	 * However, you should use best OO practices. That is, you should not expose
	 * how the board is being implemented to other classes. Specifically, the
	 * Player classes.
	 * 
	 * You may add private and public methods if you wish. In fact, to achieve
	 * what the assignment is asking, you'll have to
	 * 
	 */
	private char[][] board = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
	public Board() {
	}
	
	public void printBoard() {
		for (int r = 0; r < NUM_OF_ROW; r++) {
			System.out.println("\n");
			System.out.print(" | ");
			for (int c = 0; c < NUM_OF_COLUMNS; c++){
				System.out.print(board[r][c]);
				System.out.print(" | ");
			}
		}
		System.out.println();
		System.out.println("------------------------");
	}
	
	public boolean containsWin() {
		return checkWin(board);
	}
	// helper function that checks the board
	private boolean checkWin(char[][] grid) {
		// checking cross
		for (int r = 0; r < NUM_OF_ROW; r++) {
			for (int c = 0; c < NUM_OF_COLUMNS - 3; c++) {
					if (grid[r][c] != 0 &&
					grid[r][c] == grid[r][c+1] && 
					grid[r][c] == grid[r][c+2] && 
					grid[r][c] == grid[r][c+3]) {
						return true;
					}
			}
		}

		// checking up and down
		for (int r = 0; r < NUM_OF_ROW - 3; r++) {
			for (int c = 0; c < NUM_OF_COLUMNS; c++) {
				if (grid[r][c] != 0 &&
					grid[r][c] == grid[r+1][c] &&
					grid[r][c] == grid[r+2][c] &&
					grid[r][c] == grid[r+3][c]) {
						return true;
				}
			}
		}
		// checking upward diagonal
		for (int r = 3; r < NUM_OF_ROW; r++) {
			for (int c = 0; c < NUM_OF_COLUMNS - 3; c++) {
				if (grid[r][c] != 0 &&
					grid[r][c] == grid[r-1][c+1] &&
					grid[r][c] == grid[r-2][c+2] &&
					grid[r][c] == grid[r-3][c+3]) {
						return true;
				}
			}
		}
		// checking downward diagonal
		for (int r = 0; r < NUM_OF_ROW - 3; r++) {
			for (int c = 0; c < NUM_OF_COLUMNS -3; c++) {
				if (grid[r][c] != 0 &&
					grid[r][c] == grid[r+1][c+1] &&
					grid[r][c] == grid[r+2][c+2] &&
					grid[r][c] == grid[r+3][c+3]) {
						return true;
				}
			}
		}
	return false;
	}

	
	public boolean isTie() {
		for (int c = 0; c < NUM_OF_COLUMNS; c++) {
			if ((!isColumnFull(c) || containsWin())) return false;
		}
		return true;
	}
	
	public void reset() {
		this.board = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
	}

	// determine if the column is full
	public boolean isColumnFull(int column) {
		return (board[0][column] != 0);
	}

	// "drop the piece"
	public void insertSymbol(int column, char symbol) {
		for (int r = NUM_OF_ROW - 1; r >= 0; r--) {
			if (board[r][column] == 0) {
				board[r][column] = symbol;
				break;
			}
		}
	}

	public int getRow() {return NUM_OF_ROW;}
	public int getCol() {return NUM_OF_COLUMNS;}

	// -------------------For AI-------------------------------
	// insert into temp in the "bestMove" method, temp is essentially a copy of the state board
	private void insert(int column, char[][] grid, char symbol) {
		for (int r = NUM_OF_ROW - 1; r >= 0; r--) {
			if (grid[r][column] == 0) {
				grid[r][column] = symbol;
				break;
			}
		}
	}
	// copy the state board for the "bestMove" method
	private char[][] copy() {
		char[][] temp = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
		for (int r = 0; r < NUM_OF_ROW; r++) {
			for (int c = 0; c < NUM_OF_COLUMNS; c++) {
				temp[r][c] = board[r][c];
			}
		}
		return temp;
	}

	// calculate the best move for AI
	public int bestMove(char symbol) {
		int score = 0, bestCol = -1, bestScore = 0;
		for (int r = 0; r < NUM_OF_ROW; r++) {
			for (int c = 0; c < NUM_OF_COLUMNS; c++) {
				if (!isColumnFull(c)) {
					char[][] temp = copy();
					insert(c, temp, symbol);
					if (checkWin(temp)) score += 100;
					if (score > bestScore) {
						bestScore = score;
						bestCol = c;
					}
				}
			
			}
		}
		return bestCol; 
	}

	// get opponent symbol
	private char getOpponentSymbol(char symbol) {
		for (int r = 0; r < NUM_OF_ROW; r++) {
			for (int c = 0; c < NUM_OF_COLUMNS; c++){
				if (board[r][c] != 0 && board[r][c] != symbol) {
					return board[r][c];
				}
			}
		}
		return 0;
	}

	// return the column for winning move using the "bestMove" method
	public int winningMove(char symbol) {
		int move = bestMove(symbol);
		return move;
	}

	// return the column for blocking the opponent using the "bestMove" method
	public int blockOpponent(char symbol) {
		char dropPiece = symbol;
		int move = -1;
		if (getOpponentSymbol(symbol) != 0) {
			dropPiece = getOpponentSymbol(symbol);
			move = bestMove(dropPiece);
		}
		return move;
	}


}
	


