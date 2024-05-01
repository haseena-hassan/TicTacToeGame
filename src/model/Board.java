package model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public int size;
    public PlayingPiece[][] board;


    public Board(int boardSize) {
        size = boardSize;
        board = new PlayingPiece[size][size];
    }


    public void printBoard() {
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(board[i][j] != null) {
                    System.out.print(board[i][j].piecetype.name() + "\t" + "|");
                }
                else {
                    System.out.print(" " + "\t" + "|");
                }
            }
            System.out.println();
        }
    }


    public List<Pair<Integer, Integer>> getFreeCells() {
        List<Pair<Integer, Integer>> freeCells = new ArrayList<>();
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(this.board[i][j] == null) {
                    Pair<Integer, Integer> cellRowCol = new Pair<>(i, j);
                    freeCells.add(cellRowCol);
                }
            }
        }
        return freeCells;
    }


    public boolean placePiece(int row, int col, PlayingPiece piece) {
        if(this.board[row][col] == null) {
            this.board[row][col] = piece;
            return true;
        }
        return false;
    }
}

















