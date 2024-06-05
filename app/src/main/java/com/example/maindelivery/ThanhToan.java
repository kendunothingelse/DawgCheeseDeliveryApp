package com.example.maindelivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThanhToan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thanh_toan);


        Button nextButton = findViewById(R.id.next);
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(ThanhToan.this, XacNhanThanhToan.class);

            // Get selected payment method
            RadioGroup paymentMethodGroup = findViewById(R.id.payment_method_group);
            int selectedId = paymentMethodGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedId);
            String paymentMethod = selectedRadioButton.getText().toString();

            // Get driver name from previous Intent
            String driverName = getIntent().getStringExtra("driverName");

            // Get addresses from previous Intent
            String deliveryAddress = getIntent().getStringExtra("deliveryAddress");
            String pickupAddress = getIntent().getStringExtra("pickupAddress");

            // Put data into Intent
            intent.putExtra("paymentMethod", paymentMethod);
            intent.putExtra("driverName", driverName);
            intent.putExtra("addressReceive", pickupAddress);
            intent.putExtra("addressDelivery", deliveryAddress);

            startActivity(intent);
        });
        Button backButton = findViewById(R.id.back);
        backButton.setOnClickListener(v1 -> {
            Intent intent1 = new Intent(ThanhToan.this, ChonTaiXe.class);
            startActivity(intent1);
        });
    }
}