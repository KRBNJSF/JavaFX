# JavaFX

FXML
---
Tag language - (Identical with HTML)


JavaFX Scene builder sample
---
![image](https://user-images.githubusercontent.com/90755554/149889330-137f8dc3-418d-485c-9b1b-70549b556673.png)

Java Fx Text sample
---
```
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.geometry.Insets?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.VBox?>

<?import javafx.scene.control.Button?>
<VBox alignment="CENTER" spacing="20.0" xmlns:fx="http://javafx.com/fxml"
      fx:controller="com.example.gradletry.HelloController">
    <padding>
        <Insets bottom="20.0" left="20.0" right="20.0" top="20.0"/>
    </padding>

    <Label fx:id="welcomeText"/>
    <Button text="Hello!" onAction="#onHelloButtonClick"/>
</VBox>
```
Propojení logiky s prvky sample
---

```
fx:controller="com.example.gradletry.HelloController">
```
![image](https://user-images.githubusercontent.com/90755554/149891654-4170630e-aeff-40ed-afef-83565bb08764.png)

<b>Akce při spuštění</b>
```
implements Initializable
public class HelloController implements Initializable {

 @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

```

Adding attributes
---

![image](https://user-images.githubusercontent.com/90755554/149893722-df5656ee-9f3c-4001-ace3-f5fefa2c2375.png)
![image](https://user-images.githubusercontent.com/90755554/149893795-925d6217-8f90-4f9b-9800-4f1046a1ce3f.png)

Canvas example
---
view.fxml
```
<Canvas fx:id="canvas" height="400.0" width="600.0" onMouseMoved="#onMouseMove"/>
```
Controller.java
```
public void onMouseMove(MouseEvent mouseEvent) {
        gc.setFill(Paint.valueOf("BLACK"));
        gc.fillOval(mouseEvent.getX(), mouseEvent.getY(), 5, 5);
    }
```
Funkčnost onClick metody bez tlačítek
---
```
Platform.runLater(() -> {
            mainLayout.requestFocus();
        });
```
Potřebné k práci s grafickými prvky
```
GraphicsContext gc;
```
```
gc = canvas.getGraphicsContext2D();
```
<b>Character printout to console</b>
```
public void keyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getText());
    }
```
Object animation
---
<b><a href="https://github.com/KRBNJSF/JavaFX/tree/main/src/main/java/com/example/gradletry">Source code</a></b><br>

Vytvoř class Entity ->
```
package com.example.gradletry;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Entity {

    private double x;
    private double y;
    private double width;
    private double height;
    private Paint color;
    private float velocity;

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public Entity(double x, double y, double width, double height, Paint color, float velocity) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.velocity = velocity;
    }

    public void update(ArrayList<String> input) {
        if (input.contains("w")) {
            setY(getY() - velocity);
        }

        if (input.contains("s")) {
            setY(getY() + velocity);
        }
        if (input.contains("a")) {
            setX(getX() - velocity);
        }
        if (input.contains("d")) {
            setX(getX() + velocity);
        }
    }

    public void render(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

}



```
<b>Pohyb objektu po obrazovce</b>
```
public void keyPressed(KeyEvent keyEvent) {
        //System.out.print(keyEvent.getText());
        if (!input.contains(keyEvent.getText())) {
            input.add(keyEvent.getText());
        }
```

```
private void clearCanvas() {
        gc.setFill(Paint.valueOf("WHITE"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void update() {
        entity.update(input);
    }

    public void render() {
        entity.render(gc);
    }
    
```
<b>Main loop</b>
```
 @Override
    public void initialize(URL location, ResourceBundle resources) {
        input = new ArrayList<>();
        gc = canvas.getGraphicsContext2D();
        /*
        gc.setFill(Paint.valueOf("ORANGE"));
        gc.fillRect(100, 200, 200, 400);
         */
        entity = new Entity(10, 10, 20, 30, Paint.valueOf("PINK"), 5.1f);

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
                //render
                render();
            }
        };
        mainLoop.start();
    }
```

3.2. 2022
---
Collision check
---
```
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
```

```
private void spawnEntities(int number) {
        for (int i = 0; i < number; i++) {
            entitiesList.add(new Entity(generate(0, canvas.getWidth()), generate(0, canvas.getHeight()), generate(10, 30), generate(10, 30), Color.color(Math.random(), Math.random(), Math.random(), Math.random()), 1.1f));
        }
    }
```

```
package com.example.gradletry;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Entity {

    private double x;
    private double y;
    private double width;
    private double height;
    private Paint color;
    private float velocity;

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public Entity(double x, double y, double width, double height, Paint color, float velocity) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.velocity = velocity;
    }

    public void update(ArrayList<String> input, Canvas canvas) {
        if (input.contains("w")) {
            setY(getY() - velocity);
            if (y + height < 0) {
                y = canvas.getHeight();
            }
        }

        if (input.contains("s")) {
            setY(getY() + velocity);
            if (y > canvas.getHeight()) {
                y = -height;
            }
        }
        if (input.contains("a")) {
            setX(getX() - velocity);
            if (x + height < 0) {
                x = canvas.getWidth();
            }
        }
        if (input.contains("d")) {
            setX(getX() + velocity);
            if (x > canvas.getWidth()) {
                x = -width;
            }
        }
    }

    public void render(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

}

```

10.2. 2022
---
https://www.tutorialspoint.com/javafx/javafx_2d_shapes.htm

https://docs.oracle.com/javase/8/javafx/api/javafx/scene/canvas/GraphicsContext.html

https://www.algoritmy.net/article/1396/Boruvkuv-algoritmus

17.2. 2022
---
Executable .jar file JavaFX

https://stackoverflow.com/questions/57019143/build-executable-jar-with-javafx11-from-maven

```
<plugin>
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.2.1</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
            <configuration>
                <transformers>
                    <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                        <mainClass>my.package.name.SuperMain</mainClass>
                    </transformer>
                </transformers>
            </configuration>
        </execution>
    </executions>
</plugin>
```

![image](https://user-images.githubusercontent.com/90755554/154493401-6115ef4f-e83e-46d7-a36e-176a3f0899b2.png)


![image](https://user-images.githubusercontent.com/90755554/154493160-9809df50-1960-49a2-8d50-fe29fdd95107.png)

ctrl + ctrl

![image](https://user-images.githubusercontent.com/90755554/154493771-875a62c0-566e-433e-8e75-56b8fa8279b0.png)

![image](https://user-images.githubusercontent.com/90755554/154493548-495b0ca6-61b8-436a-bb7b-cabb054aa9ef.png)




