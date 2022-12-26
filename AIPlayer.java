import java.util.Random;
public class AIPlayer extends Player{
    
    public AIPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    public void makeMove(Board board) {
        /*  
        * (1) if there is one or more winning moves available, the AI player will make one of them
        * (2) if there is no winning move available, but there opponent has one or more winning moves available for the next turn,
        * the AI player will block one of them
        */ 
        // generate random move
        int nextMove;
        do {
            Random rand = new Random();
            nextMove = rand.nextInt(board.getCol());
            if (board.winningMove(symbol) != -1) {
                nextMove = board.winningMove(symbol);
                System.out.println("Winning Move: " + (nextMove + 1));
            }  
            if (board.blockOpponent(symbol) != -1) {
                nextMove = board.blockOpponent(symbol);
                System.out.println("Blocking Move: " + (nextMove + 1));
            } else {
                System.out.println("Random Move:" + (nextMove + 1));
            }
            System.out.print(getName() + ", has entered the move: " + (nextMove + 1));
        } while(board.isColumnFull(nextMove));


        // insert the symbol corresponding the the random move
        board.insertSymbol(nextMove, symbol);
    }

}
