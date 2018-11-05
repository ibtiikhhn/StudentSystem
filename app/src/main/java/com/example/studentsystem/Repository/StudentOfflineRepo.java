package com.example.studentsystem.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.studentsystem.LocalDatabase.StudentDao;
import com.example.studentsystem.LocalDatabase.StudentDatabase;
import com.example.studentsystem.LocalDatabase.StudentModel;

import java.util.List;

import androidx.lifecycle.LiveData;

public class StudentOfflineRepo {
    private StudentDao studentDao;
    LiveData<List<StudentModel>> allStudents;

    public StudentOfflineRepo(Application application) {
        StudentDatabase studentDatabase = StudentDatabase.getStudentDatabase(application);
        studentDao = studentDatabase.studentDao();
        allStudents = studentDao.getAllStudents();
    }

    public void update(StudentModel studentModel) {
        new UpdateStudentAsyncTask(studentDao).execute(studentModel);
    }

    public void delete(StudentModel studentModel) {
        new DeleteStudentAsyncTask(studentDao).execute(studentModel);
    }

    public void insert(StudentModel studentModel) {
        new InsertStudentAsyncTask(studentDao).execute(studentModel);
    }

    public void deleteAllStudents() {
        new DeleteAllStudentAsyncTask(studentDao).execute();
    }

    public LiveData<List<StudentModel>> getAllStudents() {
        return allStudents;
    }

    public static class UpdateStudentAsyncTask extends AsyncTask<StudentModel, Void, Void> {
        StudentDao studentDao;

        public UpdateStudentAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(StudentModel... studentModels) {
            studentDao.update(studentModels[0]);
            return null;
        }
    }

    public static class DeleteStudentAsyncTask extends AsyncTask<StudentModel, Void, Void> {
        StudentDao studentDao;

        public DeleteStudentAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(StudentModel... studentModels) {
            studentDao.delete(studentModels[0]);
            return null;
        }
    }

    public static class InsertStudentAsyncTask extends AsyncTask<StudentModel, Void, Void> {
        StudentDao studentDao;

        public InsertStudentAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(StudentModel... studentModels) {
            studentDao.insert(studentModels[0]);
            return null;
        }
    }

    public static class DeleteAllStudentAsyncTask extends AsyncTask<Void, Void, Void> {
        StudentDao studentDao;

        public DeleteAllStudentAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.deleteAll();
            return null;
        }
    }

}
