package com.yauhe.online_cosmetics_store.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderItem extends AbstractEntity {

    private int count;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Override
    public String toString() {
        return "OrderItem{" +
                "count=" + count +
                ", item=" + item +
                ", order=" + order +
                '}';
    }

}
