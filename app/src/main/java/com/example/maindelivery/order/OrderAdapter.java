package com.example.maindelivery.order;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.maindelivery.ChonTaiXe;
import com.example.maindelivery.R;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Order> orderList;

    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_card, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.yourName.setText(order.getYourName());
        holder.receiverName.setText(order.getReceiverName());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView yourName;
        public TextView receiverName;

        public OrderViewHolder(View view) {
            super(view);
            yourName = view.findViewById(R.id.yourName);
            receiverName = view.findViewById(R.id.receiverName);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ChonTaiXe.class);

                    // Get the clicked order
                    int position = getAdapterPosition();
                    Order order = orderList.get(position);

                    // Put addresses into Intent
                    intent.putExtra("deliveryAddress", order.getDeliveryAddress());
                    intent.putExtra("pickupAddress", order.getPickupAddress());

                    context.startActivity(intent);
                }
            });
        }
    }
}