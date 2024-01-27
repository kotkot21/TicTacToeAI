package com.example.tttai;

import javafx.application.Application;
import javafx.application.Platform;
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

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    TicTacToe ticTacToe = new TicTacToe();

    Player p1;
    Player p2;
    Player cp;
    Pane gameBoard = new Pane();
    Label lbldis = new Label();
    Label title = new Label();
    Label SetTile = new Label();
    Button newGamebtn = new Button("New Game");
    Button quitbtn = new Button("Quit");
    BorderPane border;
    Label pName1 = new Label("First Player Name");
    TextField player1 = new TextField();
    Label pName2 = new Label("Second Player Name");
    TextField player2 = new TextField();

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
        settings.add(choice, 0, 9);
        settings.add(startPlaying, 0, 10);


        settings.setPadding(new Insets(15, 25, 15, 15));
        settings.setVgap(2.5);
        border.setStyle("-fx-background-color: #add8e6; -fx-border-width: 10px");
        border.setTop(lbldis);
        border.setLeft(settings);
        border.setCenter(gameBoard);
        ///////////////
        Scene scene = new Scene(border, 800, 450);
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
        newGamebtn.setOnAction(e -> {
            stage.close();
            Platform.runLater(() -> {
                try {
                    new HelloApplication().start(new Stage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
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

        for (int i = 0; i < 2; i++) {
            cp.makeMove(row, col);
            if (!TicTacToe.board[0][i].getText().isEmpty() && Objects.equals(TicTacToe.board[0][i].getText(), TicTacToe.board[1][i].getText())
                    && Objects.equals(TicTacToe.board[1][i].getText(), TicTacToe.board[2][i].getText())) {
                lbldis.setText(cp.name + " Won");
                TicTacToe.board[0][i].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[1][i].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[2][i].setBackground(Background.fill(Color.SPRINGGREEN));
                DisableAllButtons();
                break;
            } else if (!TicTacToe.board[i][0].getText().isEmpty() && Objects.equals(TicTacToe.board[i][0].getText(), TicTacToe.board[i][1].getText())
                    && Objects.equals(TicTacToe.board[i][1].getText(), TicTacToe.board[i][2].getText())) {
                lbldis.setText(cp.name + " Won");
                TicTacToe.board[i][0].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[i][1].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[i][2].setBackground(Background.fill(Color.SPRINGGREEN));
                DisableAllButtons();
                break;
            } else if ((!TicTacToe.board[0][0].getText().isEmpty() &&
                    Objects.equals(TicTacToe.board[0][0].getText(), TicTacToe.board[1][1].getText())
                    && Objects.equals(TicTacToe.board[1][1].getText(), TicTacToe.board[2][2].getText()))) {
                DisableAllButtons();
                lbldis.setText(cp.name + " Won");
                TicTacToe.board[0][0].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[1][1].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[2][2].setBackground(Background.fill(Color.SPRINGGREEN));
                break;
            } else if (!TicTacToe.board[0][2].getText().isEmpty()
                    && Objects.equals(TicTacToe.board[0][2].getText(), TicTacToe.board[1][1].getText())
                    && Objects.equals(TicTacToe.board[1][1].getText(), TicTacToe.board[2][0].getText())) {
                lbldis.setText(cp.name + " Won");
                DisableAllButtons();
                TicTacToe.board[0][2].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[1][1].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[2][0].setBackground(Background.fill(Color.SPRINGGREEN));
                break;
            }
            if (TicTacToe.checkDraw()) {
                lbldis.setText("Game is a draw");

                DisableAllButtons();
                break;
            } else {
                if (cp == p1) {
                    cp = p2;
                } else {
                    Platform.runLater(() -> {
                        cp.makeMove(row, col);
                        lbldis.setText(cp.name + " Turn");
                    });
                    cp = p1;
                    lbldis.setText(cp.name + " Turn");

                }
            }
        }
    }

    public void pvp(int row, int col) {

        TicTacToe.board[row][col].setText(cp.mark);
        for (int i = 0; i < 2; i++) {
            cp.makeMove(row, col);
            if (!TicTacToe.board[0][i].getText().isEmpty() && Objects.equals(TicTacToe.board[0][i].getText(), TicTacToe.board[1][i].getText())
                    && Objects.equals(TicTacToe.board[1][i].getText(), TicTacToe.board[2][i].getText())) {
                lbldis.setText(cp.name + " Won");
                TicTacToe.board[0][i].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[1][i].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[2][i].setBackground(Background.fill(Color.SPRINGGREEN));
                DisableAllButtons();
                break;
            } else if (!TicTacToe.board[i][0].getText().isEmpty() && Objects.equals(TicTacToe.board[i][0].getText(), TicTacToe.board[i][1].getText())
                    && Objects.equals(TicTacToe.board[i][1].getText(), TicTacToe.board[i][2].getText())) {
                lbldis.setText(cp.name + " Won");
                TicTacToe.board[i][0].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[i][1].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[i][2].setBackground(Background.fill(Color.SPRINGGREEN));
                DisableAllButtons();
                break;
            } else if ((!TicTacToe.board[0][0].getText().isEmpty() &&
                    Objects.equals(TicTacToe.board[0][0].getText(), TicTacToe.board[1][1].getText())
                    && Objects.equals(TicTacToe.board[1][1].getText(), TicTacToe.board[2][2].getText()))) {
                DisableAllButtons();
                lbldis.setText(cp.name + " Won");
                TicTacToe.board[0][0].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[1][1].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[2][2].setBackground(Background.fill(Color.SPRINGGREEN));
                break;
            } else if (!TicTacToe.board[0][2].getText().isEmpty()
                    && Objects.equals(TicTacToe.board[0][2].getText(), TicTacToe.board[1][1].getText())
                    && Objects.equals(TicTacToe.board[1][1].getText(), TicTacToe.board[2][0].getText())) {
                lbldis.setText(cp.name + " Won");
                DisableAllButtons();
                TicTacToe.board[0][2].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[1][1].setBackground(Background.fill(Color.SPRINGGREEN));
                TicTacToe.board[2][0].setBackground(Background.fill(Color.SPRINGGREEN));
                break;
            }
        } if (TicTacToe.checkDraw()) {
            lbldis.setText("Game is a draw");
            DisableAllButtons();
        } else {
            if (cp == p1) {
                cp = p2;
                lbldis.setText(cp.name + " Turn");
            } else {
                cp = p1;
            }
            lbldis.setText(cp.name + " Turn");
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