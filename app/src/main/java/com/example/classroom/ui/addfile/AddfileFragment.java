package com.example.classroom.ui.addfile;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.classroom.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class AddfileFragment extends Fragment {
    FloatingActionButton linkedInA, githubA, linkedInS, githubS;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_addfile, container, false);
        linkedInA = view.findViewById(R.id.linkedinAnand);
        githubA = view.findViewById(R.id.githubAnand);
        linkedInS = view.findViewById(R.id.linkedinSumit);
        githubS = view.findViewById(R.id.githubSumit);
        linkedInA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.linkedin.com/in/anand-s-63as");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        linkedInS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.linkedin.com/in/sumit-poonia-62ba911ba/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        githubA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.github.com/prototype47");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        githubS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://github.com/sumitpoonia57");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        return view;
    }
    }

