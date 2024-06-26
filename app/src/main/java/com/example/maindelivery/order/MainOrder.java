package com.example.maindelivery.order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maindelivery.R;

public class MainOrder extends AppCompatActivity {
 private Button daXacNhan, danGiao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add OrderListFragment to MainActivity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new OrderListFragment())
                .commit();

        daXacNhan = (Button) findViewById(R.id.button_map);
        danGiao = (Button) findViewById(R.id.button_confirmed_orders);
        daXacNhan.setOnClickListener(v -> {
            Intent intent = new Intent(MainOrder.this, ConfirmedOrders.class);
            startActivity(intent);
        });
        danGiao.setOnClickListener(v -> {
            Intent intent = new Intent(MainOrder.this, DS_DonDaGiao.class);
            startActivity(intent);
        });
    }
}