package com.example.maindelivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DatHangThanhCong extends AppCompatActivity {
    Button back_to_home, view_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dat_hang_thanh_cong);
        back_to_home = findViewById(R.id.back_to_home);
        view_order = findViewById(R.id.view_orders);
        back_to_home.setOnClickListener(v -> {
            Intent intent = new Intent(DatHangThanhCong.this, MainActivity.class);
            startActivity(intent);
        });
        view_order.setOnClickListener(v -> {
            Intent intent = new Intent(DatHangThanhCong.this, ConfirmedOrders.class);
            startActivity(intent);
        });
    }
}