package com.example.maindelivery.order.adapter.listener;

import com.example.maindelivery.order.ConfirmedOrderAttributes;
import com.example.maindelivery.order.adapter.ConfirmedOrderAdapter;

public class ConfirmedOrderClickListener {
    private ConfirmedOrderAdapter.OnItemClickListener listener;

    public ConfirmedOrderClickListener(ConfirmedOrderAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void onItemClick(int position, ConfirmedOrderAttributes order) {
        if (listener != null) {
            listener.onItemClick(order);
        }
    }
}
