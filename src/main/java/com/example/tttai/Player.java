package com.example.tttai;

abstract class Player {
    String name;
    String mark;

    abstract void makeMove(int row, int col);

    boolean isValidMove(int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (TicTacToe.board[row][col].getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}