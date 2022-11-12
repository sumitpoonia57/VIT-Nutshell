
package com.example.classroom.file;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.classroom.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileActivity extends AppCompatActivity {
    private RecyclerView FileRecycler;
    private DatabaseReference reference;
    private List<FileData> list;
    private FileAdapter adapter;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("File");
        FileRecycler=findViewById(R.id.FileRecycler);
        reference= FirebaseDatabase.getInstance().getReference().child("FILE");
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
                    FileData data=snapshot.getValue(FileData.class);
                    list.add(data);
                }
                adapter=new FileAdapter(FileActivity.this,list);
                FileRecycler.setLayoutManager(new LinearLayoutManager(FileActivity.this));
                FileRecycler.setAdapter(adapter);
                shimmerFrameLayout.stopShimmer();
                shimmerLayout.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FileActivity.this,"Data base Error", Toast.LENGTH_SHORT).show();

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