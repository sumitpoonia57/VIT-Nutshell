package com.example.classroom.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.classroom.R;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudyActivity extends AppCompatActivity {
    private RecyclerView ebookRecycler;
    private DatabaseReference reference;
    private List<StudyData>list;
    private StudyAdapter adapter;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ebooks");
        ebookRecycler=findViewById(R.id.ebookRecycler);
        reference= FirebaseDatabase.getInstance().getReference().child("PDF");
        getData();
        shimmerFrameLayout=findViewById(R.id.shimmer_view_container);
        shimmerLayout=findViewById(R.id.shimmer_layout);

    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list=new ArrayList<>();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    StudyData data=snapshot.getValue(StudyData.class);
                    list.add(data);
                }
                adapter=new StudyAdapter(StudyActivity.this,list);
                ebookRecycler.setLayoutManager(new LinearLayoutManager(StudyActivity.this));
                ebookRecycler.setAdapter(adapter);
                shimmerFrameLayout.stopShimmer();
                shimmerLayout.setVisibility(View.GONE);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudyActivity.this,"Data base Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    protected void onPause(){
        shimmerFrameLayout.stopShimmer();
        super.onPause();

    }
    @Override
    protected void onResume(){
        shimmerFrameLayout.startShimmer();
        super.onResume();
    }
}