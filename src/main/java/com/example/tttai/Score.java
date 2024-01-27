package com.example.tttai;

public class Score {

    int score;
    int row;
    int col;

    public Score(int score, int row, int col) {
        this.score = score;
        this.row = row;
        this.col = col;
    }

    public int getScore() {
        return score;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
