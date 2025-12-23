package com.ecommerce.repository;

import com.ecommerce.model.entity.Order;
import com.ecommerce.model.entity.User;
import com.ecommerce.model.enums.OrderStatus;
import com.ecommerce.model.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // 1️⃣ Find orders by user with pagination
    Page<Order> findByUser(User user, Pageable pageable);

    // 2️⃣ Find orders by status
    List<Order> findByStatus(OrderStatus status);

    // 3️⃣ Find orders within a date range
    @Query("SELECT o FROM Order o WHERE o.createdAt BETWEEN :startDate AND :endDate")
    List<Order> findOrdersBetweenDates(@Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate);

    // 4️⃣ Find order by order number and fetch user eagerly
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.user WHERE o.orderNumber = :orderNumber")
    Optional<Order> findByOrderNumberWithUser(@Param("orderNumber") String orderNumber);

    // 5️⃣ Custom JPQL query to get order summaries as DTOs
    @Query("SELECT new com.ecommerce.model.dto.OrderDTO(o.id, o.orderNumber, o.totalAmount, o.status, o.createdAt) " +
           "FROM Order o WHERE o.user.id = :userId")
    List<OrderDTO> findOrderSummariesByUserId(@Param("userId") Long userId);

    // 6️⃣ Native query for daily order report
    @Query(value = """
        SELECT CAST(o.created_at AS DATE) as order_date,
               COUNT(*) as total_orders,
               SUM(o.total_amount) as total_revenue
        FROM orders o
        WHERE o.created_at >= :startDate
        GROUP BY CAST(o.created_at AS DATE)
        ORDER BY order_date DESC
        """, nativeQuery = true)
    List<Object[]> getDailyOrderReport(@Param("startDate") LocalDateTime startDate);
}
