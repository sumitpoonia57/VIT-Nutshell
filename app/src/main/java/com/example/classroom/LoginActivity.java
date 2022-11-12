package com.example.classroom;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;



public class LoginActivity extends AppCompatActivity {

    Button signupButton,loginbtn;
    TextInputEditText username_var,password_var;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signupButton=findViewById(R.id.signup);
        loginbtn=findViewById(R.id.login);
        username_var=findViewById(R.id.username);
        password_var=findViewById(R.id.password);
        mAuth=FirebaseAuth.getInstance();
        loginbtn.setOnClickListener(view -> {
            loginUser();

        });
        signupButton.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this,SignupActivity.class));
        });
    }
    private void loginUser(){
        String email=username_var.getText().toString();
        String password=password_var.getText().toString();
        if(TextUtils.isEmpty(email)){
            username_var.setError("Username cannot be empty");
            username_var.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            password_var.setError("Password cannot empty");
            password_var.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"User logged in successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this,"Log in  Error:" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}