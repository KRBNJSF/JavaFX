package com.example.canvasalg;

import javafx.scene.paint.Paint;

public class Entity {
    public int height;
    public int width;
    public int x;
    public int y;
    public Paint color;

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    public Entity(int height, int width, int x, int y, Paint color) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
