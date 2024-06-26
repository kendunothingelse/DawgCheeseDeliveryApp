package com.example.maindelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.maindelivery.order.MainOrder;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormDatHang extends AppCompatActivity {
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_dat_hang);

        db = FirebaseFirestore.getInstance();

        Button orderButton = findViewById(R.id.orderButton);
        EditText yourName = findViewById(R.id.yourName);
        EditText receiverName = findViewById(R.id.receiverName);
        EditText pickupAddress = findViewById(R.id.pickupAddress);
        EditText deliveryAddress = findViewById(R.id.deliveryAddress);
        Button listButton = findViewById(R.id.listButton);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> order = new HashMap<>();
                order.put("yourName", yourName.getText().toString());
                order.put("receiverName", receiverName.getText().toString());
                order.put("pickupAddress", pickupAddress.getText().toString());
                order.put("deliveryAddress", deliveryAddress.getText().toString());

                db.collection("orders")
                        .add(order)
                        .addOnSuccessListener(documentReference -> Toast.makeText(FormDatHang.this, "Đã nhập đơn hàng thành công", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(FormDatHang.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FormDatHang.this, MainOrder.class));
            }
        });
    }
}