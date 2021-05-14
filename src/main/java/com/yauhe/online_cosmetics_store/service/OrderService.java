package com.yauhe.online_cosmetics_store.service;

import com.yauhe.online_cosmetics_store.entity.Order;
import com.yauhe.online_cosmetics_store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends CrudImpl<Order> {

    public OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
