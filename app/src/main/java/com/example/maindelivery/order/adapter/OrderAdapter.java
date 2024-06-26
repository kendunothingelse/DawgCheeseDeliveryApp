package com.example.maindelivery.order.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maindelivery.R;
import com.example.maindelivery.order.Order;
import com.example.maindelivery.order.adapter.listener.OrderClickListener;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Order> orderList;
    private OrderClickListener clickListener;

    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    public OrderAdapter(List<Order> orderList, OrderClickListener clickListener) {
        this.orderList = orderList;
        this.clickListener = clickListener;
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

        holder.itemView.setOnClickListener(v -> {
            clickListener.onItemClick(position, orderList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Order order);
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView yourName;
        public TextView receiverName;

        public OrderViewHolder(View view) {
            super(view);
            yourName = view.findViewById(R.id.yourName);
            receiverName = view.findViewById(R.id.receiverName);
        }
    }
}