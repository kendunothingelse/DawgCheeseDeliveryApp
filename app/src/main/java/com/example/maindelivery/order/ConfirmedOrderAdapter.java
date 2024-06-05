package com.example.maindelivery.order;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maindelivery.R;
import com.example.maindelivery.order.ConfirmedOrderAttributes;

import java.util.List;

public class ConfirmedOrderAdapter extends RecyclerView.Adapter<ConfirmedOrderAdapter.ConfirmedOrderViewHolder> {
    private List<ConfirmedOrderAttributes> orders;
    private int selectedPosition = -1; // Khởi tạo vị trí được chọn là -1, tức là không có thẻ nào được chọn

    public ConfirmedOrderAdapter(List<ConfirmedOrderAttributes> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public ConfirmedOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.confirmed_order_card, parent, false);
        return new ConfirmedOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmedOrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ConfirmedOrderAttributes order = orders.get(position);
        holder.paymentMethod.setText(order.getPaymentMethod());
        holder.driverName.setText(order.getDriverName());
        holder.addressReceive.setText(order.getAddressReceive());
        holder.addressDelivery.setText(order.getAddressDelivery());
        holder.orderStatus.setText(order.getOrderStatus());

        holder.itemView.setOnClickListener(v -> {
            notifyItemChanged(selectedPosition); // Cập nhật lại thẻ cũ
            selectedPosition = position; // Cập nhật vị trí mới
            notifyItemChanged(selectedPosition); // Cập nhật thẻ mới
        });

        // Thay đổi background của thẻ dựa vào vị trí được chọn
        if (position == selectedPosition) {
            holder.itemView.setBackgroundResource(R.drawable.card_border_selected);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.card_border);
        }

        holder.itemView.setOnClickListener(v -> {
            notifyItemChanged(selectedPosition);
            selectedPosition = position;
            notifyItemChanged(selectedPosition);

            if (listener != null) {
                listener.onItemClick(orders.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


    public interface OnItemClickListener {
        void onItemClick(ConfirmedOrderAttributes order);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ConfirmedOrderViewHolder extends RecyclerView.ViewHolder {
        public TextView paymentMethod;
        public TextView driverName;
        public TextView addressReceive;
        public TextView addressDelivery;
        public TextView orderStatus;

        public ConfirmedOrderViewHolder(View view) {
            super(view);
            paymentMethod = view.findViewById(R.id.paymentMethod);
            driverName = view.findViewById(R.id.driverName);
            addressReceive = view.findViewById(R.id.addressReceive);
            addressDelivery = view.findViewById(R.id.addressDelivery);
            orderStatus = view.findViewById(R.id.orderStatus);
        }
    }
}