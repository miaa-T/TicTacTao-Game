package solutions;

import java.util.Scanner;
import core_algorithms.Minimax;
import problems.Game;
import problems.TicTacToe;

public class TicTacToePlayer  extends Minimax<char [][] , int []>{
   public TicTacToePlayer(int size){
       super(new TicTacToe(size, TicTacToe.Marks.O), false);
   }
   public void play (   int boardSize ){
       Scanner scanner = new Scanner(System.in);



       TicTacToe.Marks startingPlayer = TicTacToe.Marks.X;
       TicTacToe ticTacToeGame = new TicTacToe(boardSize, startingPlayer);


       Minimax<char[][], int[]> minimax = new Minimax<>(ticTacToeGame, true);

       //while we have not reached a terminal state:
       while (!ticTacToeGame.isTerminal(ticTacToeGame.getBoard())) {

           printBoard(ticTacToeGame.getBoard());  //print the current board

           // Let the human player input (row, column)
           System.out.println("Enter your move (row and column, separated by space):");
           int row = scanner.nextInt();
           int column = scanner.nextInt();
           int[] humanMove = {row, column};
           ticTacToeGame.execute(humanMove, ticTacToeGame.getBoard());
           printBoard(ticTacToeGame.getBoard());
           //System.out.println("Heeeeeereeeee");
           // Check if human move results in a terminal state
           if (ticTacToeGame.isTerminal(ticTacToeGame.getBoard())) {
               printBoard(ticTacToeGame.getBoard());
               announceWinner(ticTacToeGame.utility(ticTacToeGame.getBoard()));
               break;
           }
           // Run the Minimax search to find the best action
           int[] bestAction = minimax.minimaxSearch(ticTacToeGame.getBoard());
           printBoard(ticTacToeGame.getBoard());
           ticTacToeGame.execute(bestAction, ticTacToeGame.getBoard());

           // Check if computer move results in a terminal state
           if (ticTacToeGame.isTerminal(ticTacToeGame.getBoard())) {
               printBoard(ticTacToeGame.getBoard());
               announceWinner(ticTacToeGame.utility(ticTacToeGame.getBoard()));
               break;
           }
       }
    }
    // Method to print the Tic Tac Toe board
    private static void printBoard(char[][] board) {
        System.out.println("Current Board:");
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // Method to announce the winner or draw
    private static void announceWinner(int utility) {
        if (utility == 1) {
            System.out.println("Player X wins!");
        } else if (utility == -1) {
            System.out.println("Player O wins!");
        } else {
            System.out.println("draw!");
        }
    }
    public static void main(String[] args) {
        int boardSize = 3;
        TicTacToePlayer player = new TicTacToePlayer(boardSize);
        player.play( boardSize);

    }


}
