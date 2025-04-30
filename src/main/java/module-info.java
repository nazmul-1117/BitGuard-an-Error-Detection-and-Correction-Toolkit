module com.bitguard.bitguard {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bitguard.bitguard to javafx.fxml;
    exports com.bitguard.bitguard;
    exports com.bitguard.controllers;
    opens com.bitguard.controllers to javafx.fxml;
}