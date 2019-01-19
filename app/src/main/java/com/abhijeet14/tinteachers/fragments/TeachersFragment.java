package com.abhijeet14.tinteachers.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abhijeet14.tinteachers.R;
import com.abhijeet14.tinteachers.TeacherProfileActivity;
import com.abhijeet14.tinteachers.pojoclasses.TeachersData;
import com.abhijeet14.tinteachers.viewholder.FaqViewHolder;
import com.abhijeet14.tinteachers.viewholder.TeachersViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeachersFragment extends Fragment {
    private View v;
    private AppCompatActivity a;
    private FirebaseRecyclerAdapter<TeachersData,TeachersViewHolder> f;
    private DatabaseReference teacherRef;
    private RecyclerView teacherView;
    private ProgressDialog pd;

    public TeachersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       v = inflater.inflate(R.layout.fragment_teachers, container, false);
       a = (AppCompatActivity) getActivity();
       a.getSupportActionBar().setTitle("Teachers");
       NavigationView nav = a.findViewById(R.id.nav_view);
       nav.setCheckedItem(R.id.nav_teachers);

       teacherView = v.findViewById(R.id.teacher_container);
       teacherView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
       teacherView.hasFixedSize();

        pd = new ProgressDialog(getContext());
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();

        teacherRef = FirebaseDatabase.getInstance().getReference().child("users").child("teacher");
        FirebaseRecyclerOptions<TeachersData> options = new FirebaseRecyclerOptions.Builder<TeachersData>().setQuery(teacherRef,TeachersData.class).build();
        f = new FirebaseRecyclerAdapter<TeachersData, TeachersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final TeachersViewHolder holder, final int position, @NonNull final TeachersData model) {
                    teacherRef.child(getRef(position).getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            holder.setTvName(dataSnapshot.child("name").getValue().toString());
                            String dpUrl = dataSnapshot.child("image").getValue().toString();
                            Picasso.with(getContext()).load(dpUrl).into(holder.setTeachersDp());
                            holder.getTeacherInfo().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(getContext(),TeacherProfileActivity.class);
                                    i.putExtra("uid",getRef(position).getKey());
                                    startActivity(i);
                                }
                            });
                            pd.dismiss();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Snackbar snackbar = Snackbar
                                    .make(getView(), databaseError.getMessage(), Snackbar.LENGTH_SHORT)
                                    .setAction("OK", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                        }
                                    });
                            snackbar.setActionTextColor(Color.WHITE);
                            View sbView = snackbar.getView();
                            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                            textView.setTextColor(Color.BLUE);

                            snackbar.show();
                            pd.dismiss();
                        }
                    });
            }

            @NonNull
            @Override
            public TeachersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.teacher_row,viewGroup,false);
                return new TeachersViewHolder(view);
            }
        };
        teacherView.setAdapter(f);

       return v;


    }

    @Override
    public void onStart() {
        super.onStart();
        f.startListening();
    }
}
