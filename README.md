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
<b>Akce při spuštění</b>
```
implements Initializable
public class HelloApplication extends Application implements Initializable

 @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

```
