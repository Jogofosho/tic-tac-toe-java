/**
 * A program which runs a game of Tic-Tac-Toe.
 * @author: Joe Gonzales
 */

import javax.swing.JOptionPane;
import java.util.Scanner;

public class TicTacToe{
    
    public static void main (String[] args){
        play();
    }//main
    
    public static void play(){
        Board game = new Board();
        Scanner scanner = new Scanner(System.in);
        int endValue = -1;
        game.playGame();
        
        while (endValue != JOptionPane.NO_OPTION){
            endValue = JOptionPane.showConfirmDialog(null,
                            "Play again?",
                            "Tic-Tac-Toe",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
            if (endValue == JOptionPane.YES_OPTION){
                game.newGame();
                game.playGame();
            }
        }
    }//play
    
}//TicTacToe

class Board{
    //instance variables
    public char[][] board;
    public int movesMade;
    
    //constructor
    public Board(){
        board = new char[3][3];
        movesMade = 0;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = '-';
            }
        }
    }
    
    //instance methods
    public void newGame(){
        movesMade = 0;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = '-';
            }
        }
    }//newGame
    
    public void playGame(){
        Scanner scanner = new Scanner(System.in);
        while (!(endGame()) && movesMade != 9){
            System.out.println("Pick a row");
            int rowNum = -1;
            if (scanner.hasNextInt()){
                rowNum = scanner.nextInt();
            }
            System.out.println("Pick a column");
            int colNum = -1;
            if (scanner.hasNextInt()){
                colNum = scanner.nextInt();
            }
            System.out.println("X or O?");
            String move = scanner.next();
            move(rowNum,colNum,move.charAt(0));
            System.out.println(toString());
        }
        if (endGame()){
            System.out.println("We have a wiener!!!");
        } else {
            System.out.println("Nobody won this round!");
        }
    }//playGame
    
    public void move(int row, int col, char player){
        // since counting starts at 0, decrement player's entries
        row--;
        col--;
        
        if (!endGame()){
            if (movesMade != 9){
                if ((row < 3 && row > -1) && (col < 3 && col > -1)) {
                    if (player == 'X' || player == 'O'){
                        if (board[row][col] != 'X' && board[row][col] != 'O'){
                            board[row][col] = player;
                            movesMade++;
                            
                        } else {
                            System.out.println("Box already filled!");
                        }
                    } else {
                        System.out.println("Not a valid character. Use X or O.");
                    }
                } else {
                    System.out.println("Not a valid location.");
                }
            }
            endGame();
        } 
    }//move
    
    public String toString(){
        String toReturn = String.format("  %s","1 2 3")+"\n";
        
        for (int i = 0; i < board.length; i++){
            toReturn += ""+(i+1);
            for (int j = 0; j < board[i].length; j++){
                toReturn += String.format("%2c",board[i][j]);
            }
            toReturn += "\n";
        }
        
        return toReturn;
    }//toString
    
    public boolean endGame(){
        boolean winner = false;
        
        // check the columns
        for (int i = 0; i < board.length; i++){
            if ((board[i][0] != '-' && board[i][0] == board[i][1]) &&
                board[i][1] != '-' && board[i][1] == board[i][2]){
                    winner = true;
                }
        }
        // check the rows
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                if (board[0][j] != '-' && board[0][j] == board[1][j] &&
                board[1][j] != '-' && board[1][j] == board[2][j]){
                    winner = true;
                }
            }
        }
        // check the diagonals
        if ((board[0][0] != '-' && board[0][0] == board[1][1]) &&
            (board[1][1] != '-' && board[1][1] == board[2][2])){
                winner = true;
        } else if ((board[0][2] != '-' && board[0][2] == board[1][1]) &&
            (board[1][1] != '-' && board[1][1] == board[2][0])){
                winner = true;
        }
        
        return winner;
    }//endGame
}//Board
