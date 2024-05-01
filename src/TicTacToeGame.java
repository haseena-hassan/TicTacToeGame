import model.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javafx.util.Pair;

public class TicTacToeGame {
    Deque<Player> players;
    Board gameBoard;

    TicTacToeGame() {
        initializeGame();
    }

    public void initializeGame() {
        gameBoard = new Board(3);
        players = new LinkedList<>();

        PlayingPieceX crossPiece = new PlayingPieceX();
        Player player1 = new Player("Player1", crossPiece);

        PlayingPieceO doughnutPiece = new PlayingPieceO();
        Player player2 = new Player("Player2", doughnutPiece);

        players.add(player1);
        players.add(player2);
    }

    public String startGame() {
        boolean noWinner = true;
        while(noWinner) {

            // Print Board
            gameBoard.printBoard();

            // Get free cells and check for tie
            List<Pair<Integer, Integer>> freeCells = gameBoard.getFreeCells();
            if(freeCells.isEmpty()) {
                noWinner = false;
                continue;
            }

            // Get the player with current turn and take input
            Player currentPlayer = players.removeFirst();
            System.out.println("Player: " + currentPlayer.name + " - Enter the row,colum : ");
            Scanner inputScanner = new Scanner(System.in);
            String cellInputs = inputScanner.nextLine();
            String[] cellValues = cellInputs.split(",");
            int inputRow = Integer.parseInt(cellValues[0]);
            int inputCol = Integer.parseInt(cellValues[1]);

            // Place the piece and validate
            boolean piecePlacedSuccessfully = gameBoard.placePiece(inputRow, inputCol, currentPlayer.playingPiece);
            if(!piecePlacedSuccessfully) {
                System.out.println("Incorrect position chosen - Try Again!");
                players.addFirst(currentPlayer);
                continue;
            }
            players.addLast(currentPlayer);

            // Check if current move make a winner
            boolean winner = isPlayerWinner(inputRow, inputCol, currentPlayer.playingPiece.piecetype);
            if(winner) {
                return currentPlayer.name;
            }
        }
        return "tie";
    }

    boolean isPlayerWinner(int row, int col, PieceType piece) {
        boolean rowCheck = true;
        boolean colCheck = true;
        boolean diagonalCheck = true;
        boolean antiDiagonalCheck = true;

        // Check row for any match
        for(int i=0; i<gameBoard.size; i++) {
            if(gameBoard.board[row][i] == null || gameBoard.board[row][i].piecetype != piece) {
                rowCheck = false;
                break;
            }
        }

        // Check column for any match
        for(int i=0; i<gameBoard.size; i++) {
            if(gameBoard.board[i][col] == null || gameBoard.board[i][col].piecetype != piece) {
                colCheck = false;
                break;
            }
        }

        // Check diagonal for any match
        for(int i=0, j=0; i<gameBoard.size; i++, j++) {
            if(gameBoard.board[i][j] == null || gameBoard.board[i][j].piecetype != piece) {
                diagonalCheck = false;
                break;
            }
        }

        // Check row for any match
        for(int i=0, j=gameBoard.size-1; i<gameBoard.size; i++, j--) {
            if(gameBoard.board[i][j] == null || gameBoard.board[i][j].piecetype != piece) {
                antiDiagonalCheck = false;
                break;
            }
        }

        return rowCheck || colCheck || diagonalCheck || antiDiagonalCheck ;
    }
}




















