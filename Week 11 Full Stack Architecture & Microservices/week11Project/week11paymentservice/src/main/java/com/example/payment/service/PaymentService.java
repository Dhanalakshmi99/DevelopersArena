package com.example.payment.service;

import com.example.common.event.inventory.InventoryReservedEvent;
import com.example.payment.model.Payment;

import java.util.UUID;

public interface PaymentService {
    void processPayment(InventoryReservedEvent event);
    Payment getPaymentByOrderId(UUID orderId);
}