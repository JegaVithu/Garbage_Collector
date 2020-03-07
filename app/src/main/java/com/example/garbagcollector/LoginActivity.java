package com.example.garbagcollector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    //variable
    Button callsingup, log_in;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //hooks
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);


        callsingup = findViewById(R.id.callsignup);
        log_in = findViewById(R.id.log_in);

        callsingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });

    }

    private Boolean ValidUsername(){
        String val = username.getEditText().getText().toString();
        String nowhitespace = "\\A\\w{4,20}\\z";

        if(val.isEmpty()){
            username.setError("Field Can't be Empty");
            return false;
        }
        else if(!val.matches(nowhitespace)){
            username.setError("White Space are not allowed");
            return false;

        }
        else{
            username.setError(null);
            return true;

        }
    }

    private Boolean ValidPasssword(){
        String val = password.getEditText().getText().toString();
        String nowhitespace = "\\A\\w{4,20}\\z";

        if(val.isEmpty()){
            password.setError("Field Can't be Empty");
            return false;
        }
        else if(!val.matches(nowhitespace)){
            password.setError("White Space are not allowed");
            return false;

        }
        else{
            password.setError(null);
            return true;

        }
    }

    public  void LoginUser(){
        if(!ValidUsername() | !ValidPasssword()){
            return;
        }
        else{
            isUser();
        }
    }

    private void isUser() {
        final String EnteredUserName = username.getEditText().getText().toString().trim();
        final String EnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query Checkuser = reference.orderByChild("username").equalTo(EnteredUserName);
        Checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    username.setError(null);
                    username.setErrorEnabled(false);


                    String passwordFromDB = dataSnapshot.child(EnteredUserName).child("password").getValue(String.class);


                    if(passwordFromDB.equals(EnteredPassword)){

                        password.setError(null);
                        password.setErrorEnabled(false);

                        Intent intent = new Intent(getApplicationContext(), MainViewActivity.class);
                        startActivity(intent);

                    }
                    else {
                        password.setError("Wrong Password!!");
                        password.requestFocus();
                    }
                }
                else {
                    username.setError("User does not exists");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
