package com.example.maindelivery.order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maindelivery.R;
import com.example.maindelivery.order.adapter.ConfirmedOrderAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ConfirmedOrders extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ConfirmedOrderAdapter adapter;
    private List<ConfirmedOrderAttributes> orders;
    FirebaseFirestore db;
    Button back_to_main_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_orders);
        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recycler_view);
        orders = new ArrayList<>();
        loadOrders();
        adapter = new ConfirmedOrderAdapter(orders);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        back_to_main_button = findViewById(R.id.back_to_main_button);
        back_to_main_button.setOnClickListener(v -> {
            Intent intent = new Intent(ConfirmedOrders.this, MainOrder.class);
            startActivity(intent);
        });

    }

    private void loadOrders() {
        db.collection("confirm_orders").whereEqualTo("orderStatus", "Chưa giao")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            ConfirmedOrderAttributes order = document.toObject(ConfirmedOrderAttributes.class);
                            orders.add(order);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}