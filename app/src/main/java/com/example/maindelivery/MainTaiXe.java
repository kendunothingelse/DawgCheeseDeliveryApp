package com.example.maindelivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maindelivery.order.ConfirmedOrderAttributes;

public class MainTaiXe extends AppCompatActivity {

    private TextView usernameTextView;
    private Button viewListButton, viewMapButton;
    String addressReceive, addressDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tai_xe);

        ConfirmedOrderAttributes order = (ConfirmedOrderAttributes) getIntent().getSerializableExtra("selectedOrder");

        // Gán thông tin địa chỉ nhận và giao hàng vào hai biến
        if (order != null) {
            addressReceive = order.getAddressReceive();
            addressDelivery = order.getAddressDelivery();
        }


        String username = getIntent().getStringExtra("username");
        usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText(username);
        Intent intent = new Intent(this, TX_OrderList.class);
        intent.putExtra("username", username);
        //Xem danh sách đơn hàng
        viewListButton = findViewById(R.id.viewListButton);
        viewListButton.setOnClickListener(v -> {
            Intent intent2 = new Intent(this, TX_OrderList.class);
            startActivity(intent2);
            startActivity(intent);
        });

        //Xem bản đồ
        viewMapButton = findViewById(R.id.viewMapButton);
        viewMapButton.setOnClickListener(v -> {
            Intent intent3 = new Intent(this, TX_Map.class);
            intent3.putExtra("username", username);
            if (order != null) {
                intent3.putExtra("selectedOrder", order);
                intent3.putExtra("addressReceive", addressReceive);
                intent3.putExtra("addressDelivery", addressDelivery);
            }

            startActivity(intent3);
        });
        Toast.makeText(this, "Đã đăng nhập với tên người dùng: " + username, Toast.LENGTH_SHORT).show();
    }
}