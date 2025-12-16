module com.eyuphalitinci.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.eyuphalitinci.calculator to javafx.fxml;
    exports com.eyuphalitinci.calculator;
}