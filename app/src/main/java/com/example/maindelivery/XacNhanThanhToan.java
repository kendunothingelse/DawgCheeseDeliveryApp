package com.example.maindelivery;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class XacNhanThanhToan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xac_nhan_thanh_toan);

        // Get data from Intent
        String paymentMethod = getIntent().getStringExtra("paymentMethod");
        String driverName = getIntent().getStringExtra("driverName");
        String addressReceive = getIntent().getStringExtra("addressReceive");
        String addressDelivery = getIntent().getStringExtra("addressDelivery");

        // Get TextViews
        TextView paymentMethodTextView = findViewById(R.id.total);
        TextView driverTextView = findViewById(R.id.driver);
        TextView addressReceiveTextView = findViewById(R.id.address_receive);
        TextView addressDeliveryTextView = findViewById(R.id.address_delivery);

        // Set text
        paymentMethodTextView.setText("Phương thức thanh toán: " + paymentMethod);
        driverTextView.setText("Tài xế: " + driverName);
        addressReceiveTextView.setText("Địa chỉ nhận: " + addressReceive);
        addressDeliveryTextView.setText("Địa chỉ giao: " + addressDelivery);

        findViewById(R.id.confirm).setOnClickListener(v -> confirmPayment(paymentMethod, driverName, addressReceive, addressDelivery));
        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(XacNhanThanhToan.this, ThanhToan.class);
            startActivity(intent);
        });
    }

    private void confirmPayment(String paymentMethod, String driverName, String addressReceive, String addressDelivery) {
        new AlertDialog.Builder(this)
                .setTitle("Bạn có chắc chắn không?")
                .setNegativeButton("Hủy", null)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Create a new order
                        Map<String, Object> order = new HashMap<>();
                        order.put("paymentMethod", paymentMethod);
                        order.put("driverName", driverName);
                        order.put("addressReceive", addressReceive);
                        order.put("addressDelivery", addressDelivery);
                        order.put("orderStatus", "Chưa giao");

                        // Save the order to Firestore
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("confirm_orders").add(order);

                        // Navigate to DatHangThanhCong
                        Intent intent = new Intent(XacNhanThanhToan.this, DatHangThanhCong.class);
                        startActivity(intent);
                    }
                })
                .show();
    }
}