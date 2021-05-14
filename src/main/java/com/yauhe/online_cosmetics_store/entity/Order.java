package com.yauhe.online_cosmetics_store.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "myOrders")
@Data
public class Order extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    private Date dates;

    private Time times;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Override
    public String toString() {
        return "Order{" +
                "creator=" + creator.getFio() +
                ", dates=" + dates +
                ", times=" + times +
                ", status=" + status +
                '}';
    }
}
