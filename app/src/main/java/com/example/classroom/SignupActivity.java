package com.example.classroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    TextInputEditText fullname_var,username_var,phonenumber_var,password_var;
    Button register,clickhere;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fullname_var=findViewById(R.id.name);
        username_var=findViewById(R.id.usernames);
        phonenumber_var=findViewById(R.id.phonenumber);
        password_var=findViewById(R.id.passwords);
        register=findViewById(R.id.register);
        clickhere=findViewById(R.id.clickhere);
        mAuth=FirebaseAuth.getInstance();
        register.setOnClickListener(view -> {
            createUser();
        });
        clickhere.setOnClickListener(view -> {
            startActivity(new Intent(SignupActivity.this,LoginActivity.class));
        });
    }
    private void createUser(){
        String email=username_var.getText().toString();
        String password=password_var.getText().toString();
        String phonenumber=phonenumber_var.getText().toString();
        String name=fullname_var.getText().toString();
        if(TextUtils.isEmpty(email)){
            username_var.setError("Username cannot be empty");
            username_var.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            password_var.setError("Password cannot empty");
            password_var.requestFocus();
        }else if(TextUtils.isEmpty(phonenumber)){
            phonenumber_var.setError("Phone number cannot empty");
            phonenumber_var.requestFocus();
        }else if(TextUtils.isEmpty(name)){
            fullname_var.setError("Name cannot empty");
            fullname_var.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignupActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                    }
                    else{
                        Toast.makeText(SignupActivity.this,"Registration  Error:" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}