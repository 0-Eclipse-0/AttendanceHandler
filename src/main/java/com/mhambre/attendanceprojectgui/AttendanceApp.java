package com.mhambre.attendanceprojectgui;

import javafx.css.Style;

import java.util.*;

public class AttendanceApp {
    // Members
    StudentRoster _roster;
    AttendanceLog _log;
    ProjectController _controller;

    // Constructor
    public AttendanceApp(String _rosterName, String _attendanceLogName, ProjectController controller) {
        _controller = controller;
        _log = new AttendanceLog(_attendanceLogName, controller);
        _roster = new StudentRoster(_rosterName, controller);
    }

    // Load Methods
    public void load_roster() {
        StudentRoster.load_log();
    }

    public void load_log() {
        AttendanceLog.load_log();
    }

    // Basic Print Methods
    public void print_roster() {
        _roster.print_collection();
    }

    public void print_log() {
        _log.print_collection();
    }

    // Size methods
    public void roster_size() {
        _roster.print_size();
    }

    public void log_size() {
        _log.print_size();
    }

    // Attendance App Functions
    public void list_students_not_in_class() {
        LinkedList<Student> returnList = new LinkedList<>();

        // iterate through roster and only print those nonexistant in the log
        for (Student student : (Iterable<Student>) StudentRoster._roster) {
            String key = student.get_studentName();

            if (!AttendanceLog._log.containsKey(key)) {  // add missing student to return list
                returnList.add(new Student(key));
            }
        }

        print_query_list(returnList);
        print_count(returnList);
    }

    public void list_all_times_checking_in_and_out(String studentName) {
        LinkedList<Log> returnList = new LinkedList<>();

        System.out.println("****** List all swipe in and out for a student *******");
        if (AttendanceLog._log.containsKey(studentName)) {  // find student
            Iterator<Log> it = AttendanceLog._log.get(studentName).iterator();

            while (it.hasNext()) { // check all instances
                returnList.add(it.next());
            }
        }

        print_query_list(returnList);
        print_count(returnList);
    }

    public void list_all_times_checked_in() {
        LinkedList<Log> returnList = new LinkedList<Log>();

        // Sort each bucket and print first item

        for (String key : AttendanceLog._log.keySet()) {
            int counter = 1;
            Collections.sort(AttendanceLog._log.get(key), new Sortbytime().thenComparing(new Sortbydate()));

            for (Log student : AttendanceLog._log.get(key)) { // iterate through bucket
                if (counter % 2 == 1) { // only odd numbers should be check-ins after the list is sorted
                    returnList.add(student);
                }

                counter += 1;
            }
        }

        print_query_list(returnList);
        print_count(returnList);
    }

    public void list_students_late_to_class(String time) {
        Log comparisonStudent = new Log("nil", "nil", time, "nil");
        LinkedList<Log> returnList = new LinkedList<Log>();
        LinkedList<Log> checkInList = new LinkedList<Log>();

        // Get checkins
        for (String key : AttendanceLog._log.keySet()) {
            int counter = 1;
            Collections.sort(AttendanceLog._log.get(key), new Sortbytime().thenComparing(new Sortbydate()));

            for (Log student : AttendanceLog._log.get(key)) { // iterate through bucket
                if (counter % 2 == 1) { // only odd numbers should be check-ins after the list is sorted
                    checkInList.add(student);
                }

                counter += 1;
            }
        }

        // Find all late students
        for (Log i : checkInList) {
            int comparison = new Sortbytime().compare(i, comparisonStudent);

            if (comparison > 0) { // if student is late print
                returnList.add(i);
            }
        }

        print_query_list(returnList);
        print_count(returnList);
    }

    public void get_first_student_to_enter(String date) {
        Log earliest = null;
        int comparison;

        for (String key : AttendanceLog._log.keySet()) { // iterate through hashmap of names

            for (Log student : AttendanceLog._log.get(key)) { // iterate through bucket
                if (student.get_dateString().compareTo(date) == 0) { // ensure date is the same
                    if (earliest == null) { // handle first student checked
                        earliest = AttendanceLog._log.get(key).get(0);
                    } else {
                        comparison = new Sortbytime().compare(AttendanceLog._log.get(key).get(0), earliest); // compare earliest to current

                        earliest = (comparison <= 0) ? AttendanceLog._log.get(key).get(0) : earliest;
                    }
                }
            }
        }

        System.out.println(earliest);
        _controller.outputBox.setText(earliest.toString());
    }

    public void list_all_students_checked_in(String date) {
        Log comparisonStudent = new Log("nil", "nil", "00:00:00", date); // dummy instance
        LinkedList<Student> returnList = new LinkedList<>();

        // Sort each bucket and print first item
        for (String key : _log._log.keySet()) {
            for (Log log : AttendanceLog._log.get(key)) {
                if (new Sortbydate().compare(log, comparisonStudent) == 0) {
                    // output found student
                    System.out.println(String.join(", ", log.get_lastName(), log.get_firstName()));
                    returnList.add(new Student(
                            String.join(", ", log.get_lastName(), log.get_firstName())));
                    break; // break inner loop
                }
            }
        }

        print_query_list(returnList);
        print_count(returnList);
    }

    public void print_attendance_data_for_student(String name) {
        if (AttendanceLog._log.containsKey(name)) {
            System.out.print(name + " [");
            _controller.outputBox.setText(name + " [");
            for (Log i : AttendanceLog._log.get(name)) {
                System.out.print("\'" + i.get_timeString() + ", " + i.get_dateString() + "\', "); // format data
                _controller.outputBox.setText(_controller.outputBox.getText() +
                        "\'" + i.get_timeString() + ", " + i.get_dateString() + "\', ");
            }
            System.out.println("\b\b] "); // remove and replace ending chars
            _controller.outputBox.setText(_controller.outputBox.getText() + "\b\b] ");
        } else {
            System.out.println("No student of this name in the attendance log");
        }
    }

    public void list_all_students_checked_in_before(String date, String time) {
        Log comparisonStudent = new Log("nil", "nil", time, date); // dummy instance
        LinkedList<Student> returnList = new LinkedList<>();

        // Sort each bucket and print first item
        for (String key : _log._log.keySet()) {
            for (Log log : AttendanceLog._log.get(key)) { // iterate bucket
                if (new Sortbydate().compare(log, comparisonStudent) == 0) {
                    if (new Sortbytime().compare(log, comparisonStudent) < 0) {
                        returnList.add(new Student(
                                String.join(", ", log.get_lastName(), log.get_firstName())));
                        break; // break inner loop in case they check out before time
                    }
                }
            }
        }

        print_query_list(returnList);
        print_count(returnList);
    }

    public void list_students_attendance_count(int numClasses) {
        LinkedList<Student> returnList = new LinkedList<Student>();

        // Get checkins
        for (String key : AttendanceLog._log.keySet()) {
            int counter = 1;
            int matchCount = 0;

            Collections.sort(AttendanceLog._log.get(key), new Sortbytime().thenComparing(new Sortbydate()));

            for (Log student : AttendanceLog._log.get(key)) { // iterate through bucket
                if (counter % 2 == 1) { // only odd numbers should be check-ins after the list is sorted
                    matchCount += 1;
                }

                counter += 1;
            }

            if (matchCount == numClasses) {
                returnList.add(new Student(key));
            }
        }

        print_query_list(returnList);
        print_count(returnList);
    }

    public boolean is_present(String student_name, String date) {
        Log comparisonStudent = new Log("nil", "nil", "00:00:00", date); // dummy instance

        // Sort each bucket and print first item
        if (AttendanceLog._log.containsKey(student_name)) {

            for (Log log : AttendanceLog._log.get(student_name)) {
                if (new Sortbydate().compare(log, comparisonStudent) == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public <I> void print_query_list(LinkedList<I> list) {
        for (I i : list) {
            System.out.println(i);
            _controller.outputBox.setText(_controller.outputBox.getText() + i + "\n");
        }
    }

    public <I> void print_count(LinkedList<I> list) {
        System.out.println("There were " + list.size() + "records for this query");
        _controller.outputBox.setText(_controller.outputBox.getText() + "There were " + list.size()
                + " records for this query\n");
    }
}
