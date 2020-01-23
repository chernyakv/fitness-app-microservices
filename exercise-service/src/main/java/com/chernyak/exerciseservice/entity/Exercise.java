package com.chernyak.exerciseservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document(collection = "exercise")
public class Exercise {
    @Id
    private String id;
    private double calories;
    private double water;
    private double sleep;
    private String activity;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private String userId;

    public Exercise() {
    }

    public Exercise(double calories, double water, double sleep, String activity) {
        this.calories = calories;
        this.water = water;
        this.sleep = sleep;
        this.activity = activity;
    }

    public Exercise(double calories, double water, double sleep, String activity, LocalDate date) {
        this.calories = calories;
        this.water = water;
        this.sleep = sleep;
        this.activity = activity;
        this.date = date;
    }

    public Exercise(double calories, double water, double sleep, String activity, LocalDate date, String userId) {
        this.calories = calories;
        this.water = water;
        this.sleep = sleep;
        this.activity = activity;
        this.date = date;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public double getSleep() {
        return sleep;
    }

    public void setSleep(double sleep) {
        this.sleep = sleep;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
