package com.mhambre.attendanceprojectgui;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AttendanceLog extends ProjectController {
    public static HashMap<String, LinkedList<Log>> _log = new HashMap();
    private static String _attendanceLogName;
    private static ProjectController _controller;

    // Constructor
    public AttendanceLog(String _attendanceLogName, ProjectController controller) {
        this._attendanceLogName = _attendanceLogName;
        this._controller = controller;
    }

    // Load roster
    public static void load_log() {
        // read in roster file
        try {
            Scanner readFile = new Scanner(new File(_attendanceLogName));

            while (readFile.hasNextLine()) {
                String usrData[] = readFile.nextLine().split(", ", 0);
                String key = String.join(", ", usrData[0], usrData[1]);

                if (!_log.containsKey(key)) { // check if key exists
                    // create bucket if it doesn't exist
                    _log.put(key, new LinkedList<Log>());
                }

                _log.get(key).add(new Log(usrData[0], usrData[1], usrData[2], usrData[3])); // insert
            }

            readFile.close();

            System.out.println("AttendanceLog loaded.");
        } catch (FileNotFoundException e) { // exception handling
            e.printStackTrace();
        }
    }

    // Print attendancelog
    public void print_collection() {
        if (_log.size() == 0) {
            System.out.println("AttendanceLog not loaded!");
            _controller.outputBox.setText("AttendanceLog not loaded!");
            return;
        }

        for (String i : _log.keySet()) { //  iterate through hashmap
            Iterator<Log> it = _log.get(i).iterator();

            while(it.hasNext()) { // iterate through bucket
                Log temp = it.next();
                System.out.println(temp);
                _controller.outputBox.setText(_controller.outputBox.getText() + temp + "\n");
            }
        }
    }

    // Print size
    public void print_size() {
        if (_log.size() == 0) {
            System.out.println("AttendanceLog not loaded!");
            _controller.outputBox.setText("AttendanceLog not loaded!");
            return;
        }

        int size = 0;

        for (String key : _log.keySet()) { // get sizes of buckets
            size += _log.get(key).size();
        }

        System.out.println("AttendanceLog Size: " + size);
        _controller.outputBox.setText("AttendanceLog Size: " + size);
    }
}

