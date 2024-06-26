package com.example.maindelivery.driver;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maindelivery.R;
import com.example.maindelivery.driver.handler.OrderListDataHandler;
import com.example.maindelivery.order.adapter.ConfirmedOrderAdapter;
import com.example.maindelivery.order.ConfirmedOrderAttributes;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class TX_OrderList extends AppCompatActivity {
    // ...
    private RecyclerView orderListRecyclerView;
    private List<ConfirmedOrderAttributes> orderList;
    private ConfirmedOrderAdapter orderAdapter;
    TextView usernameTextView;
    String username;
    private ConfirmedOrderAttributes selectedOrder;

    private Button backButton, acceptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tx_order_list);

        orderListRecyclerView = findViewById(R.id.orderListRecyclerView);
        orderListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderList = new ArrayList<>();
        orderAdapter = new ConfirmedOrderAdapter(orderList);
        orderListRecyclerView.setAdapter(orderAdapter);

        username = getIntent().getStringExtra("username");

        OrderListDataHandler orderListDataHandler = new OrderListDataHandler(FirebaseFirestore.getInstance(), username, orderList);
        orderListDataHandler.displayOrderList(orderAdapter, TX_OrderList.this);


        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainTaiXe.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

// Trong phương thức onCreate
        acceptButton = findViewById(R.id.acceptButton);
        acceptButton.setEnabled(false); // Mặc định nút không hoạt động
// Khi một thẻ được chọn trong RecyclerView
// Khi một thẻ được chọn trong RecyclerView
        orderAdapter.setOnItemClickListener(new ConfirmedOrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ConfirmedOrderAttributes order) {
                selectedOrder = order;
                acceptButton.setEnabled(true);
                acceptButton.setBackgroundColor(Color.parseColor("#800080")); // Màu tím
            }
        });
        // Khi nút acceptButton được nhấp
        acceptButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(TX_OrderList.this);
            builder.setTitle("Kiểm tra lại thông tin");


            // Tạo một TextView để hiển thị thông tin thẻ đã chọn
            TextView infoTextView = new TextView(TX_OrderList.this);
            infoTextView.setText(selectedOrder.toString()); // Giả sử ConfirmedOrderAttributes có phương thức toString để hiển thị thông tin

            builder.setView(infoTextView);

            // Thêm nút "Hủy"
            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });

            // Thêm nút "Xác nhận"
            builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Xử lý khi nút "Xác nhận" được nhấp
                    //Đưa selectedOrder vào trong Intent
                    Intent intent = new Intent(TX_OrderList.this, TX_HoanThanhNhanDon.class);
                    intent.putExtra("selectedOrder", selectedOrder);
                    startActivity(intent);
                }
            });

            // Tạo và hiển thị AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

}