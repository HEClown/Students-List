package com.example.studentslist.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Spinner;

// Описание элемента в списке студентов
public class StudentModel {

    int id;
    String firstName;
    String secondName;

    public StudentModel(int id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

}