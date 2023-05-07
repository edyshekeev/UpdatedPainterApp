package com.example.updatedpainterapp;

import javafx.event.ActionEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class UpdatedPainterAppController {

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double alpha = 1.0;

    private enum PenSize {
        SMALL(2),
        MEDIUM(4),
        LARGE(6);

        private final int radius;
        PenSize(int radius) {this.radius = radius;}

        public int getRadius() {return radius;}
    };

    @FXML
    private Slider alphaSlider;

    @FXML
    private TextField alphaTextField;

    @FXML
    private Slider blueSlider;

    @FXML
    private TextField blueTextField;

    @FXML
    private Button clearButton;

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private Slider greenSlider;

    @FXML
    private TextField greenTextField;

    @FXML
    private RadioButton largeRadioButton;

    @FXML
    private RadioButton mediumRadioButton;

    @FXML
    private Slider redSlider;

    @FXML
    private TextField redTextField;

    @FXML
    private RadioButton smallRadioButton;

    @FXML
    private Button undoButton;

    @FXML
    private ToggleGroup sizeToggleGroup;

    private PenSize radius = PenSize.MEDIUM;
    private Paint brushColor = Color.rgb(red, green, blue, alpha);

    public void initialize() {
        smallRadioButton.setUserData(PenSize.SMALL);
        mediumRadioButton.setUserData(PenSize.MEDIUM);
        largeRadioButton.setUserData(PenSize.LARGE);
        redTextField.textProperty().bind(redSlider.valueProperty().asString("%.0f"));
        greenTextField.textProperty().bind(greenSlider.valueProperty().asString("%.0f"));
        blueTextField.textProperty().bind((blueSlider.valueProperty().asString("%.0f")));
        alphaTextField.textProperty().bind(alphaSlider.valueProperty().asString("%.2f"));
        redSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                red = newValue.intValue();
                brushColor = Color.rgb(red, green, blue, alpha);
            }
        });
        greenSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                green = newValue.intValue();
                brushColor = Color.rgb(red, green, blue, alpha);
            }
        });
        blueSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                blue = newValue.intValue();
                brushColor = Color.rgb(red, green, blue, alpha);
            }
        });
        alphaSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                alpha = newValue.doubleValue();
                brushColor = Color.rgb(red, green, blue, alpha);
            }
        });
    }

    @FXML
    private void drawingAreaMouseDragged(MouseEvent e) {
        Circle newCircle = new Circle(e.getX(), e.getY(), radius.getRadius(), brushColor);
        drawingAreaPane.getChildren().add(newCircle);
    }

    @FXML
    private void sizeRadioButtonSelected(ActionEvent e) {
        radius = (PenSize) sizeToggleGroup.getSelectedToggle().getUserData();
    }

    @FXML
    private void undoButtonPressed(ActionEvent e) {
        int count = drawingAreaPane.getChildren().size();
        if (count > 0) {
            drawingAreaPane.getChildren().remove(count - 1);
        }
    }

    @FXML
    private void clearButtonPressed(ActionEvent e) {
        drawingAreaPane.getChildren().clear();
    }
}