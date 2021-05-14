package com.yauhe.online_cosmetics_store.repository;

import com.yauhe.online_cosmetics_store.entity.Item;
import com.yauhe.online_cosmetics_store.entity.Order;
import com.yauhe.online_cosmetics_store.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);

    List<Order> findByOrderAndItem(Order order, Item inventory);
}
