packagecom.example.order.saga;

import com.example.common.event.inventory.InventoryReservationFailedEvent;
import com.examplecommon.event.inventory.InventoryReservedEvent;
import com.example.common.event.payment.PaymentFailedEvent;
import com.example.common.event.payment.PaymentProcessedEvent;
import com.example.common.event.shipping.ShipmentFailedEvent;
import com.example.common.event.shipping.ShipmentProcessedEvent;
import com.example.order.model.Order;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderSagaHandler {

    private final OrderService orderService;

    @KafkaListener(topics = "inventory-events", groupId = "order-service-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void handleInventoryEvents(Object event) {
        log.info("Received inventory event: {}", event.getClass().getSimpleName());

        if (event instanceof InventoryReservedEvent reservedEvent) {
            // Inventory was successfully reserved
            orderService.updateOrderStatus(reservedEvent.getOrderId(), Order.OrderStatus.INVENTORY_RESERVED);
            log.info("Inventory reserved for order: {}", reservedEvent.getOrderId());
        } else if (event instanceof InventoryReservationFailedEvent failedEvent) {
            // Inventory reservation failed - cancel order
            orderService.updateOrderStatus(failedEvent.getOrderId(), Order.OrderStatus.CANCELLED);
            log.error("Inventory reservation failed for order: {}, reason: {}",
                    failedEvent.getOrderId(), failedEvent.getReason());
        }
    }

    @KafkaListener(topics = "payment-events", groupId = "order-service-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void handlePaymentEvents(Object event) {
        log.info("Received payment event: {}", event.getClass().getSimpleName());

        if (event instanceof PaymentProcessedEvent processedEvent) {
            // Payment was successfully processed
            orderService.updateOrderStatus(processedEvent.getOrderId(), Order.OrderStatus.PAYMENT_COMPLETED);
            log.info("Payment processed for order: {}", processedEvent.getOrderId());
        } else if (event instanceof PaymentFailedEvent failedEvent) {
            // Payment failed - cancel order
            orderService.updateOrderStatus(failedEvent.getOrderId(), Order.OrderStatus.FAILED);
            log.error("Payment failed for order: {}, reason: {}",
                    failedEvent.getOrderId(), failedEvent.getReason());
        }
    }

    @KafkaListener(topics = "shipping-events", groupId = "order-service-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void handleShippingEvents(Object event) {
        log.info("Received shipping event: {}", event.getClass().getSimpleName());

        if (event instanceof ShipmentProcessedEvent processedEvent) {
            // Shipment was successfully processed
            orderService.updateOrderStatus(processedEvent.getOrderId(), Order.OrderStatus.SHIPPED);
            log.info("Order shipped: {}, tracking number: {}",
                    processedEvent.getOrderId(), processedEvent.getTrackingNumber());
        } else if (event instanceof ShipmentFailedEvent failedEvent) {
            // Shipping failed
            orderService.updateOrderStatus(failedEvent.getOrderId(), Order.OrderStatus.FAILED);
            log.error("Shipping failed for order: {}, reason: {}",
                    failedEvent.getOrderId(), failedEvent.getReason());
        }
    }
}