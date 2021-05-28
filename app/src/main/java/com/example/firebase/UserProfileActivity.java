package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileActivity extends AppCompatActivity {
    Button information,uploadImage;
    TextView name, username, phoneNumber, email,password;
    EditText number;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_activity);
        initView();
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this,AddingImageActivity.class);
                startActivity(intent);
            }
        });
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userNumber = number.getText().toString();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String userNameDB = (String) snapshot.child(userNumber).child("username").getValue();
                        String userNumberDB = (String) snapshot.child(userNumber).child("phoneNumber").getValue();
                        String userEmailDB = (String) snapshot.child(userNumber).child("email").getValue();
                        String useranmeDB = (String) snapshot.child(userNumber).child("fullName").getValue();
                        String userpasswordDB = (String) snapshot.child(userNumber).child("password").getValue();

                        name.setText(useranmeDB);
                        username.setText(userNameDB);
                        phoneNumber.setText(userNumberDB);
                        email.setText(userEmailDB);
                        password.setText(userpasswordDB);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(UserProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void initView() {
        information = findViewById(R.id.btn_information);
        uploadImage = findViewById(R.id.btn_image);
        name = findViewById(R.id.tv_nmae);
        phoneNumber = findViewById(R.id.tv_phoneNumber);
        username = findViewById(R.id.tv_username);
        email = findViewById(R.id.tv_email);
        number = findViewById(R.id.et_number);
        password = findViewById(R.id.tv_password);
    }
}
