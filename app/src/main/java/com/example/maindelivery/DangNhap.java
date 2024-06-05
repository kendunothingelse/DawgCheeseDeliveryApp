package com.example.maindelivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class DangNhap extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        usernameEditText = findViewById(R.id.user);
        passwordEditText = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.btndangnhap);
        Button registerButton = findViewById(R.id.btndangky);
        db = FirebaseFirestore.getInstance();

        loginButton.setOnClickListener(v -> loginUser(usernameEditText.getText().toString(), passwordEditText.getText().toString()));
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(DangNhap.this, DangKy.class);
            startActivity(intent);
        });
    }

    private void loginUser(String username, String password) {
        db.collection("users")
                .whereEqualTo("username", username)
                .whereEqualTo("password", password)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (!querySnapshot.isEmpty()) {
                            // User exists
                            for (QueryDocumentSnapshot document : querySnapshot) {
                                if (document.exists()) {
                                    Intent intent = new Intent(DangNhap.this, MainTaiXe.class);
                                    intent.putExtra("username", username);
                                    startActivity(intent);
                                    finish();
                                    break;
                                }
                            }
                        } else {
                            // User does not exist
                            Toast.makeText(DangNhap.this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DangNhap.this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}