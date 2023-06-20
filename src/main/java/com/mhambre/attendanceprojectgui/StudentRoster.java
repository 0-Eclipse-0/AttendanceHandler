package com.mhambre.attendanceprojectgui;

import java.util.Iterator;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StudentRoster {
    public static LinkedList _roster = new LinkedList();
    static private String _rosterName;
    static private ProjectController _controller;

    // Constructor
    public StudentRoster(String _rosterName, ProjectController controller) {
        this._rosterName = _rosterName;
        this._controller = controller;
    }

    // Load roster
    public static void load_log() {
        // read in roster file
        try {
            Scanner readFile = new Scanner(new File(_rosterName));

            while (readFile.hasNextLine()) {
                _roster.add(new Student(readFile.nextLine()));
            }

            readFile.close();

            System.out.println("Roster loaded.");
        } catch (FileNotFoundException e) { // exception handling
            e.printStackTrace();
        }
    }

    // Print roster
    public void print_collection() {
        if (_roster.size() == 0) {
            System.out.println("Roster not loaded!");
            _controller.outputBox.setText("Roster not loaded!");
            return;
        }

        Iterator<Student> it = _roster.iterator();

        while (it.hasNext()) { // iterate through list
            Student temp = it.next();
            System.out.println(temp);
            _controller.outputBox.setText(_controller.outputBox.getText() + temp + "\n");
        }
    }

    // Print size
    public void print_size() {
        if (_roster.size() == 0) {
            System.out.println("Roster not loaded!");
            _controller.outputBox.setText("Roster not loaded!");
            return;
        }

        System.out.println("Roster Size: " + _roster.size());
        _controller.outputBox.setText("Roster Size: " + _roster.size());
    }
}
