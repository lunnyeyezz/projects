import java.util.Scanner;

public class project4 {
    public static void main(String[] args) {
        int[][] board = new int[3][3];
        int currentPlayer = 1;
        boolean gameWon = false;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Tic Tac Toe");
        displayBoard(board);

        for (int moves = 0; moves < 9; moves++) {
            boolean validMove = false;

            while (!validMove) {
                System.out.printf("Player %s, enter your move: ", currentPlayer == 1 ? "X" : "O");
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;

                if (isValidMove(board, row, col)) {
                    board[row][col] = currentPlayer;
                    validMove = true;
                } else {
                    System.out.println("Invalid move.Try again.");
                }
            }

            displayBoard(board);

            if (checkWin(board, currentPlayer)) {
                System.out.printf("Player %s wins!\n", currentPlayer == 1 ? "X" : "O");
                gameWon = true;
                break;
            }

            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }

        if (!gameWon) {
            System.out.println("Draw");
        }

        scanner.close();
    }

    public static void displayBoard(int[][] board) {
        System.out.println("Current board:");
        for (int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(board[i][j]==0) {
                    System.out.print(" ");
                } else {
                    System.out.print(board[i][j] == 1 ? "X" : "O");
                }
                if(j<2)System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("--+---+--");
        }
    }

    public static boolean isValidMove(int[][] board, int row, int col) {
        if(row<0||row>=3||col<0||col>=3){
            return false;
        }
        return board[row][col]==0;
    }

    public static boolean checkWin(int[][] board, int player) {
        for(int i=0;i<3;i++){
            if(board[i][0]==player && board[i][1]==player && board[i][2]==player) {
                return true;
            }
            if(board[0][i] == player && board[1][i]==player && board[2][i] == player) {
                return true;
            }
        }
        if(board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if(board[0][2] == player && board[1][1]==player && board[2][0]==player) {
            return true;
        }
        return false;
    }
}
