package com.example.maindelivery.order.handler;

import android.content.Context;
import android.content.Intent;

import com.example.maindelivery.order.DatHangThanhCong;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PaymentConfirmationHandler {
    private Context context;

    public PaymentConfirmationHandler(Context context) {
        this.context = context;
    }

    public void confirmPayment(String paymentMethod, String driverName, String addressReceive, String addressDelivery) {
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
        Intent intent = new Intent(context, DatHangThanhCong.class);
        context.startActivity(intent);
    }
}