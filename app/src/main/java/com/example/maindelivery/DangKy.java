package com.example.maindelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DangKy extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        Button registerButton = findViewById(R.id.btndangky);
        Button backToLoginButton = findViewById(R.id.btnthoat);
        db = FirebaseFirestore.getInstance();

        registerButton.setOnClickListener(v -> checkUserExistence(usernameEditText.getText().toString()));
        backToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(DangKy.this, DangNhap.class);
            startActivity(intent);
            }
        });
    }

    private void registerUser(String username, String password) {

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("username", username);
        userMap.put("password", password);

        db.collection("users")
                .add(userMap)
                .addOnSuccessListener(documentReference -> Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(DangKy.this, "Lỗi đăng ký", Toast.LENGTH_SHORT).show());
    }

    private void checkUserExistence(String username) {
        db.collection("users")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (!task.getResult().isEmpty()) {
                            // User exists
                            Toast.makeText(DangKy.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        } else {
                            // User does not exist
                            registerUser(username, passwordEditText.getText().toString());
                        }
                    } else {
                        Toast.makeText(DangKy.this, "Lỗi kiểm tra tài khoản", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}