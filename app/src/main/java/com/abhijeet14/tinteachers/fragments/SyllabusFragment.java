package com.abhijeet14.tinteachers.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.abhijeet14.tinteachers.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class SyllabusFragment extends Fragment {
    private View v;
    private AppCompatActivity a;
    private TextView firstYearSyllabus, secondYearSyllabus, thirdYearSyllabus, fourthYearSyllabus, fifthYearSyllabus;
    private DatabaseReference syllabusRef,teacherRef;
    private FirebaseAuth mAuth;
    public SyllabusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_syllabus, container, false);
        a = (AppCompatActivity) getActivity();
        a.getSupportActionBar().setTitle("Syllabus");
        NavigationView nav = a.findViewById(R.id.nav_view);
        nav.setCheckedItem(R.id.nav_syllabus);

        firstYearSyllabus = v.findViewById(R.id.firstYearUrl);
        secondYearSyllabus = v.findViewById(R.id.secondYearUrl);
        thirdYearSyllabus = v.findViewById(R.id.thirdYearUrl);
        fourthYearSyllabus = v.findViewById(R.id.fourthYearUrl);
        fifthYearSyllabus = v.findViewById(R.id.fifthYearUrl);

        mAuth = FirebaseAuth.getInstance();
        syllabusRef  = FirebaseDatabase.getInstance().getReference().child("syllabus");
        teacherRef = FirebaseDatabase.getInstance().getReference().child("users").child("teacher").child(mAuth.getCurrentUser().getUid());

        firstYearSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syllabusRef.child("first").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String url = dataSnapshot.child("url").getValue().toString();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setType("application/pdf");
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        secondYearSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syllabusRef.child("second").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String url = dataSnapshot.child("url").getValue().toString();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setType("application/pdf");
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        thirdYearSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syllabusRef.child("third").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String url = dataSnapshot.child("url").getValue().toString();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setType("application/pdf");
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        fourthYearSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syllabusRef.child("fourth").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String url = dataSnapshot.child("url").getValue().toString();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setType("application/pdf");
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        fifthYearSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syllabusRef.child("fifth").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String url = dataSnapshot.child("url").getValue().toString();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setType("application/pdf");
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        return v;
    }

}
