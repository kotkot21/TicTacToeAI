package com.example.tttai;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class AIPlayer extends Player {

    static Alert alert;

    public AIPlayer(String name, String mark) {
        this.name = name;
        this.mark = mark;
    }

    @Override
    void makeMove(int row, int col) {
        int[] bestMove = BesMove(TicTacToe.board, mark);
        StringBuilder contentText = new StringBuilder("All Possibilities:\n");

        for (int i = 0; i < TicTacToe.board.length; i++) {
            for (int j = 0; j < TicTacToe.board[i].length; j++) {
                if (TicTacToe.board[i][j].getText().isEmpty()) {
                    TicTacToe.board[i][j].setText(mark);
                    int score = Minmax(TicTacToe.board, !mark.equals(mark));
                    TicTacToe.board[i][j].setText("");

                    if (score==-1){
                        contentText.append("Score is ").append(score).append(" and it will Lose")
                                .append(" on the Row ").append(i)
                                .append(",Col ").append(j)
                                .append("\n");
                    }
                    else if (score == 1){
                        contentText.append("Score is ").append(score).append(" and it will Win")
                                .append(" on the Row ").append(i)
                                .append(",Col ").append(j)
                                .append("\n");
                    }else if (score == 0){
                        contentText.append("Score is ").append(score).append(" and it will be a Draw")
                                .append(" on the Row ").append(i)
                                .append(",Col ").append(j)
                                .append("\n");
                    }

                }
            }
        }

        alert =  new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("All Possibilities");
        alert.setHeaderText(null);
        alert.setContentText(contentText.toString());
        alert.showAndWait();


        // Place the best move
        TicTacToe.placeMark(bestMove[0], bestMove[1], mark);
    }

    private int[] BesMove(Button[][] board, String player) {
        int[] bestMove = new int[]{-1, -1};
        int bestScore;
        if (player.equals(mark)) {
            bestScore = Integer.MIN_VALUE;
        } else {
            bestScore = Integer.MAX_VALUE;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getText().isEmpty()) {
                    board[i][j].setText(player);
                    int score = Minmax(board, !player.equals(mark));
                    board[i][j].setText("");

                    if ((player.equals(mark) && score > bestScore) || (!player.equals(mark) && score < bestScore)) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        return bestMove;
    }


    private int Minmax(Button[][] board, boolean isMaximizing) {
        if (TicTacToe.checkRowWin() || TicTacToe.checkColWin() || TicTacToe.checkDiagWin()) {
            if (isMaximizing) {
                return -1; // The maximizing player lost
            } else {
                return 1; // The minimizing player lost
            }
        }
        if (TicTacToe.checkDraw()) {
            return 0;
        }

        String currentPlayer;
        if (isMaximizing) {
            currentPlayer = mark;
        } else {
            if ("X".equals(mark)) {
                currentPlayer = "O";
            } else {
                currentPlayer = "X";
            }
        }

        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
        } else {
            bestScore = Integer.MAX_VALUE;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getText().isEmpty()) {
                    board[i][j].setText(currentPlayer);
                    int score = Minmax(board, !isMaximizing);
                    board[i][j].setText("");

                    if (isMaximizing) {
                        bestScore = Math.max(score, bestScore);
                    } else {
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }

        return bestScore;
    }
}