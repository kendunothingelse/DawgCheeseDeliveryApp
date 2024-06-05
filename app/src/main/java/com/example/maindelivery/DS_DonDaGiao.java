package com.example.maindelivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maindelivery.order.ConfirmedOrderAdapter;
import com.example.maindelivery.order.ConfirmedOrderAttributes;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class DS_DonDaGiao extends AppCompatActivity {

    private FirebaseFirestore db;
    private List<ConfirmedOrderAttributes> orderList;
    private ConfirmedOrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_giao_hang_thanh_cong);

        db = FirebaseFirestore.getInstance();
        orderList = new ArrayList<>();
        orderAdapter = new ConfirmedOrderAdapter(orderList);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderAdapter);

        db.collection("confirm_orders").whereEqualTo("orderStatus", "đã giao thành công")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            ConfirmedOrderAttributes order = document.toObject(ConfirmedOrderAttributes.class);
                            order.setId(document.getId());
                            orderList.add(order);
                        }
                        orderAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(DS_DonDaGiao.this, "Lỗi khi lấy dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                });

        Button btnBack = findViewById(R.id.backButton);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(DS_DonDaGiao.this, MainActivity.class);
            startActivity(intent);
        });
    }
}