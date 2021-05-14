package com.yauhe.online_cosmetics_store.controller;

import com.yauhe.online_cosmetics_store.entity.Item;
import com.yauhe.online_cosmetics_store.entity.Order;
import com.yauhe.online_cosmetics_store.entity.OrderItem;
import com.yauhe.online_cosmetics_store.entity.User;
import com.yauhe.online_cosmetics_store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/backet")
public class BacketController {

    @Autowired
    ItemTypeService serviceItemType;

    @Autowired
    ItemService serviceItem;

    @Autowired
    UserServiceImpl serviceUser;

    @Autowired
    OrderService serviceOrder;

    @Autowired
    OrderItemService serviceOrderItem;

    @Autowired
    StatusService serviceStatus;


    @RequestMapping("/addToBacket")
    String getBooking(Model model, @Param("id") Long id,
                      @Param("count") Long count, Principal principal) {
        User user = serviceUser.findByUsername(principal.getName());
        Item item = serviceItem.read(id);

        OrderItem oi = new OrderItem();
        oi.setCount(count.intValue());
        oi.setItem(item);
        System.out.println("oi: " + oi);

        addOrderItemToBacket(user, oi);


        return "redirect:/user/history";
    }

    @RequestMapping("/removeFromBacket/{id}")
    String removeFromBacket(Model model, @PathVariable("id") Long id,
                            Principal principal) throws Exception {
        serviceOrderItem.delete(id);

        return "redirect:/user/history";
    }

    private void addOrderItemToBacket(User user, OrderItem oi) {
        Order order = null;
        for (Order o : user.getMyOrders()) {
            if (o.getStatus().getId() == 1) {
                order = o;
                break;
            }
        }
        System.out.println("order pri sakace: " + order);
        if (order == null) {
            order = new Order();
            order.setStatus(serviceStatus.read(1));
            order.setCreator(user);
            order.setDates(new Date(System.currentTimeMillis()));
            order.setTimes(new Time(System.currentTimeMillis()));
            serviceOrder.create(order);
            user.getMyOrders().add(order);
            serviceUser.update(user);
            serviceUser.update(user);
            addOrderItemToBacket(serviceUser.read(user.getId()), oi);
        } else {
            order.getOrderItems().add(oi);
            oi.setOrder(order);
            serviceOrderItem.create(oi);
            serviceOrder.update(order);
        }
    }

}
