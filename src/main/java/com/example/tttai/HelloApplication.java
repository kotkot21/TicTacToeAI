package com.example.tttai;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.DefaultProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.security.auth.callback.TextInputCallback;
import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    TicTacToe ticTacToe = new TicTacToe();

    Player p1;
    Player p2;
    Player cp;
    Pane gameBoard = new Pane();
    int coun = 0;
    Button[][] btnpos = new Button[3][3];
    double width = 100;
    double height = 100;
    boolean hasWinner = false;
    boolean isplayerA = true;
    Label lbldis = new Label();
    Label title = new Label();
    Label SetTile = new Label();
    Label counterhum = new Label();
    Label countcomp = new Label();

    Button newGamebtn = new Button("New Game");
    Button quitbtn = new Button("Quit");
    BorderPane border;
    TextField txt = new TextField();

    Label pName1 = new Label("First Player Name");
    TextField player1 = new TextField();
    Label pName2 = new Label("Second Player Name");
    TextField player2 = new TextField();
    static Line line;
    private int currentRound = 1;
    int numberOfRounds;
    int compcounter = 0;
    int humcounter = 0;

    @Override
    public void start(Stage stage) throws IOException {

        ToggleGroup toggle = new ToggleGroup();
        RadioButton pVp = new RadioButton("Player vs Player");
        pVp.setToggleGroup(toggle);
        RadioButton EZ = new RadioButton("Easy");
        EZ.setToggleGroup(toggle);
        RadioButton AI = new RadioButton("Expert");
        AI.setToggleGroup(toggle);
        pVp.setSelected(true);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(pVp, EZ, AI);
        Label counter1 = new Label();
        Label counter2 = new Label();

        VBox vbox = new VBox();



        vbox.getChildren().addAll(pName1, player1, pName2, player2);

        String[] c = new String[2];
        c[0] = "Player";
        c[1] = "Computer";

        ChoiceBox<String> choice = new ChoiceBox<>();
        choice.getItems().addAll(c);
        choice.setValue(c[0]);
        choice.setDisable(true);

        pVp.setOnAction(e -> {
            choice.setDisable(true);
            pName2.setOpacity(1);

        });

        EZ.setOnAction(e -> {
            choice.setDisable(false);
            player2.setDisable(true);
            pName2.setOpacity(0.5);
        });

        AI.setOnAction(e -> {
            player2.setDisable(true);
            choice.setDisable(false);
            pName2.setOpacity(0.5);

        });

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                gameBoard.getChildren().add(TicTacToe.board[i][j]);
            }
        }
        gameBoard.setDisable(true);


        Button startPlaying = new Button("Start Playing");
        startPlaying.setOnAction(e -> {

            gameBoard.setDisable(false);
            if (EZ.isSelected()) {
                if (choice.getValue().equalsIgnoreCase(c[0])) {
                    p1 = new HumanPlayer(player1.getText(), "O");
                    p2 = new RandomPlayer("Computer", "X");
                    cp = p1;
                    lbldis.setText(cp.name + " Turn");

                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            final int row = i;
                            final int col = j;
                            TicTacToe.board[i][j].setOnAction(ev -> {
                                pressButton(row, col);
                            });
                        }
                    }
                }
                if (choice.getValue().equalsIgnoreCase(c[1])) {
                    p1 = new HumanPlayer(player1.getText(), "O");
                    p2 = new RandomPlayer("Computer", "X");
                    cp = p1;
                    lbldis.setText(cp.name + " Turn");
                    p2.makeMove(0, 0);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            final int row = i;
                            final int col = j;
                            TicTacToe.board[i][j].setOnAction(ev -> {
                                pressButton(row, col);
                            });
                        }
                    }
                }
            } else if (AI.isSelected()) {
                if (choice.getValue().equalsIgnoreCase("Player")) {
                    p1 = new HumanPlayer(player1.getText(), "O");
                    p2 = new AIPlayer("AI", "X");
                    cp = p1;
                    lbldis.setText(cp.name + " Turn");
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            final int row = i;
                            final int col = j;
                            TicTacToe.board[i][j].setOnAction(ev -> {
                                pressButton(row, col);
                            });
                        }
                    }
                }
                if (choice.getValue().equalsIgnoreCase("Computer")) {
                    p1 = new HumanPlayer(player1.getText(), "O");
                    p2 = new AIPlayer("AI", "X");
                    cp = p1;


                    lbldis.setText(cp.name + " Turn");
                    p2.makeMove(0, 0);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            final int row = i;
                            final int col = j;
                            TicTacToe.board[i][j].setOnAction(ev -> {
                                pressButton(row, col);
                            });
                        }
                    }
                }
            } else {
                p1 = new HumanPlayer(player1.getText(), "O");
                p2 = new HumanPlayer(player2.getText(), "X");
                cp = p1;
                lbldis.setText(cp.name + " Turn");
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        final int row = i;
                        final int col = j;
                        TicTacToe.board[i][j].setOnAction(ev -> {
                            pvp(row, col);
                        });
                    }
                }
            }

        });
        Label lbl = new Label("Enter the number of Rounds");

        newGamebtn.setPrefSize(100, 30);
        newGamebtn.setLayoutX(200);
        newGamebtn.setLayoutY(310);
        quitbtn.setPrefSize(100, 30);
        quitbtn.setLayoutX(200);
        quitbtn.setLayoutY(310);
        GridPane settings = new GridPane();
        lbldis.setFont(Font.font("ALGERIAN", FontWeight.BOLD, 50));
        lbldis.setAlignment(Pos.CENTER);
        SetTile.setText("Settings: ");
        title.setText("Tic Tac Toe");
        title.setFont(Font.font("ALGERIAN", FontWeight.BOLD, 50));
        border = new BorderPane();
        settings.add(SetTile, 0, 0);
        settings.add(newGamebtn, 0, 3);
        settings.add(quitbtn, 0, 6);
        settings.add(vbox, 0, 7);
        settings.add(hbox, 0, 8);
        settings.add(lbl,0,9);
        settings.add(txt,0,10);
        settings.add(choice, 0, 11);
        settings.add(startPlaying, 0, 12);
        settings.add(counterhum,0,13);
        settings.add(countcomp,1,13);

        settings.setPadding(new Insets(15, 25, 15, 15));
        settings.setVgap(2.5);
        border.setStyle("-fx-background-color: white ; -fx-border-width: 10px");
        border.setTop(lbldis);
        border.setLeft(settings);
        border.setCenter(gameBoard);
        ///////////////
        Scene scene = new Scene(border, 800, 450);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        newGamebtn.setOnAction(e -> {
            EnableAllButtons();
            clearButtons();
        });

        quitbtn.setOnAction(e ->
        {
            System.out.println("quiting");
            stage.close();
        });
    }

    public void clearButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                TicTacToe.board[row][col].setText("");
                TicTacToe.board[row][col].setOpacity(1);
                TicTacToe.board[i][j].setBackground(Background.fill(Color.valueOf("#bfd0cc")));

            }
        }
    }


    public void pressButton(int row, int col) {

        counterhum.setText(p1.name + "\n"+String.valueOf(compcounter));

        countcomp.setText(p2.name + "\n"+String.valueOf(humcounter));

        String s = txt.getText().trim();
        numberOfRounds = Integer.parseInt(s);

        for (int i = 0; i < 2; i++) {
            cp.makeMove(row, col);
            if (TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagWin()) {
                lbldis.setText("Round: "+cp.name + " Won");
                clearButtons();
                EnableAllButtons();
                if (Objects.equals(cp.name, player1.getText())){
                    humcounter++;
                }
                else if (Objects.equals(cp.name, p2.name)){
                    compcounter++;
                }
                counterhum.setText(p1.name + "\n"+String.valueOf(humcounter));

                countcomp.setText(p2.name + "\n"+String.valueOf(compcounter));
                currentRound++;
                if (currentRound > numberOfRounds) {
                    lbldis.setText("Game Over "+ numberOfRounds +" rounds completed!");
                    DisableAllButtons();
                }
                break;
            } else if (TicTacToe.checkDraw()) {
                lbldis.setText("Game is a draw");

                clearButtons();
                EnableAllButtons();
                System.out.println("Game is a draw ");
                if (currentRound > numberOfRounds) {
                    lbldis.setText("Game Over "+ numberOfRounds +" rounds completed!");
                    DisableAllButtons();
                }
                break;
            } else {
                if (cp == p1) {
                    cp = p2;
                    lbldis.setText("Round: " + currentRound+ cp.name + " Turn");

                } else {
                    Platform.runLater(() -> {

                        cp.makeMove(row, col);
                    });

                    cp = p1;
                    lbldis.setText("Round: " + currentRound+ cp.name + " Turn");

                }
            }
        }
    }

    public void pvp(int row, int col) {
        counterhum.setText(p1.name + "\n" + String.valueOf(compcounter));
        countcomp.setText(p2.name + "\n" + String.valueOf(humcounter));

        String s = txt.getText().trim();
        numberOfRounds = Integer.parseInt(s);

        TicTacToe.board[row][col].setText(cp.mark);
        cp.makeMove(row, col);

        if (TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagWin()) {
            lbldis.setText("Round: " + currentRound + cp.name + " Won");
            currentRound++;
            clearButtons();
            EnableAllButtons();

            if (Objects.equals(cp.name, player1.getText())) {
                humcounter++;
            } else if (Objects.equals(cp.name, player2.getText())) {
                compcounter++;
            }

            counterhum.setText(p1.name + "\n" + String.valueOf(humcounter));
            countcomp.setText(p2.name + "\n" + String.valueOf(compcounter));

            if (currentRound <= numberOfRounds) {
                lbldis.setText("Round: " + currentRound + " "+cp.name + " Turn");
            } else {
                lbldis.setText("Game Over " + numberOfRounds + " rounds completed!");
                DisableAllButtons();
            }
        } else if (TicTacToe.checkDraw()) {
            lbldis.setText("Game is a draw");
            clearButtons();
            EnableAllButtons();
            System.out.println("Game is a draw ");

            currentRound++;
            if (currentRound <= numberOfRounds) {
                lbldis.setText("Round: " + currentRound + " "+cp.name + " Turn");
            } else {
                lbldis.setText("Game Over " + numberOfRounds + " rounds completed!");
                DisableAllButtons();
            }
        } else {
            if (cp == p1) {
                cp = p2;
            } else {
                cp = p1;
            }lbldis.setText("Round: " + currentRound + " "+cp.name + " Turn");
        }
    }



    public void DisableAllButtons() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                TicTacToe.board[i][j].setDisable(true);
            }
        }
    }

    public void EnableAllButtons() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                TicTacToe.board[i][j].setDisable(false);
            }
        }
    }


    public static void main(String[] args) {
        launch();
    }
}