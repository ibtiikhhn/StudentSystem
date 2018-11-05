package com.example.studentsystem.LocalDatabase;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StudentDao {

    @Insert
    void insert(StudentModel studentModel);

    @Delete
    void delete(StudentModel studentModel);

    @Update
    void update(StudentModel studentModel);

    @Query("DELETE from student_table")
    void deleteAll();

    @Query("SELECT * FROM student_table")
    LiveData<List<StudentModel>> getAllStudents();

}
