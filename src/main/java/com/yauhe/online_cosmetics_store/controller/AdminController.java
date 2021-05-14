package com.yauhe.online_cosmetics_store.controller;

import com.yauhe.online_cosmetics_store.entity.Order;
import com.yauhe.online_cosmetics_store.entity.OrderItem;
import com.yauhe.online_cosmetics_store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.sql.Time;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

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


    @GetMapping
    @RequestMapping("/booking")
    String getBooking(Model model) {
        List<Order> all = serviceOrder.repository.findAll();
        Collections.reverse(all);
        model.addAttribute("list", all);
        return "booking";
    }


    @GetMapping
    @RequestMapping("/deleteOrderUser/{id}")
    String deleteOrderUser(Model model, @PathVariable("id") Long id) throws Exception {
        deleteOrder(id);
        return "redirect:/user/history";
    }

    @GetMapping
    @RequestMapping("/deleteOrderAdmin/{id}")
    String deleteOrderAdmin(Model model, @PathVariable("id") Long id) throws Exception {
        deleteOrder(id);
        return "redirect:/admin/booking";
    }

    private void deleteOrder(@PathVariable("id") Long id) throws Exception {
        Order order = serviceOrder.read(id);
        for (OrderItem orderList : order.getOrderItems()) {
            serviceOrderItem.delete(orderList.getId());
        }
        serviceOrder.delete(id);
    }

    @GetMapping
    @RequestMapping("/aproveOrderUser/{id}")
    String aproveOrderUser(Model model, @PathVariable("id") Long id) {
        Order order = serviceOrder.read(id);
        order.setStatus(serviceStatus.read(2));
        order.setDates(new Date(System.currentTimeMillis()));
        order.setTimes(new Time(System.currentTimeMillis()));
        serviceOrder.update(order);
        return "redirect:/user/history";
    }

    @GetMapping
    @RequestMapping("/aproveOrderAdmin/{id}")
    String aproveOrderAdmin(Model model, @PathVariable("id") Long id) {
        Order order = serviceOrder.read(id);
        order.setStatus(serviceStatus.read(3));
        serviceOrder.update(order);
        return "redirect:/admin/booking";
    }
}
