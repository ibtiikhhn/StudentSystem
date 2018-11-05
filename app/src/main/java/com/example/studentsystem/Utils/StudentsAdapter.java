package com.example.studentsystem.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentsystem.LocalDatabase.StudentModel;
import com.example.studentsystem.R;
import com.example.studentsystem.UI.StudentsActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentsHolder> {

    List<StudentModel> arrayList = new ArrayList<>();
    Context context;
     public void setArrayList(List<StudentModel> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public StudentsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StudentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_student_recycler, parent, false);
        StudentsHolder studentsHolder = new StudentsHolder(view);
        return studentsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentsHolder holder, int position) {
        holder.stdName.setText(arrayList.get(position).getName());
        Bitmap bitmap = getBitmapFromEncodedString(arrayList.get(position).getBytes());
        holder.stdImage.setImageBitmap(bitmap);
        holder.stdDepartment.setText(arrayList.get(position).getDepartment());
        holder.stdEmail.setText(arrayList.get(position).getEmail());

        holder.stdMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.stdMore);
                popupMenu.inflate(R.menu.popup);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.deleteStudent:
                                ((StudentsActivity)context).deleteStudent(arrayList.get(0));
                                break;
                            default:
                                break;
                        }


                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }


    private Bitmap getBitmapFromEncodedString(String encodedString){

        byte[] arr = Base64.decode(encodedString, Base64.URL_SAFE);

        return BitmapFactory.decodeByteArray(arr, 0, arr.length);

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class StudentsHolder extends RecyclerView.ViewHolder {
        ImageView stdImage;
        ImageView stdMore;
        TextView stdName;
        TextView stdEmail;
        TextView stdDepartment;

        public StudentsHolder(@NonNull View itemView) {
            super(itemView);

            stdImage = itemView.findViewById(R.id.stdImage);
            stdMore = itemView.findViewById(R.id.stdMore);
            stdName = itemView.findViewById(R.id.stdName);
            stdEmail = itemView.findViewById(R.id.stdEmail);
            stdDepartment = itemView.findViewById(R.id.department);
        }
    }
}
