package com.example.garbagcollector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.content.DialogInterface;
//import android.support.v7.app.AlertDialog;

public class SignUpActivity extends AppCompatActivity {

    //variable
    Button calllogin, register;

    TextInputLayout fname, lname, phonum, address, email, password, username;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks all variable
        calllogin = findViewById(R.id.calllogin);
        register = findViewById(R.id.register);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        phonum = findViewById(R.id.phonum);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);


        //create user account
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUser();
            }
        });

        calllogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }


    private Boolean ValidUsername(){
        String val = username.getEditText().getText().toString();
        String nowhitespace = "\\A\\w{4,20}\\z";

        if(val.isEmpty()){
            password.setError("Field Can't be Empty");
            return false;
        }
        else if(!val.matches(nowhitespace)){
            username.setError("White Space are not allowed");
            return false;

        }
        else{
            username.setError(null);
            username.setErrorEnabled(false);
            password.setError(null);
            password.setErrorEnabled(false);
            return true;

        }
    }

    private Boolean ValidEntry(){
        String val_fname = fname.getEditText().getText().toString();
        String val_lname = lname.getEditText().getText().toString();
        String val_address = address.getEditText().getText().toString();



        if(val_fname.isEmpty()){
            password.setError("Field Can't be Empty");
            return false;
        }
        else if(val_lname.isEmpty()){
            password.setError("Field Can't be Empty");
            return false;

        }
        else if(val_address.isEmpty()){
            password.setError("Field Can't be Empty");
            return false;

        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
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
            password.setErrorEnabled(false);
            return true;

        }
    }

    private Boolean ValidMobileNumber(){
        String val = phonum.getEditText().getText().toString();
        String nowhitespace = "^[0-9]{9,10}$";



        if(val.isEmpty()){
            password.setError("Field Can't be Empty");
            return false;
        }
        else if(!val.matches(nowhitespace)){
            System.out.println(val);
            phonum.setError("Invalid mobile number");
            return false;

        }
        else{
            phonum.setError(null);
            phonum.setErrorEnabled(false);
            password.setError(null);
            password.setErrorEnabled(false);
            return true;

        }
    }

    private Boolean ValidEmailAddress(){
        String val = email.getEditText().getText().toString();
        String nowhitespace = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        if(val.isEmpty()){
            password.setError("Field Can't be Empty");
            return false;
        }
        else if(!val.matches(nowhitespace)){
            email.setError("Invalid email address");
            return false;

        }
        else{
            email.setError(null);
            email.setErrorEnabled(false);
            password.setError(null);
            password.setErrorEnabled(false);
            return true;

        }
    }

    public  void RegisterUser(){


        if(!ValidUsername() | !ValidPasssword() | !ValidEntry() | !ValidMobileNumber() | !ValidEmailAddress()){
            return;
        }

        else{

            isUserExists();
        }
    }

    private void isUserExists() {

        username.requestFocus();
        username.setErrorEnabled(false);

        final String EnteredUserName = username.getEditText().getText().toString().trim();
        System.out.println(EnteredUserName);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query Checkuser = reference.orderByChild("username").equalTo(EnteredUserName);
        Checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //String passwordFromDB = dataSnapshot.child(EnteredUserName).child("password").getValue(String.class);
                    System.out.println("hello");

                    username.setError("UserName Already Exists");
                    username.requestFocus();

                }
                else {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    setRegister();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setRegister(){

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        //account variable
        String reffname = fname.getEditText().getText().toString();
        String reflname = lname.getEditText().getText().toString();
        String refphonum = phonum.getEditText().getText().toString();
        String refaddress = address.getEditText().getText().toString();
        String refemail = email.getEditText().getText().toString();
        String refpassword = password.getEditText().getText().toString();
        String refusername = username.getEditText().getText().toString();
        String reftype = "user";
        String reflong = "79.861083";
        String reflatti = "6.878662";

        //call account creation
        UserHelperClass userHelperClass = new UserHelperClass(reffname, reflname, refphonum, refaddress, refemail, refpassword, reftype, reflong, reflatti, refusername);
        reference.child(refusername).setValue(userHelperClass);
        openDialog(reffname);



    }

    public void openDialog(String name) {
       AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);

        builder.setCancelable(true);
        builder.setTitle("Succeed");
        builder.setMessage("Hi "+name+", Welcome to Trash it. Thank You to join with us.");

//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.cancel();
//            }
//        });

        builder.setPositiveButton("Go back to Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.show();
        AlertDialog dialog = builder.create();
    }
}
