package com.example.tttai;

import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, String mark) {
        this.name = name;
        this.mark = mark;
    }

    @Override
    void makeMove(int row, int col) {
        TicTacToe.placeMark(row, col, mark);
    }
}