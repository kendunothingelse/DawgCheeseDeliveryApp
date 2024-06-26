package com.example.maindelivery.order;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.example.maindelivery.R;
import com.example.maindelivery.order.handler.PaymentConfirmationHandler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class XacNhanThanhToan extends AppCompatActivity {
    private PaymentConfirmationHandler paymentConfirmationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xac_nhan_thanh_toan);

        paymentConfirmationHandler = new PaymentConfirmationHandler(this);

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

        findViewById(R.id.confirm).setOnClickListener(v -> paymentConfirmationHandler.confirmPayment(paymentMethod, driverName, addressReceive, addressDelivery));
        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(XacNhanThanhToan.this, ThanhToan.class);
            startActivity(intent);
        });
    }
}