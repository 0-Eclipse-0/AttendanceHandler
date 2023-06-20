module com.mhambre.attendanceprojectgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mhambre.attendanceprojectgui to javafx.fxml;
    exports com.mhambre.attendanceprojectgui;
}