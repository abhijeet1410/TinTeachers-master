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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.abhijeet14.tinteachers.FeedbackActivity;
import com.abhijeet14.tinteachers.R;
import com.abhijeet14.tinteachers.pojoclasses.FaqData;
import com.abhijeet14.tinteachers.viewholder.FaqViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {
    private View v;
    private AppCompatActivity a;
    private FirebaseRecyclerAdapter<FaqData,FaqViewHolder> f;
    private DatabaseReference faqRef;
    private RecyclerView faqView;
    private ProgressDialog pd;

    public HelpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_help, container, false);
        a = (AppCompatActivity) getActivity();
        a.getSupportActionBar().setTitle("Help");
        NavigationView nav = a.findViewById(R.id.nav_view);
        nav.setCheckedItem(R.id.nav_help);

        faqView = v.findViewById(R.id.container_faq);
        faqView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        faqView.hasFixedSize();

        pd = new ProgressDialog(getContext());
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();

        faqRef = FirebaseDatabase.getInstance().getReference().child("faq");
        FirebaseRecyclerOptions<FaqData> options = new FirebaseRecyclerOptions.Builder<FaqData>().setQuery(faqRef,FaqData.class).build();
        f = new FirebaseRecyclerAdapter<FaqData, FaqViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final FaqViewHolder holder, int position, @NonNull FaqData model) {
                faqRef.child(getRef(position).getKey()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        holder.setAnswer(dataSnapshot.child("answer").getValue().toString());
                        holder.setQuestion(dataSnapshot.child("question").getValue().toString());
                        holder.getCard().setVisibility(View.VISIBLE);
                        pd.dismiss();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
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
                    }
                });

            }

            @NonNull
            @Override
            public FaqViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.faq_row,viewGroup,false);
                return new FaqViewHolder(view);
            }
        };
        faqView.setAdapter(f);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        f.startListening();
    }
}