package com.example.firebase;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    TextInputLayout fullName, username, email, phoneNumber, password;
    Button register, login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initView();
        registerUser();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,SignInActivity.class));
            }
        });

    }

    private Boolean validateName(){
        String val = fullName.getEditText().getText().toString();
        if (val.isEmpty()){
            fullName.setError("Please Enter name");
            fullName.setErrorTextColor(ColorStateList.valueOf(getColor(R.color.white)));
            return false;
        }
        else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername(){
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()){
            username.setError("Please Enter Username");
            username.setErrorTextColor(ColorStateList.valueOf(getColor(R.color.white)));
            return false;
        }
        else if (val.length()>=15){
            username.setError("Username should be less than 15 letters");
            username.setErrorTextColor(ColorStateList.valueOf(getColor(R.color.white)));
            return false;
        }
        else if (val.length()<=6){
            username.setError("Username must but more than 6 letters");
            username.setErrorTextColor(ColorStateList.valueOf(getColor(R.color.white)));
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail(){
        String val = email.getEditText().getText().toString();
        if (val.isEmpty()){
            email.setError("Please Enter Email");
            email.setErrorTextColor(ColorStateList.valueOf(getColor(R.color.white)));
            return false;
        }
        else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhone(){
        String val = phoneNumber.getEditText().getText().toString();
        if (val.isEmpty()){
            phoneNumber.setError("Please Enter Phone number");
            phoneNumber.setErrorTextColor(ColorStateList.valueOf(getColor(R.color.white)));
            return false;
        }
        else if (val.length()>10 || val.length()<10){
            phoneNumber.setError("Invalid Phone Number");
            phoneNumber.setErrorTextColor(ColorStateList.valueOf(getColor(R.color.white)));
            return false;
        }
        else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()){
            password.setError("Please Enter password");
            password.setErrorTextColor(ColorStateList.valueOf(getColor(R.color.white)));
            return false;
        }else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private void registerUser() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName() | !validateEmail() | !validatePassword() | !validatePhone() | !validateUsername())
                    return;

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

                String name = fullName.getEditText().getText().toString();
                String userName = username.getEditText().getText().toString();
                String e_mail = email.getEditText().getText().toString();
                String number = phoneNumber.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();
                String uid = reference.push().getKey();

                User user = new User(name, userName, e_mail, number, pass,uid);
                reference.child(number).setValue(user);

                Intent in = new Intent(RegistrationActivity.this,UserProfileActivity.class);
                startActivity(in);

                fullName.getEditText().setText("");
                username.getEditText().setText("");
                phoneNumber.getEditText().setText("");
                email.getEditText().setText("");
                password.getEditText().setText("");


            }
        });
    }

    private void initView() {
        fullName = findViewById(R.id.et_full_name);
        username = findViewById(R.id.et_user_name);
        email = findViewById(R.id.et_email);
        phoneNumber = findViewById(R.id.et_phone_number);
        password = findViewById(R.id.et_password);
        register = findViewById(R.id.btn_register);
        login = findViewById(R.id.btn_login);

    }
}
