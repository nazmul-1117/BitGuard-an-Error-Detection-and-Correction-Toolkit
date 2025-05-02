module com.bitguard.bitguard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.jfoenix;


    opens com.bitguard.bitguard to javafx.fxml;
    exports com.bitguard.bitguard;

    exports com.bitguard.controllers.containerController;
    opens com.bitguard.controllers.containerController to javafx.fxml;

    exports com.bitguard.controllers.home;
    opens com.bitguard.controllers.home to javafx.fxml;

    exports com.bitguard.controllers.hamming;
    opens com.bitguard.controllers.hamming to javafx.fxml;

    exports com.bitguard.controllers.crc;
    opens com.bitguard.controllers.crc to javafx.fxml;

    exports com.bitguard.controllers.checksum;
    opens com.bitguard.controllers.checksum to javafx.fxml;

    exports com.bitguard.controllers.staffing;
    opens com.bitguard.controllers.staffing to javafx.fxml;

    exports com.bitguard.controllers.manual;
    opens com.bitguard.controllers.manual to javafx.fxml;

    exports com.bitguard.controllers.about;
    opens com.bitguard.controllers.about to javafx.fxml;

    exports com.bitguard.models.hammingModel;
}