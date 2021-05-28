package com.example.firebase;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ImageActivity extends AppCompatActivity {
    TextView tv;
    EditText category;
    Button information;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_item_list);
        tv = findViewById(R.id.tvImageUrl);
        category = findViewById(R.id.et_category);
        information = findViewById(R.id.btn_information);
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String categorySelected = category.getText().toString();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("categories");
                Query checkCategory = reference.orderByChild("category").equalTo(categorySelected);
                checkCategory.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data : snapshot.getChildren()){
                            String imageUrl = data.child("imageUrl").getValue().toString();
                            tv.setText(imageUrl);
                            System.out.println("\n");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ImageActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
