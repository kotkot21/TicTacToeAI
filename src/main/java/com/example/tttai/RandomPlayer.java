package com.example.tttai;

import java.util.Random;

public class RandomPlayer extends Player {

    public RandomPlayer(String name, String mark) {
        this.name = name;
        this.mark = mark;
    }

    void makeMove(int row, int col) {
        do {
            Random random = new Random();
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!isValidMove(row, col));
        TicTacToe.placeMark(row, col, mark);
    }

}
