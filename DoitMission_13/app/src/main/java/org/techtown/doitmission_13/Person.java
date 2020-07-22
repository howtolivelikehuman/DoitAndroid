package org.techtown.doitmission_13;

public class Person {
    String Name;
    String Date;
    String Number;

    public Person(String name, String date, String number) {
        Name = name;
        Date = date;
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
