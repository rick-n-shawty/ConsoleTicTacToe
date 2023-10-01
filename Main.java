package TicTacToe;
import java.util.Scanner; 

public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static void main(String args[]){
        char[][] board = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
        };

        start(board);
    }
    public static void start(char[][] board){
        Scanner scanner = new Scanner(System.in);
        boolean isPlayerX = true; 
        boolean isGameOver = false;
        int input;  
        while(!isGameOver){
            char currentPlayer = isPlayerX ? 'X' : 'O'; 
            boolean isFreeSquare = true;
            display(board);
            System.out.println(PURPLE + "Player " + currentPlayer + " turn:" + RESET);
            // take input froom the player
            input = scanner.nextInt(); 
            if(input > 9 || input < 1){ 
                System.out.println(RED+"Please choose a valid input!!!"+RESET);
                continue; 
            }
            if(input >= 1 && input <= 3){
                isFreeSquare = (board[0][input - 1] != 'X' && board[0][input - 1] != 'O') ? true : false; 
                if(isFreeSquare){
                    board[0][input - 1] = currentPlayer;
                }else{
                    System.out.println(RED + "Square is already taken!!!" + RESET);
                    continue;
                }
            }else if(input >= 4 && input <= 6){
                isFreeSquare = (board[1][input - 4] != 'X' && board[1][input - 4] != 'O') ? true : false;
                if(isFreeSquare){
                    board[1][input - 4] = currentPlayer; 
                }else{
                    System.out.println(RED + "Square is already taken!!!" + RESET);
                    continue; 
                }
            }else if(input >= 7 && input <= 9){
                isFreeSquare = (board[2][input - 7] != 'X' && board[2][input - 7] != 'O') ? true : false;
                if(isFreeSquare){
                    board[2][input - 7] = currentPlayer; 
                }else{
                    System.out.println(RED + "Square is already taken!!!" + RESET);
                    continue; 
                }

            }



            // check for the win or a draw here!
            String state = checkState(board);
            if(state.charAt(0) == 'W'){
                isGameOver = true; 
                System.out.println(GREEN + state + RESET);
                display(board);
            }else if(state.charAt(0) == 'D'){
                isGameOver = true; 
                System.out.println(YELLOW + state + RESET);
            }

            isPlayerX = !isPlayerX; // toggle player's turn 
        }
    }
    public static String checkState(char[][] board){
        // first check for a win and only then check for a draw 
        int diagonalCountL = 0; 
        int diagonalCountR = 0; 
        int drawNum = 0; 
        for(int i = 0; i < board.length; i++){
            int horizontalCount = 0;
            int verticalCount = 0; 
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == 'X' || board[i][j] == 'O'){
                    drawNum +=1; 
                }
                if(board[j][i] == 'X'){
                    verticalCount += 10; 
                }else if(board[j][i] =='O'){
                    verticalCount += 100; 
                }
                if(board[i][j] == 'X'){
                    horizontalCount += 10; 
                }else if(board[i][j] == 'O'){
                    horizontalCount += 100;
                }
            }
            if(verticalCount == 300 || horizontalCount == 300){
                return "WIN BY PLAYER " + "'O'";
            }else if(verticalCount == 30 || horizontalCount == 30){
                return "WIN BY PLAYER " + "'X'";
            }

            if(board[i][i] == 'X'){
                diagonalCountL += 10; 
            }else if(board[i][i] == 'O'){
                diagonalCountL += 100; 
            }
            if(board[i][2 - i] == 'X'){
                diagonalCountR += 10; 
            }else if(board[i][2 - i] == 'O'){
                diagonalCountR += 100; 
            }
        }
        if(diagonalCountL == 30 || diagonalCountR == 30){
            return "WIN BY PLAYER " + "'X'"; 
        }else if(diagonalCountR == 300 || diagonalCountR == 300){
            return "WIN BY PLAYER " + "'O'"; 
        }

        return drawNum == 9 ? "DRAW" : "CONTINUE";
    }
    public static void display(char[][] board){
        for(int i = 0; i < board.length; i++){
            String row = "| ";
            System.out.println("|---|---|---|");
            for(int j = 0; j < board[i].length; j++){
                row+= board[i][j] + " | ";
            }
            System.out.println(row);
            if(i == 2){
                System.out.println("|---|---|---|");
            }
        }
    }
}
