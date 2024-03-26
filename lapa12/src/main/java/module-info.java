module lapa12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens com.example.lapa12 to javafx.fxml;
    exports com.example.lapa12;
    exports com.example.lapa12.heros;
    opens com.example.lapa12.heros to javafx.fxml;
}