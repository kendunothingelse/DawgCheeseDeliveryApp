package com.example.maindelivery.order.adapter.listener;

import com.example.maindelivery.order.Order;
import com.example.maindelivery.order.adapter.ConfirmedOrderAdapter;
import com.example.maindelivery.order.adapter.OrderAdapter;

public class OrderClickListener {
    private OrderAdapter.OnItemClickListener listener;

    public OrderClickListener(OrderAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void onItemClick(int position, Order order) {
        if (listener != null) {
            listener.onItemClick(order);
        }
    }
}
