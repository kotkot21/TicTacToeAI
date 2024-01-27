package com.example.tttai;


import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TicTacToe {
    static Button[][] board;
   private double width = 100;
    private double height = 100;

    public TicTacToe() {
        board = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Button();
                board[i][j].setPrefSize(width, height);
                board[i][j].setLayoutX(j * width);
                board[i][j].setLayoutY(i * height);
                board[i][j].setBackground(Background.fill(Color.valueOf("#bfd0cc")));
                board[i][j].setBorder(Border.stroke(Color.BLACK));
            }
        }
    }


    public void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    static void placeMark(int row, int col, String mark) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            board[row][col].setText(mark);
            board[row][col].setFont(Font.font(24));
            board[row][col].setDisable(true);
            board[row][col].setBackground(Background.fill(Color.GREY));
        } else
            System.out.println("Invalid Position");
    }
    static void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                TicTacToe.board[i][j].setText("");
                TicTacToe.board[i][j].setDisable(false);
            }
        }
    }

    static boolean checkColWin() {
        for (int j = 0; j <= 2; j++) {
            if (!board[0][j].getText().isEmpty() && board[0][j].getText() == board[1][j].getText()
                    && board[1][j].getText() == board[2][j].getText()) {
                return true;
            }
        }
        return false;
    }

    static boolean checkRowWin() {
        for (int i = 0; i <= 2; i++) {
            if (!board[i][0].getText().isEmpty() && board[i][0].getText() == board[i][1].getText()
                    && board[i][1].getText() == board[i][2].getText()) {
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagWin() {
        if ((!board[0][0].getText().isEmpty() && board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText()) ||
                (!board[0][2].getText().isEmpty() && board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText())) {
            return true;
        }
        return false;
    }

    static String retcharWin() {
        String winner = "n";
        for (int j = 0; j <= 2; j++) {
            if (!board[0][j].getText().isEmpty() && board[0][j].getText() == board[1][j].getText()
                    && board[1][j].getText() == board[2][j].getText()) {
                winner = board[0][j].getText();
            }
        }
        for (int i = 0; i <= 2; i++) {
            if (!board[i][0].getText().isEmpty()&& board[i][0].getText() == board[i][1].getText()
                    && board[i][1].getText() == board[i][2].getText()) {
                winner = board[i][0].getText();
            }
        }
        if (!board[0][0].getText().isEmpty() && board[0][0].getText() == board[1][1].getText()
                && board[1][1].getText() == board[2][2].getText()) {
            winner = board[0][0].getText();
        }
        if (!board[0][2].getText().isEmpty() &&
                board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText()) {
            winner = board[0][2].getText();
        }
        if (checkDraw()) {
            winner = "t";
        }
        return winner;
    }

    static boolean checkDraw() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (board[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col].getText().isEmpty();
    }
    public boolean isWinner() {
        return checkRowWin() || checkColWin() || checkDiagWin();
    }
}