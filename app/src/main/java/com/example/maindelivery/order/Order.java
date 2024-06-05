package com.example.maindelivery.order;

public class Order {
    private String yourName;
    private String receiverName;
    private String pickupAddress;
    private String deliveryAddress;

    public Order() {
        // Default constructor required for calls to DataSnapshot.getValue(Order.class)
        
    }

    public Order(String yourName, String receiverName, String pickupAddress, String deliveryAddress) {
        this.yourName = yourName;
        this.receiverName = receiverName;
        this.pickupAddress = pickupAddress;
        this.deliveryAddress = deliveryAddress;
    }

    public String getYourName() {
        return yourName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
}