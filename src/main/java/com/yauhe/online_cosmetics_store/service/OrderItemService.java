package com.yauhe.online_cosmetics_store.service;

import com.yauhe.online_cosmetics_store.entity.OrderItem;
import com.yauhe.online_cosmetics_store.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService extends CrudImpl<OrderItem> {

    public OrderItemRepository repository;

    @Autowired
    public OrderItemService(OrderItemRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
