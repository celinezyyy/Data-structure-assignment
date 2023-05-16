/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.struc.assignment;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Q1TreeChart extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final Color BOX_COLOR = Color.LIGHTBLUE;
    private static final Color LINE_COLOR = Color.GRAY;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Wu Kingdom Hierarchy Chart");

        Pane root = new Pane();
        root.setStyle("-fx-background-color: white;");

        Q1WuKingdomHierarchy hierarchy = new Q1WuKingdomHierarchy();

        // Layer 1
        Text layer1Text = createText(hierarchy.root.emperor.name, 500, 50);
        Rectangle layer1Box = createBox(layer1Text);
        root.getChildren().addAll(layer1Box, layer1Text);

        // Layer 2
        int xCoordinateLayer2 = 300;
        for (Q1TreeNode child : hierarchy.root.children) {
            String chiefName = child.chief.name;
            Text layer2Text = createText(chiefName, xCoordinateLayer2, 150);
            Rectangle layer2Box = createBox(layer2Text);
            root.getChildren().addAll(layer2Box, layer2Text);
            xCoordinateLayer2 += 450;
        }

        // Layer 3
        int xCoordinateLayer3 = 100;
        for (Q1TreeNode child : hierarchy.root.children) {
            for (Q1TreeNode grandchild : child.children) {
                String generalName = grandchild.general.name;
                Text layer3Text = createText(generalName, xCoordinateLayer3, 250);
                Rectangle layer3Box = createBox(layer3Text);
                root.getChildren().addAll(layer3Box, layer3Text);
                xCoordinateLayer3 += 100;
            }
        }

        // Connect the emperor with the chiefs
        for (Q1TreeNode child : hierarchy.root.children) {
            String chiefName = child.chief.name;
            Text startNode = findTextByContent(root, hierarchy.root.emperor.name);
            Text endNode = findTextByContent(root, chiefName);
            Line line = createLine(startNode, endNode);
            root.getChildren().add(line);
            line.toBack();
        }
        // Connect the chiefs with the generals
        for (Q1TreeNode child : hierarchy.root.children) {
            String chiefName = child.chief.name;
            Text startNode = findTextByContent(root, chiefName);

            for (Q1TreeNode grandchild : child.children) {
                String generalName = grandchild.general.name;
                Text endNode = findTextByContent(root, generalName);

                Line line = createLine(startNode, endNode);
                root.getChildren().add(line);
                line.toBack();
            }
        }

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Text createText(String text, double x, double y) {
        Text t = new Text(text);
        t.setX(x);
        t.setY(y);
        t.setFont(Font.font("Arial", 16));
        return t;
    }

    private Rectangle createBox(Text text) {
        Bounds bounds = text.getBoundsInLocal();
        Rectangle box = new Rectangle(bounds.getMinX(), bounds.getMinY(),
                bounds.getWidth(), bounds.getHeight());
        box.setFill(BOX_COLOR);
        return box;
    }

    private Line createLine(Text startNode, Text endNode) {
        double startX = startNode.getX() + startNode.getBoundsInLocal().getWidth() / 2;
        double startY = startNode.getY() + startNode.getBoundsInLocal().getHeight();
        double endX = endNode.getX() + endNode.getBoundsInLocal().getWidth() / 2;
        double endY = endNode.getY();

        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(LINE_COLOR);
        line.setStrokeWidth(2);

        return line;
    }

    private Text findTextByContent(Pane pane, String content) {
        for (javafx.scene.Node node : pane.getChildren()) {
            if (node instanceof Text) {
                Text textNode = (Text) node;
                if (textNode.getText().equals(content)) {
                    return textNode;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
