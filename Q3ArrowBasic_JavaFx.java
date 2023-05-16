package javafxtest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Q3ArrowBasic_JavaFx extends Application {

    private TextField frontTextField;
    private TextField leftTextField;
    private TextField rightTextField;
    private TextField backTextField;
    private TextField numWaveTextField;
    private TextField arrowTextField;
    private Label bestDirectionLabel;
    private Label arrowReceivedLabel;
    private Label totalLabel;
    private Q3MyQueue<Integer> bestArrowCaptured;
    private Q3MyQueue<String> bestDirection;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Q3 Arrow Basic");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label frontLabel = new Label("Front:");
        grid.add(frontLabel, 0, 0);

        frontTextField = new TextField();
        grid.add(frontTextField, 1, 0);

        Label leftLabel = new Label("Left:");
        grid.add(leftLabel, 0, 1);

        leftTextField = new TextField();
        grid.add(leftTextField, 1, 1);

        Label rightLabel = new Label("Right:");
        grid.add(rightLabel, 0, 2);

        rightTextField = new TextField();
        grid.add(rightTextField, 1, 2);

        Label backLabel = new Label("Back:");
        grid.add(backLabel, 0, 3);

        backTextField = new TextField();
        grid.add(backTextField, 1, 3);

        Label numWaveLabel = new Label("Number of Waves:");
        grid.add(numWaveLabel, 0, 4);

        numWaveTextField = new TextField();
        grid.add(numWaveTextField, 1, 4);

        Label arrowLabel = new Label("Arrow (in decreasing order):");
        grid.add(arrowLabel, 0, 5);

        arrowTextField = new TextField();
        grid.add(arrowTextField, 1, 5);

        Button calculateButton = new Button("Calculate");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(calculateButton);
        grid.add(hbBtn, 1, 6);

        VBox resultBox = new VBox(10);
        bestDirectionLabel = new Label();
        arrowReceivedLabel = new Label();
        totalLabel = new Label();
        resultBox.getChildren().addAll(bestDirectionLabel, arrowReceivedLabel, totalLabel);
        grid.add(resultBox, 1, 7);

        calculateButton.setOnAction(e -> calculateButtonClicked());

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateButtonClicked() {
        int front = Integer.parseInt(frontTextField.getText());
        int left = Integer.parseInt(leftTextField.getText());
        int right = Integer.parseInt(rightTextField.getText());
        int back = Integer.parseInt(backTextField.getText());
        int numWave = Integer.parseInt(numWaveTextField.getText());
        String arrowInput = arrowTextField.getText();

        int[] arrow = new int[numWave];
    String[] arrowTokens = arrowInput.split(" ");
    for (int i = 0; i < numWave; i++) {
        arrow[i] = Integer.parseInt(arrowTokens[i]);
    }

    int[] numStrawMen = {front, left, right, back};
    int[] count = {0, 0, 0, 0};

    bestArrowCaptured = new Q3MyQueue<>();
    bestDirection = new Q3MyQueue<>();

    int[] choose;
    for (int i = 0; i < numWave; i++) {
        choose = efficiency(numStrawMen, arrow[i], count);

        if (choose[0] >= choose[1] && choose[0] >= choose[2] && choose[0] >= choose[3]) {
            bestDirection.enqueue("front");
            bestArrowCaptured.enqueue(choose[0]);
        } else if (choose[1] >= choose[2] && choose[1] >= choose[3]) {
            bestDirection.enqueue("left");
            bestArrowCaptured.enqueue(choose[1]);
        } else if (choose[2] >= choose[3]) {
            bestDirection.enqueue("right");
            bestArrowCaptured.enqueue(choose[2]);
        } else {
            bestDirection.enqueue("back");
            bestArrowCaptured.enqueue(choose[3]);
        }
        count = useStrawMenCount(bestDirection.getElement(i), count);
    }

    StringBuilder bestDirectionText = new StringBuilder("Boat Direction: [");
    while (!bestDirection.isEmpty()) {
        bestDirectionText.append(bestDirection.dequeue());
        if (!bestDirection.isEmpty()) {
            bestDirectionText.append(", ");
        } else {
            bestDirectionText.append("]");
        }
    }
    bestDirectionLabel.setText(bestDirectionText.toString());

    int totalCaptured = 0;
    StringBuilder arrowReceivedText = new StringBuilder("Arrow received: [");
    while (!bestArrowCaptured.isEmpty()) {
        int arrowCount = bestArrowCaptured.dequeue();
        totalCaptured += arrowCount;
        arrowReceivedText.append(arrowCount);
        if (!bestArrowCaptured.isEmpty()) {
            arrowReceivedText.append(", ");
        } else {
            arrowReceivedText.append("]");
        }
    }
    arrowReceivedLabel.setText(arrowReceivedText.toString());

    totalLabel.setText("Total = " + totalCaptured);
}

public static int[] useStrawMenCount(String direction, int[] count) {
    switch (direction) {
        case "front":
            count[0]++;
            break;
        case "left":
            count[1]++;
            break;
        case "right":
            count[2]++;
            break;
        case "back":
            count[3]++;
            break;
    }
    return count;
}

public static int[] efficiency(int[] numStrawMen, int arrow, int[] count) {
    int[] captured = new int[4];

    for (int i = 0; i < 4; i++) {
        if (count[i] == 0)
            captured[i] = arrow * numStrawMen[i] / 100;
        else if (count[i] == 1)
            captured[i] = (int) (arrow * (numStrawMen[i] * 0.8) / 100);
        else if (count[i] == 2)
            captured[i] = (int) (arrow * (numStrawMen[i] * 0.4) / 100);
        else
            captured[i] = 0;
    }
    return captured;
}
}