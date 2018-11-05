package com.example.studentsystem.Utils;

import android.app.Application;

import com.example.studentsystem.LocalDatabase.StudentModel;
import com.example.studentsystem.Repository.StudentOfflineRepo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class StudentsViewModel extends AndroidViewModel {
    StudentOfflineRepo studentOfflineRepo;
    LiveData<List<StudentModel>> studentsList;

    public StudentsViewModel(@NonNull Application application) {
        super(application);
        studentOfflineRepo = new StudentOfflineRepo(application);
        studentsList = studentOfflineRepo.getAllStudents();
    }

    public void deleteAllStudents() {
        studentOfflineRepo.deleteAllStudents();
    }

    public void insertStudent(StudentModel studentModel) {
        studentOfflineRepo.insert(studentModel);
    }

    public void updateStudent(StudentModel studentModel) {
        studentOfflineRepo.update(studentModel);
    }

    public void deleteStudent(StudentModel studentModel) {
        studentOfflineRepo.delete(studentModel);
    }

    public LiveData<List<StudentModel>> getStudentsList() {
        return studentOfflineRepo.getAllStudents();
    }

}
