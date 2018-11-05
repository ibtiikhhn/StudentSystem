package com.example.studentsystem.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.studentsystem.LocalDatabase.StudentModel;
import com.example.studentsystem.R;
import com.example.studentsystem.Utils.StudentsAdapter;
import com.example.studentsystem.Utils.StudentsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StudentsActivity extends AppCompatActivity {

    StudentsViewModel studentsViewModel;

    Toolbar toolbar;
    RecyclerView recyclerView;
    StudentsAdapter studentsAdapter;
    FloatingActionButton newRecord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        studentsViewModel = ViewModelProviders.of(this).get(StudentsViewModel.class);
        studentsViewModel.getStudentsList().observe(this, new Observer<List<StudentModel>>() {
            @Override
            public void onChanged(List<StudentModel> studentModels) {
                studentsAdapter.setArrayList(studentModels);
            }
        });

        newRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentsActivity.this, NewRecord.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    public void deleteStudent(StudentModel studentModel) {
        studentsViewModel.deleteStudent(studentModel);
    }

    public void updateStudent(StudentModel studentModel) {
        studentsViewModel.updateStudent(studentModel);
    }

    public void deleteAllStudents() {
        studentsViewModel.deleteAllStudents();
    }

    public void initViews() {
        newRecord = findViewById(R.id.addRecord);
        recyclerView = findViewById(R.id.stdRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentsAdapter = new StudentsAdapter(this);
        recyclerView.setAdapter(studentsAdapter);
        toolbar = findViewById(R.id.toolbar3);

        toolbar.setTitle("Student System");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            String name = data.getStringExtra("Name");
            String department = data.getStringExtra("Department");
            String degree = data.getStringExtra("Degree");
            String email = data.getStringExtra("Email");
            Bitmap image = data.getParcelableExtra("Image");
            String bytes = getEncodedString(image);
            StudentModel studentModel = new StudentModel(name, email, degree, department, bytes);
            studentsViewModel.insertStudent(studentModel);
        }
    }

    private String getEncodedString(Bitmap bitmap){

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, (100), os);

        byte[] imageArr = os.toByteArray();

        return Base64.encodeToString(imageArr, Base64.URL_SAFE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.moreee:
                studentsViewModel.deleteAllStudents();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
