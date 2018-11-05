package com.example.studentsystem.LocalDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class StudentModel {

    @PrimaryKey(autoGenerate = true)
    int id;

    String name;

    String email;

    String degree;

    String department;


    String bytes;

    public StudentModel(String name, String email, String degree, String department, String bytes) {
        this.name = name;
        this.email = email;
        this.degree = degree;
        this.department = department;
        this.bytes = bytes;
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

    public String getEmail() {
        return email;
    }

    public String getDegree() {
        return degree;
    }

    public String getDepartment() {
        return department;
    }


    public String getBytes() {
        return bytes;
    }
}
