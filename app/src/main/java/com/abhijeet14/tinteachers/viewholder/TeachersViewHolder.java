package com.abhijeet14.tinteachers.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.abhijeet14.tinteachers.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeachersViewHolder extends RecyclerView.ViewHolder {


    private TextView tvName;
    public View v;
    private CircleImageView teachersDp;
    private CardView teacherInfo;

    public TeachersViewHolder(@NonNull View itemView) {
        super(itemView);
        v = itemView;
        tvName = v.findViewById(R.id.row_name);
        teachersDp = itemView.findViewById(R.id.teachers_dp);
        teacherInfo = itemView.findViewById(R.id.teacher_info);

    }
    public void setTvName(String name){
        tvName.setText(name);
    }

    public CircleImageView setTeachersDp() {
        return this.teachersDp;
    }

    public CardView getTeacherInfo(){
        return this.teacherInfo;
    }
}
