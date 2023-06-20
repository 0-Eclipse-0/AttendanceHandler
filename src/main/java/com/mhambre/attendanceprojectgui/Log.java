package com.mhambre.attendanceprojectgui;

import java.util.*;
import java.lang.*;

public class Log {
    private
    String _firstName;
    String _lastName;
    String _dateString;
    String _timeString;
    String[] _date;
    int[] _time;

    public Log(String _lastName, String _firstName, String _time, String _date) {
        // initialize strings
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._dateString = _date;
        this._timeString = _time;

        // initialize lists
        this._date = new String[3];
        this._time = new int[3];

        // fill date array
        for (int i = 0; i < _dateString.split("/").length; i++) {
            this._date[i] = _date.split("/")[i];
        }

        for (int i = 0; i < _timeString.split(":").length; i++) {
            this._time[i] = Integer.parseInt(_time.split(":")[i]);
        }
    }

    // basic accessors and mutators
    public String get_firstName() {
        return _firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String get_lastName() {
        return _lastName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public String get_dateString() {
        return _dateString;
    }

    public void set_dateString(String _dateString) {
        this._dateString = _dateString;
    }

    public String get_timeString() {
        return _timeString;
    }

    public void set_timeString(String _timeString) {
        this._timeString = _timeString;
    }

    public String[] get_date() {
        return _date;
    }

    public void set_date(String[] _date) {
        this._date = _date;
    }

    public int[] get_time() {
        return _time;
    }

    public void set_time(int[] _time) {
        this._time = _time;
    }

    // time accessors
    public int get_secs() {
        return this._time[2];
    }

    public int get_mins() {
        return this._time[1];
    }

    public int get_hours() {
        return this._time[0];
    }

    // date accessors
    public String get_day() {
        return this._date[1];
    }

    public String get_month() {
        return this._date[0];
    }

    public String get_year() {
        return this._date[2];
    }

    @Override
    public String toString() {
        return _lastName + ", " + _firstName + ", " + _timeString + ", " + _dateString;
    }
}

class Sortbytime implements Comparator<Log> {
   public int compare(Log a, Log b) {
       Integer timeA = new Integer(a.get_secs() + a.get_mins() * 60 + a.get_hours() * 3600);
       Integer timeB = new Integer(b.get_secs() + b.get_mins() * 60 + b.get_hours() * 3600);

       return timeA.compareTo(timeB);
   }
}

class Sortbydate implements Comparator<Log> {
    public int compare(Log a, Log b) {
        if (a.get_year().compareTo(b.get_year()) <= 0) {
            if (a.get_month().compareTo(b.get_month()) <= 0) {
                if (a.get_day().compareTo(b.get_day()) <= 0) {
                    return a.get_day().compareTo(b.get_day());
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}
