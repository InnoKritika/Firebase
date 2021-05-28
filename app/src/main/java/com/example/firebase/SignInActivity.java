package com.example.firebase;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    TextInputLayout userUsername, userPassword;
    Button signIn, newUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);
        initView();
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, RegistrationActivity.class));
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin() {
        final  String username = userUsername.getEditText().getText().toString();
        final String password = userPassword.getEditText().getText().toString();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("phoneNumber").equalTo(username);
        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    userUsername.setError(null);
                    userUsername.setErrorEnabled(false);
                    String userPasswordDB = (String) snapshot.child(username).child("password").getValue();

                    if (userPasswordDB.equals(password)){
                        userPassword.setError(null);
                        userPassword.setErrorEnabled(false);
                        startActivity(new Intent(SignInActivity.this, UserProfileActivity.class));
                    }
                    else {
                        userPassword.setError("Wrong Password");
                        userPassword.setErrorTextColor(ColorStateList.valueOf(getColor(R.color.white)));
                        userPassword.requestFocus();
                    }
                }
                else {
                    userUsername.setError("No such Username exists");
                    userUsername.setErrorTextColor(ColorStateList.valueOf(getColor(R.color.white)));
                    userUsername.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView() {
        userPassword = findViewById(R.id.et_password1);
        userUsername = findViewById(R.id.et_user_name1);
        signIn = findViewById(R.id.btn_sign_in);
        newUser = findViewById(R.id.btn_new_user);
    }

    private Boolean validateUsername(){
        String val = userUsername.getEditText().getText().toString();
        if (val.isEmpty()){
            userUsername.setError("Please Enter Username");
            userUsername.setErrorTextColor(ColorStateList.valueOf(getColor(R.color.white)));
            return false;
        }
        else {
            userUsername.setError(null);
            userUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val = userPassword.getEditText().getText().toString();
        if (val.isEmpty()){
            userPassword.setError("Please Enter password");
            userPassword.setErrorTextColor(ColorStateList.valueOf(getColor(R.color.white)));
            return false;
        }else {
            userPassword.setError(null);
            userPassword.setErrorEnabled(false);
            return true;
        }
    }
}
