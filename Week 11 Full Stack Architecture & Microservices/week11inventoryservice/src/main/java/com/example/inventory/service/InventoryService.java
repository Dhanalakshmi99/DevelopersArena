package com.example.inventory.service;

import com.example.common.event.order.OrderCreatedEvent;

import java.util.UUID;

public interface InventoryService {
    void reserveInventory(OrderCreatedEvent orderCreatedEvent);
    void confirmReservation(UUID orderId);
    void cancelReservation(UUID orderId);
}