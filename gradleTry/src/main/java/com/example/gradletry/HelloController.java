package com.example.gradletry;


import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public Canvas canvas;
    public Label label;
    public Circle ball;
    public VBox mainLayout;
    GraphicsContext gc;
    Entity entity;
    AnimationTimer mainLoop;
    ArrayList<String> inputList;
    ArrayList<Entity> entitiesList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputList = new ArrayList<>();
        entitiesList = new ArrayList<>();
        gc = canvas.getGraphicsContext2D();
        /*
        gc.setFill(Paint.valueOf("ORANGE"));
        gc.fillRect(100, 200, 200, 400);
         */
        entity = new Entity(10, 10, 20, 30, Color.color(Math.random(), Math.random(), Math.random(), Math.random()), 5.1f);
        spawnEntities(10);

        Platform.runLater(() -> {
            mainLayout.requestFocus();
        });

        mainLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //clear canvas
                clearCanvas();
                //update
                update();
                checkCollision();
                //render
                render();
            }
        };
        mainLoop.start();
    }

    private void clearCanvas() {
        gc.setFill(Paint.valueOf("WHITE"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void update() {
        entity.update(inputList, canvas);
    }

    public void render() {
        entity.render(gc);
        entitiesList.forEach(obj -> {
            obj.render(gc);
        });
    }

    private void spawnEntities(int number) {
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            entitiesList.add(new Entity(generate(0, canvas.getWidth()), generate(0, canvas.getHeight()), generate(10, 30), generate(10, 30), Color.color(Math.random(), Math.random(), Math.random(), Math.random()), 1.1f));
        }
    }

    private void checkCollision() {
        for (Entity obj : entitiesList) {
            if (entity.getX() < obj.getX() + obj.getWidth() &&
                    entity.getX() + obj.getWidth() > obj.getX() &&
                    entity.getY() < obj.getY() + obj.getHeight() &&
                    entity.getHeight() + entity.getY() > obj.getY()) {
                obj.setColor(Color.color(Math.random(), Math.random(), Math.random(), Math.random()));
                entitiesList.remove(obj);
                break;
            }
        }
       /* entitiesList.forEach(obj -> {
            if (entity.getX() < obj.getX() + obj.getWidth() &&
                    entity.getX() + obj.getWidth() > obj.getX() &&
                    entity.getY() < obj.getY() + obj.getHeight() &&
                    entity.getHeight() + entity.getY() > obj.getY()) {
                obj.setColor(Color.color(Math.random(), Math.random(), Math.random(), Math.random()));
                entitiesList.remove(obj);
            }
        });*/
    }

    private int generate(int min, double max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public void onMouseMove(MouseEvent mouseEvent) {
        gc.setFill(Paint.valueOf("BLACK"));
        gc.fillOval(mouseEvent.getX(), mouseEvent.getY(), 5, 5);
    }

    public void getCoords(MouseEvent mouseEvent) {
        label.setText(String.valueOf(mouseEvent.getX()));
    }

    public void onClickJump(MouseEvent mouseEvent) {
        if (ball.getTranslateX() <= 200 || ball.getTranslateX() > 0) {
            ball.setTranslateX(ball.getTranslateX() - 20);

        } else {
            ball.setTranslateX(ball.getTranslateX() + 20);
            ball.setTranslateY(ball.getTranslateY() + 20);
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (!inputList.contains(keyEvent.getText())) {
            inputList.add(keyEvent.getText());
        }




        /*
        //System.out.print(keyEvent.getText());
        if (keyEvent.getText().equalsIgnoreCase("w")) {
            entity.setY(entity.getY() - 1);
            ball.setTranslateY(ball.getTranslateY() - 20);
        }

        if (keyEvent.getText().equalsIgnoreCase("s")) {
            entity.setY(entity.getY() + 1);
            ball.setTranslateY(ball.getTranslateY() + 20);

        }
        if (keyEvent.getText().equalsIgnoreCase("a")) {
            entity.setX(entity.getX() - 1);
            ball.setTranslateX(ball.getTranslateX() - 20);
        }
        if (keyEvent.getText().equalsIgnoreCase("d")) {
            ball.setTranslateX(ball.getTranslateX() + 20);
            entity.setX(entity.getX() + 1);
        }

        gc.setFill(Paint.valueOf("WHITE"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(entity.getColor());
        gc.fillRect(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());

         */
    }

    public void keyReleased(KeyEvent keyEvent) {
        inputList.remove(keyEvent.getText());
    }
}