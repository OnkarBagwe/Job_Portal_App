package com.example.jobportalapp.Model;

public class Data {

    String title;
    String description;
    String skills;
    String salary;

    String id;
    String date;
    String deadline;
    Float dateValue;

    public Data(){

    }

    public Data(String title, String description, String skills, String salary, String id, String date, String deadline, Float dateValue) {
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.salary = salary;
        this.id = id;
        this.date = date;
        this.deadline = deadline;
        this.dateValue = dateValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Float getDateValue() {
        return dateValue;
    }

    public void setDateValue(Float dateValue) {
        this.dateValue = dateValue;
    }
}
