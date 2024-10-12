module com.example.javaoblig2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.javaoblig2 to javafx.fxml;
    exports com.example.javaoblig2;
}