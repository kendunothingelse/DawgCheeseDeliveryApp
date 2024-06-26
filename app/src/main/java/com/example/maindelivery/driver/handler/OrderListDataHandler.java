package com.example.maindelivery.driver.handler;

import android.content.Context;
import android.widget.Toast;

import com.example.maindelivery.driver.TX_OrderList;
import com.example.maindelivery.order.ConfirmedOrderAttributes;
import com.example.maindelivery.order.adapter.ConfirmedOrderAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.List;

public class OrderListDataHandler {
    private FirebaseFirestore db;
    private String username;
    private List<ConfirmedOrderAttributes> orderList;

    public OrderListDataHandler(FirebaseFirestore db, String username, List<ConfirmedOrderAttributes> orderList) {
        this.db = db;
        this.username = username;
        this.orderList = orderList;
    }

    public void displayOrderList(ConfirmedOrderAdapter orderAdapter, Context context) {
        db.collection("confirm_orders").whereEqualTo("driverName", username).whereEqualTo("orderStatus", "Chưa giao")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            ConfirmedOrderAttributes order = document.toObject(ConfirmedOrderAttributes.class);
                            order.setId(document.getId()); // Gán giá trị cho trường id
                            orderList.add(order);
                        }
                        orderAdapter.notifyDataSetChanged();
                    } else {
                        // Handle error
                        Toast.makeText(context, "Lỗi khi lấy dữ liệu", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}