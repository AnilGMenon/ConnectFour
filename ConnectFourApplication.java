
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ANIL MENON
 * @version 1.0
 */
public class ConnectFourApplication extends Application implements Observer {

    /**
     * @param args the command line arguments
     */
    public final static int NUM_COLUMNS = 8;
    public final static int NUM_ROWS = 8;
    public final static int NUM_TO_WIN = 4;
    public final static int BUTTON_SIZE = 40;
    private ConnectFourGame gameEngine;
    private ConnectButton[][] buttons;
    private int rw;
    private int cm;
    private ConnectFourEnum x;
    private TextField t;
    private GridPane gridPane;

    /**
     * Initializes the instance variables and randomizes the initial player
     * playing.
     */
    public ConnectFourApplication() {
        Random r = new Random();
        ConnectFourEnum[] array = new ConnectFourEnum[2];
        array[0] = ConnectFourEnum.BLACK;
        array[1] = ConnectFourEnum.RED;
        gameEngine = new ConnectFourGame(array[r.nextInt(2)]);
        buttons = new ConnectButton[NUM_ROWS][NUM_COLUMNS];
        rw = -1;
        cm = -1;
        t = new TextField();
    }

    @Override
    /**
     * Overrides the start method of the application and essentially creates the
     * view of GUI.
     */
    public void start(Stage primaryStage) {
        //HBox root = new HBox();
        BorderPane root = new BorderPane();
        gridPane = new GridPane();
        Scene scene = new Scene(root, 510, 380);

        t.setDisable(true);
        t.setText("" + gameEngine.getTurn() + " begins.");
        Button tf[] = new Button[64];

        root.setCenter(gridPane);
        root.setTop(t);
        ButtonHandler r = new ButtonHandler();
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                buttons[i][j] = new ConnectButton(ConnectFourEnum.EMPTY.toString(), i, j);
                buttons[i][j].setMinHeight(BUTTON_SIZE);
                buttons[i][j].setMaxWidth(Double.MAX_VALUE);
                buttons[i][j].setOnAction(r);
                gridPane.add(buttons[i][j], j, NUM_COLUMNS - i);

            }
        }

        Button takeTurn = new Button("Take My Turn");
        takeTurn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameEngine.takeTurn(rw, cm);
                System.out.println(event + "Drop the checker");
                System.out.println(gameEngine.toString());
            }
        });
        gameEngine.addObserver(this);
        root.setBottom(takeTurn);
        primaryStage.setTitle("ConnectFour");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    /**
     * Updates the GUI.
     */
    public void update(Observable o, Object arg) {

        if (gameEngine.getGameState() != ConnectFourEnum.IN_PROGRESS) {
            ConnectMove b = (ConnectMove) arg;
            rw = b.getRow();
            cm = b.getColumn();
            x = b.getColour();

            String s = x.toString();
            System.out.println(s);
            buttons[rw][cm].setText(s);
            buttons[b.getRow()][b.getColumn()].setDisable(true);
            t.setText("" + s + "'s turn");

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Alert");
            alert.setHeaderText("Game Over");
            if (gameEngine.getGameState() == ConnectFourEnum.DRAW) {
                alert.setContentText(gameEngine.getGameState().toString());
            } else {
                alert.setContentText(gameEngine.getGameState().toString() + " WINS !");
            }

            alert.showAndWait();
            gameEngine.reset(ConnectFourEnum.RED);
            //gridPane.clearConstraints(gridPane);
            reset(gridPane);
            gameEngine.set(ConnectFourEnum.IN_PROGRESS);

        } else {
            ConnectMove b = (ConnectMove) arg;

            rw = b.getRow();
            cm = b.getColumn();
            x = b.getColour();

            String s = x.toString();
            if (b.getColour() == ConnectFourEnum.BLACK) {
                buttons[rw][cm].setText(ConnectFourEnum.RED.toString());
            } else {
                buttons[rw][cm].setText(ConnectFourEnum.BLACK.toString());
            }
            buttons[b.getRow()][b.getColumn()].setDisable(true);

            t.setText("" + s + "'s turn");
        }
    }

    /**
     * Resets the buttons and gridPane.
     */
    public void reset(GridPane gridpane) {

        ButtonHandler r = new ButtonHandler();
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                buttons[i][j] = new ConnectButton(ConnectFourEnum.EMPTY.toString(), i, j);
                buttons[i][j].setMinHeight(BUTTON_SIZE);
                buttons[i][j].setMaxWidth(Double.MAX_VALUE);
                buttons[i][j].setOnAction(r);
                gridpane.add(buttons[i][j], j, NUM_COLUMNS - i);

            }
        }
    }

    /**
     * The button handler of the grid buttons.
     */
    public class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Object o = event.getSource();
            ConnectButton b = (ConnectButton) o;
            rw = b.getRow();
            cm = b.getColumn();
            System.out.println(rw + " " + cm);
            System.out.println(event.getSource());
        }

    }
}
