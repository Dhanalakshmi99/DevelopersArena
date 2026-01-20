packagecom.example.demo.event.order;

import com.example.demo.dto.OrderItemDto;
import com.example.demo.event.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class OrderCreatedEvent extends BaseEvent {
    private UUID orderId;
    private UUID customerId;
    private List<OrderItemDto> items;
    private BigDecimal totalAmount;

    public OrderCreatedEvent(UUID correlationId, UUID orderId, UUID customerId,
                             List<OrderItemDto> items, BigDecimal totalAmount) {
        super(correlationId);
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
    }
}