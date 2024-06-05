package com.example.maindelivery.driver;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maindelivery.ConfirmedOrders;
import com.example.maindelivery.R;
import com.example.maindelivery.order.ConfirmedOrderAttributes;

public class TXOrderAdapter extends  RecyclerView.Adapter<TXOrderAdapter.OrderViewHolder>{

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView detailTextView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(ConfirmedOrderAttributes order) {
            // Update this method to use the appropriate fields from ConfirmedOrderAttributes
            detailTextView.setText(order.getPaymentMethod()); // for example
        }
    }
}
