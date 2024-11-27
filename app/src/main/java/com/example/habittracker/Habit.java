package com.example.habittracker;

public class Habit {
    private int id;
    private String name;
    private String description;
    private int targetDays;

    public Habit(int id, String name, String description, int targetDays) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.targetDays = targetDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTargetDays() {
        return targetDays;
    }

    public void setTargetDays(int targetDays) {
        this.targetDays = targetDays;
    }
}
