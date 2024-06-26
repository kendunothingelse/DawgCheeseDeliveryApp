package com.example.maindelivery.driver;

import android.content.Context;
import android.widget.Toast;

import com.example.maindelivery.order.ConfirmedOrderAttributes;
import com.google.firebase.firestore.FirebaseFirestore;

public class MapDataHandler {
    private FirebaseFirestore db;
    private String username;
    private ConfirmedOrderAttributes order;

    public MapDataHandler(FirebaseFirestore db, String username, ConfirmedOrderAttributes order) {
        this.db = db;
        this.username = username;
        this.order = order;
    }

    public void confirmDelivery(Context context) {
        if (order != null) {
            order.setOrderStatus("đã giao thành công");
            db.collection("confirm_orders").document(order.getId())
                    .set(order)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(context, "Giao hàng thành công", Toast.LENGTH_SHORT).show();
                    });
        }
    }
}