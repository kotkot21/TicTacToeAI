module com.example.tttai {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tttai to javafx.fxml;
    exports com.example.tttai;
}