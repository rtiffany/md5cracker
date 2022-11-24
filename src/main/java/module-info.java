module com.md5cracker.md5cracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.md5cracker.view to javafx.fxml;
    exports com.md5cracker.view;
    exports com.md5cracker.controller;
    opens com.md5cracker.controller to javafx.fxml;
}