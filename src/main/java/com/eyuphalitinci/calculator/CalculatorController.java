package com.eyuphalitinci.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {

    @FXML
    private Label displayLabel;

    private double firstOperand = 0;
    private String operator = "";
    private boolean start = true;

    @FXML
    private void processNumber(ActionEvent event) {

        String value = ((Button) event.getSource()).getText();

        if (start) {

            displayLabel.setText(value);
            start = false;
        } else {

            displayLabel.setText(displayLabel.getText() + value);
        }
    }


    @FXML
    private void processOperator(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();


        if (value.equals("CE")) {
            displayLabel.setText("0");
            start = true;
            return;
        }

        if (!value.equals("=")) {
            if (!operator.isEmpty())
                return;
            operator = value;
            firstOperand = Double.parseDouble(displayLabel.getText());
            displayLabel.setText(firstOperand + " " + operator);
            start = true;
        }
    }


    @FXML
    private void processEquals(ActionEvent event) {
        if (operator.isEmpty() || start)
            return;

        double secondOperand = Double.parseDouble(displayLabel.getText());
        double result = calculate(firstOperand, secondOperand, operator);

        displayLabel.setText(String.valueOf(result));
        operator = "";
        start = true;
        firstOperand = result;
    }

    @FXML
    private void processClear(ActionEvent event) {
        displayLabel.setText("0");
        firstOperand = 0;
        operator = "";
        start = true;
    }

    private double calculate(double operand1, double operand2, String op) {
        return switch (op) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> {
                if (operand2 == 0) {
                    displayLabel.setText("Error");
                    yield 0;
                }
                yield operand1 / operand2;
            }
            default -> 0;
        };
    }
}