package com.yauhe.online_cosmetics_store.repository;

import com.yauhe.online_cosmetics_store.entity.Order;
import com.yauhe.online_cosmetics_store.entity.Status;
import com.yauhe.online_cosmetics_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCreator(User creator);

    List<Order> findByStatus(Status status);

    Order findByCreatorAndDates(User creator, Date dates);

    Order findByCreatorAndStatus(User creator, Status status);

    Order findByCreatorAndStatusAndDates(User creator, Status status, Date dates);
}
