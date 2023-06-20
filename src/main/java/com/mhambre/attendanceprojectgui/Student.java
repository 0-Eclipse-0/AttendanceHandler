package com.mhambre.attendanceprojectgui;

public class Student extends Object {
    private String _studentName;

    // Constructor
    public Student(String name) {
        _studentName = name;
    }

    // Getters and setters
    public String get_studentName() {
        return this._studentName;
    }

    public void set_studentName(String _studentName) {
        this._studentName = _studentName;
    }

    @Override
    public String toString() {
        return this._studentName;
    }
}
