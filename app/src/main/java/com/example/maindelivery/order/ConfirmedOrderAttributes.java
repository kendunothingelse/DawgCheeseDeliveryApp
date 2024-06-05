package com.example.maindelivery.order;

import java.io.Serializable;

public class ConfirmedOrderAttributes implements Serializable {
    private String paymentMethod;
    private String driverName;
    private String addressReceive;
    private String addressDelivery;
    private String orderStatus;
    private String id; // Thêm trường id

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getAddressReceive() {
        return addressReceive;
    }

    public void setAddressReceive(String addressReceive) {
        this.addressReceive = addressReceive;
    }

    public String getAddressDelivery() {
        return addressDelivery;
    }

    public void setAddressDelivery(String addressDelivery) {
        this.addressDelivery = addressDelivery;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return
                "PHƯƠNG THỨC THANH TOÁN='" + paymentMethod + '\'' +
                ", TÀI XẾ='" + driverName + '\'' +
                ", ĐỊA CHỈ NHẬN='" + addressReceive + '\'' +
                ", ĐỊA CHỈ GIAO='" + addressDelivery + '\'' +
                ", TRẠNG THÁI='" + orderStatus + '\'';
    }
}
