package com.example.order.service;

import com.example.order.dto.OrderRequest;
importcom.example.order.dto.OrderResponse;
import com.example.order.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse getOrderById(UUID orderId);
    List<OrderResponse> getAllOrders();
    List<OrderResponse> getOrdersByCustomerId(UUID customerId);
    void updateOrderStatus(UUID orderId, Order.OrderStatus status);
}