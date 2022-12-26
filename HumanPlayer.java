import java.util.Scanner;
public class HumanPlayer extends Player {
    
    public HumanPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);

    }
    public void makeMove(Board board){
        int columnNum;
        Scanner userIn = new Scanner(System.in);
            do {  
                // get user input
                System.out.print(getName() + ", please input your move: ");
                int move = userIn.nextInt();
                columnNum = move - 1;
            } while (board.isColumnFull(columnNum));
        // put the char in
        board.insertSymbol(columnNum, symbol);
    } 
}
        

