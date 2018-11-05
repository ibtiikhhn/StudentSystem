package com.example.studentsystem.LocalDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {StudentModel.class}, version = 3)
public abstract class StudentDatabase extends RoomDatabase {

    public static StudentDatabase studentDatabase;

    public abstract StudentDao studentDao();

    public static synchronized StudentDatabase getStudentDatabase(Context context) {
        if (studentDatabase == null) {
            studentDatabase = Room.databaseBuilder(context.getApplicationContext(), StudentDatabase.class, "studentDB")
                    .addCallback(callback)
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return studentDatabase;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
//            new PopulateDatabase(studentDatabase).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
//            new PopulateDatabase(studentDatabase).execute();
        }
    };

/*
    private static class PopulateDatabase extends AsyncTask<Void, Void, Void> {
        StudentDao studentDao;

        public PopulateDatabase(StudentDatabase studentDatabase) {
            this.studentDao = studentDatabase.studentDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.insert(new StudentModel("Ali", "lsjdfl", "Software Engineering", "Android","Fa16-bse-079"));
            studentDao.insert(new StudentModel("Ali", "lsjfldfj", "Software Engineering", "Android", "Fa16-bse-079"));
            studentDao.insert(new StudentModel("kdsjfdskljfk", "lsjfldfj", "Software Engineering", "Android","Fa16-bse-079"));

            studentDao.insert(new StudentModel("Ali", "lsdjf", "Software Engineering", "Android", "Fa16-bse-079"));
            return null;
        }
    }*/


}
