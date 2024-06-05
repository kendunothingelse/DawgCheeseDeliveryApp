package com.example.maindelivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.maindelivery.order.ConfirmedOrderAttributes;

public class TX_HoanThanhNhanDon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tx_hoan_thanh_nhan_don);

        // Khởi tạo các TextView
        TextView paymentMethod = findViewById(R.id.paymentMethod);
        TextView driverName = findViewById(R.id.driverName);
        TextView addressReceive = findViewById(R.id.addressReceive);
        TextView addressDelivery = findViewById(R.id.addressDelivery);
        TextView orderStatus = findViewById(R.id.orderStatus);

        // Thiết lập giá trị cho các TextView
        ConfirmedOrderAttributes order = (ConfirmedOrderAttributes) getIntent().getSerializableExtra("selectedOrder"); // Thay thế ... bằng cách lấy đối tượng order
        paymentMethod.setText(order.getPaymentMethod());
        driverName.setText(order.getDriverName());
        addressReceive.setText(order.getAddressReceive());
        addressDelivery.setText(order.getAddressDelivery());
        orderStatus.setText(order.getOrderStatus());

        // Khởi tạo các nút và thiết lập sự kiện click cho chúng
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            // Chuyển về trang chủ
            Intent intent = new Intent(this, MainTaiXe.class);
            intent.putExtra("username", order.getDriverName());
            intent.putExtra("selectedOrder", order);
            startActivity(intent);
        });

        Button mapButton = findViewById(R.id.mapButton);
        mapButton.setOnClickListener(v -> {
            // Chuyển đến map
            Intent intent = new Intent(this, TX_Map.class);
            intent.putExtra("username", order.getDriverName());
            intent.putExtra("selectedOrder", order);
            intent.putExtra("addressReceive", order.getAddressReceive());
            intent.putExtra("addressDelivery", order.getAddressDelivery());
            startActivity(intent);
        });

    }
}