package com.example.canvasalg;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    Canvas canvas;
    GraphicsContext gc;

    Entity entity;
    ArrayList<Entity> nodes = new ArrayList<Entity>();

    public void fillCanvas() {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Paint.valueOf("RED"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        renderNode();
    }

    public void renderNode() {
        for (int i = 0; i < nodes.size(); i++) {
        }
        gc.lineTo(25, 400);
        gc.strokeLine(40, 10, 10, 40);
        gc.setLineWidth(15);
        gc.setStroke(Paint.valueOf("YELLOW")); //Object color
        gc.stroke();
    }

    public void line() {
        Path path = new Path();

        MoveTo moveTo = new MoveTo(100, 20);

        LineTo lineTo = new LineTo(100, 50);

    }

    public void initNodes() {
        nodes.add(1, new Entity(20, 20, 20, 20, Paint.valueOf("PINK")));
        nodes.add(2, new Entity(20, 20, 20, 20, Paint.valueOf("PINK")));
        nodes.add(3, new Entity(20, 20, 20, 20, Paint.valueOf("PINK")));
        nodes.add(4, new Entity(20, 20, 20, 20, Paint.valueOf("PINK")));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Paint.valueOf("RED"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.strokeLine(100, 50, 20, 100);

        gc.strokeLine(50, 50 ,50 ,50);
    }
}