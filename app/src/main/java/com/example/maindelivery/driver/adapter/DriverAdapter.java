package com.example.maindelivery.driver.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maindelivery.order.ChonTaiXe;
import com.example.maindelivery.R;
import com.example.maindelivery.order.ThanhToan;

import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {
    private List<String> driverList;
    private Context context;

    public DriverAdapter(List<String> driverList, Context context) {
        this.driverList = driverList;
        this.context = context;
    }

    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_card, parent, false);
        return new DriverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
        String driverName = driverList.get(position);
        holder.driverName.setText(driverName);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ThanhToan.class);
            // Get addresses from previous Intent
            String deliveryAddress = ((ChonTaiXe) context).getIntent().getStringExtra("deliveryAddress");
            String pickupAddress = ((ChonTaiXe) context).getIntent().getStringExtra("pickupAddress");

            // Put data into Intent
            intent.putExtra("driverName", driverName);
            intent.putExtra("deliveryAddress", deliveryAddress);
            intent.putExtra("pickupAddress", pickupAddress);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }

    public static class DriverViewHolder extends RecyclerView.ViewHolder {
        public TextView driverName;

        public DriverViewHolder(View view) {
            super(view);
            driverName = view.findViewById(R.id.driverName);
        }
    }
}