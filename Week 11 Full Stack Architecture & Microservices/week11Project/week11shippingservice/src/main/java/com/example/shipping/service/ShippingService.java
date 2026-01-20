package com.example.shipping.service;

import com.example.common.event.payment.PaymentProcessedEvent;
import com.example.shipping.model.Shipment;

import java.util.UUID;

public interface ShippingService {
    void processShipping(PaymentProcessedEvent paymentEvent);
    Shipment getShipmentByOrderId(UUID orderId);
    Shipment getShipmentById(UUID shipmentId);
}