package com.abhijeet14.tinteachers.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.card.MaterialCardView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhijeet14.tinteachers.AllStudentActivity;
import com.abhijeet14.tinteachers.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentsFragment extends Fragment {
    private AppCompatActivity a;
    private View v;
    private MaterialCardView firstYr, secondYr, thirdYr, fourthyr, fifthYr;
    private DatabaseReference studentRef;

    public StudentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_students, container, false);
        a = (AppCompatActivity) getActivity();
        a.getSupportActionBar().setTitle("Students");
        NavigationView nav = a.findViewById(R.id.nav_view);
        nav.setCheckedItem(R.id.nav_students);

        studentRef = FirebaseDatabase.getInstance().getReference().child("users").child("student");
        firstYr = v.findViewById(R.id.first_yr_row);
        secondYr = v.findViewById(R.id.second_yr_row);
        thirdYr = v.findViewById(R.id.third_yr_row);
        fourthyr = v.findViewById(R.id.fourth_yr_row);
        fifthYr = v.findViewById(R.id.fifth_yr_row);

        firstYr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(a,AllStudentActivity.class);
                i.putExtra("year","1");
                startActivity(i);

            }
        });

        secondYr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(a,AllStudentActivity.class);
                i.putExtra("year","2");
                startActivity(i);
            }
        });

        thirdYr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(v,"Work is pending", Snackbar.LENGTH_SHORT)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                snackbar.setActionTextColor(Color.argb(255,216,27,96));
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(Color.argb(255,0,133,119));
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);

                snackbar.show();
            }
        });

        fourthyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(v, "Work is pending", Snackbar.LENGTH_SHORT)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                snackbar.setActionTextColor(Color.argb(255,216,27,96));
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(Color.argb(255,0,133,119));
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);

                snackbar.show();
            }
        });

        fifthYr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(a,AllStudentActivity.class);
                i.putExtra("year","5");
                startActivity(i);
            }
        });
        return v;
    }
}
