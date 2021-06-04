package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnFirebase, btnFirebaseCrudOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        initListener();
    }

    void initView()
    {
        btnFirebase = findViewById(R.id.btnFirebase);
        btnFirebaseCrudOperations = findViewById(R.id.btnFirebaseCrudOperations);
    }
    void initListener()
    {
        btnFirebase.setOnClickListener(this);
        btnFirebaseCrudOperations.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnFirebase:
                {
                 startActivity(new Intent(this, SignInActivity.class));
                    break;
                }
            case R.id.btnFirebaseCrudOperations:
                {
                 startActivity(new Intent(this, FirebaseCrudActivity.class));
                    break;
                }
        }
    }
}