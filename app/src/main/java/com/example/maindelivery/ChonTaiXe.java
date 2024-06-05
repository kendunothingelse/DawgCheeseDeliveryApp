package com.example.maindelivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maindelivery.driver.DriverAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ChonTaiXe extends AppCompatActivity {
    private RecyclerView driverRecyclerView;
    private DriverAdapter driverAdapter;
    private List<String> driverList;
    private FirebaseFirestore db;
    private TextView deliveryAddressTextView, pickupAddressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chon_tai_xe);

        driverRecyclerView = findViewById(R.id.driverRecyclerView);
        driverRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        driverList = new ArrayList<>();
        driverAdapter = new DriverAdapter(driverList, this);
        driverRecyclerView.setAdapter(driverAdapter);
        String deliveryAddress = getIntent().getStringExtra("deliveryAddress");
        String pickupAddress = getIntent().getStringExtra("pickupAddress");

        // Get TextViews
        deliveryAddressTextView = findViewById(R.id.delivery_address);
        pickupAddressTextView = findViewById(R.id.pickup_address);

        // Set text
        deliveryAddressTextView.setText("Địa chỉ gửi: " + deliveryAddress);
        pickupAddressTextView.setText("Địa chỉ nhận: " + pickupAddress);
        db = FirebaseFirestore.getInstance();
        fetchDriverList();

        Button backBtn = findViewById(R.id.back_button);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ChonTaiXe.this, MainActivity.class);
            startActivity(intent);
        });

    }

    private void fetchDriverList() {
        db.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String username = document.getString("username");
                            driverList.add(username);
                        }
                        driverAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ChonTaiXe.this, "Lỗi lấy danh sách tài xế", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}