package com.example.maindelivery.driver;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.maindelivery.order.DS_DonDaGiao;
import com.example.maindelivery.R;
import com.example.maindelivery.order.ConfirmedOrderAttributes;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.maindelivery.driver.MapDataHandler;
public class TX_Map extends AppCompatActivity {
    String username;
    private FirebaseFirestore db;
    private MapDataHandler mapDataHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tx_map);

        ConfirmedOrderAttributes order = (ConfirmedOrderAttributes) getIntent().getSerializableExtra("selectedOrder");
        db = FirebaseFirestore.getInstance();
        String username = getIntent().getStringExtra("username");

        // Khởi tạo các nút và thiết lập sự kiện click cho chúng
        // Nút "Trang chủ"
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            // Chuyển về trang chủ
            Intent intent = new Intent(TX_Map.this, MainTaiXe.class);
            intent.putExtra("username", username);
            if (order != null) {
                intent.putExtra("selectedOrder", order);
            }
            startActivity(intent);
        });
        // Xác nhận giao hàng thành công
        Button confirmDeliveryButton = findViewById(R.id.confirmDeliveryButton);
        confirmDeliveryButton.setOnClickListener(v -> {
            // Hiện popup xác nhận giao hàng thành công
            AlertDialog.Builder builder = new AlertDialog.Builder(TX_Map.this);
            builder.setTitle("Xác nhận giao hàng thành công?");

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
                    if (order != null) {
                        order.setOrderStatus("đã giao thành công");
                        db.collection("confirm_orders").document(order.getId())
                                .set(order)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(TX_Map.this, "Giao hàng thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(TX_Map.this, MainTaiXe.class);
                                    intent.putExtra("username", username);
                                    startActivity(intent);
                                });
                    }
                    Intent intent = new Intent(TX_Map.this, DS_DonDaGiao.class);
                    startActivity(intent);
                }
            });

            // Tạo và hiển thị AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();
        });


        // Hiển thị thông tin địa chỉ nhận và giao hàng
        TextView addressReceive = findViewById(R.id.pickupPointTextView);
        TextView addressDelivery = findViewById(R.id.deliveryPointTextView);
        if (order != null) {
            addressReceive.setText(order.getAddressReceive());
            addressDelivery.setText(order.getAddressDelivery());
        }
        // Khởi tạo nút "Xem địa chỉ nhận" và thiết lập sự kiện click
        Button viewPickupLocationButton = findViewById(R.id.viewPickupLocationButton);
        viewPickupLocationButton.setOnClickListener(v -> {
            if (order != null) {
                openLocationInGoogleMaps(order.getAddressReceive());
            }
        });

        // Khởi tạo nút "Xem địa chỉ giao" và thiết lập sự kiện click
        Button viewDeliveryLocationButton = findViewById(R.id.viewDeliveryLocationButton);
        viewDeliveryLocationButton.setOnClickListener(v -> {
            if (order != null) {
                openLocationInGoogleMaps(order.getAddressDelivery());
            }
        });

    }

    private void openLocationInGoogleMaps(String location) {
        try {
            Uri uri = Uri.parse("https://www.google.co.in/maps/search/" + Uri.encode(location));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}