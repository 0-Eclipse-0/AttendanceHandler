package com.mhambre.attendanceprojectgui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;

public class ProjectController {
    // Standard Variables
    private
    static String _rosterName = null;
    static String _attendanceLogName = null;
    static AttendanceApp _attendanceApp;

    // JavaFX Variables

    @FXML
    private ComboBox optionsList;

    @FXML
    public TextArea outputBox;

    @FXML
    private MenuItem loadButton;

    @FXML
    private Button runButton;

    @FXML
    private Button clearButton;

    @FXML
    private TextField timeInput;

    @FXML
    private TextField dateInput;

    @FXML
    private TextField nameInput;

    @FXML
    protected void loadRosterFile() {
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.showOpenDialog(null);
        _rosterName = f.getAbsolutePath();

        if (_attendanceLogName != null) { // enable load button
            loadButton.setDisable(false);
        }
    }

    @FXML
    protected void loadLogFile() {
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.showOpenDialog(null);
        _attendanceLogName = f.getAbsolutePath();

        if (_rosterName != null) {
            loadButton.setDisable(false);
            loadButton.setVisible(true);
        }
    }

    @FXML
    protected void runProgram() {
        _attendanceApp = new AttendanceApp(_rosterName, _attendanceLogName, this);

        // Load files
        _attendanceApp.load_roster();
        _attendanceApp.load_log();

        // Enable buttons
        optionsList.setDisable(false);
        runButton.setDisable(false);
        clearButton.setDisable(false);
        timeInput.setDisable(false);
        dateInput.setDisable(false);
        nameInput.setDisable(false);

        optionsList.getItems().addAll(
                "B - print_log()",
                "C - print_count() (Log)",
                "E - print_roster()",
                "F - print_count() (Roster)",
                "G - list_students_not_in_class()",
                "H - list_all_times_checking_in_and_out()",
                "I - list_all_times_checked_in()",
                "J - list_students_late_to_class()",
                "K - get_first_student_to_enter()",
                "L - print_attendance_data_for_student()",
                "M - is_present()",
                "N - list_all_students_checked_in()",
                "O - list_all_students_checked_in_before()",
                "P - list_students_attendance_count()",
                "Q - get_first_student_to_enter()");
    }

    @FXML
    protected void runAction() {
        doAction(optionsList.getValue().toString());
    }

    @FXML
    protected void clearField() {
        outputBox.setText("");
    }

    @FXML
    protected void onExit() {
        System.exit(0);
    }

    @FXML
    protected void doAction(String usrChoice) {
        switch (usrChoice) {
            case ("A - load_log()"):
                // build student log
                _attendanceApp.load_log();
                break;
            case ( "B - print_log()"):
                // print log
                _attendanceApp.print_log();
                break;
            case ("C - print_count() (Log)"):
                // print count
                _attendanceApp.log_size();
                break;
            case ("D - load_log()"):
                // build student roster
                _attendanceApp.load_roster();
                break;
            case ("E - print_roster()"):
                // print roster
                _attendanceApp.print_roster();
                break;
            case ("F - print_count() (Roster)"):
                // print count
                _attendanceApp.roster_size();
                break;
            case ("G - list_students_not_in_class()"):
                _attendanceApp.list_students_not_in_class();
                break;
            case ("H - list_all_times_checking_in_and_out()"):
                _attendanceApp.list_all_times_checking_in_and_out(nameInput.getText());
                break;
            case ("I - list_all_times_checked_in()"):
                _attendanceApp.list_all_times_checked_in();
                break;
            case ("J - list_students_late_to_class()"):
                _attendanceApp.list_students_late_to_class(timeInput.getText());
                break;
            case ("K - get_first_student_to_enter()"):
                _attendanceApp.get_first_student_to_enter(dateInput.getText());
                break;
            case ("L - print_attendance_data_for_student()"):
                _attendanceApp.print_attendance_data_for_student(nameInput.getText());
                break;
            case ("M - is_present()"):
                outputBox.setText(
                (_attendanceApp.is_present(nameInput.getText(), dateInput.getText())) ? "True" : "False");
                break;
            case ("N - list_all_students_checked_in()"):
                _attendanceApp.list_all_students_checked_in(dateInput.getText());
                break;
            case ("O - list_all_students_checked_in_before()"):
                _attendanceApp.list_all_students_checked_in_before(dateInput.getText(), timeInput.getText());
                break;
            case ("P - list_students_attendance_count()"):
                _attendanceApp.list_students_attendance_count(Integer.parseInt(dateInput.getText()));
                break;
            case ("Q - get_first_student_to_enter()"):
                _attendanceApp.get_first_student_to_enter(dateInput.getText());
                break;
        }
    }

    @FXML
    protected void lockBoxes() { // lock inputs when not needed
        // Reset date box
        dateInput.setText("");
        dateInput.setPromptText("Date");

        switch (optionsList.getValue().toString()) {
            case ("A - load_log()"):
                // build student log
                timeInput.setDisable(true);
                dateInput.setDisable(true);
                nameInput.setDisable(true);
                break;
            case ( "B - print_log()"):
                // print log
                timeInput.setDisable(true);
                dateInput.setDisable(true);
                nameInput.setDisable(true);
                break;
            case ("C - print_count() (Log)"):
                timeInput.setDisable(true);
                dateInput.setDisable(true);
                nameInput.setDisable(true);
                break;
            case ("D - load_log()"):
                // build student roster
                timeInput.setDisable(true);
                dateInput.setDisable(true);
                nameInput.setDisable(true);
                break;
            case ("E - print_roster()"):
                // print roster
                timeInput.setDisable(true);
                dateInput.setDisable(true);
                nameInput.setDisable(true);
                break;
            case ("F - print_count() (Roster)"):
                // print count
                timeInput.setDisable(true);
                dateInput.setDisable(true);
                nameInput.setDisable(true);
                break;
            case ("G - list_students_not_in_class()"):
                timeInput.setDisable(true);
                dateInput.setDisable(true);
                nameInput.setDisable(true);
                break;
            case ("H - list_all_times_checking_in_and_out()"):
                timeInput.setDisable(true);
                dateInput.setDisable(true);
                nameInput.setDisable(false);
                break;
            case ("I - list_all_times_checked_in()"):
                timeInput.setDisable(true);
                dateInput.setDisable(true);
                nameInput.setDisable(true);
                break;
            case ("J - list_students_late_to_class()"):
                timeInput.setDisable(false);
                dateInput.setDisable(true);
                nameInput.setDisable(true);
                break;
            case ("K - get_first_student_to_enter()"):
                timeInput.setDisable(true);
                dateInput.setDisable(false);
                nameInput.setDisable(true);
                break;
            case ("L - print_attendance_data_for_student()"):
                timeInput.setDisable(true);
                dateInput.setDisable(true);
                nameInput.setDisable(false);
                break;
            case ("M - is_present()"):
                timeInput.setDisable(true);
                dateInput.setDisable(false);
                nameInput.setDisable(false);
                break;
            case ("N - list_all_students_checked_in()"):
                timeInput.setDisable(true);
                dateInput.setDisable(false);
                nameInput.setDisable(true);
                break;
            case ("O - list_all_students_checked_in_before()"):
                timeInput.setDisable(false);
                dateInput.setDisable(false);
                nameInput.setDisable(true);
                break;
            case ("P - list_students_attendance_count()"):
                timeInput.setDisable(true);
                dateInput.setDisable(false);
                nameInput.setDisable(true);

                // Change input for attendance count purposes
                dateInput.setText("");
                dateInput.setPromptText("Num Classes");
            case ("Q - get_first_student_to_enter()"):
                timeInput.setDisable(true);
                dateInput.setDisable(false);
                nameInput.setDisable(true);
                break;
        }
    }
}