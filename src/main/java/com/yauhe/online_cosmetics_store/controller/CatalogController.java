package com.yauhe.online_cosmetics_store.controller;

import com.yauhe.online_cosmetics_store.service.ItemBrandService;
import com.yauhe.online_cosmetics_store.service.ItemService;
import com.yauhe.online_cosmetics_store.service.ItemTypeService;
import com.yauhe.online_cosmetics_store.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/shop")
public class CatalogController {

    @Autowired
    ItemService service;

    @Autowired
    ItemTypeService itemTypeService;

    @Autowired
    ItemBrandService itemBrandService;

    @Autowired
    UserServiceImpl userService;
    @GetMapping
    String getPage(Model model) {

        model.addAttribute("list", service.repository.findAll());
        addFilters(model);
        return "shop";
    }

    @RequestMapping("/byIdType/{id}")
    public String byIdType(Model model, @PathVariable("id") Long id) {
        model.addAttribute("list", itemTypeService.read(id).getItems());
        addFilters(model);
        return "shop";
    }

    private void addFilters(Model model) {
        model.addAttribute("types", itemTypeService.repository.findAll());
        model.addAttribute("brands", itemBrandService.repository.findAll());
    }

    @RequestMapping("/byIdBrand/{id}")
    public String byIdBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("list", itemBrandService.read(id).getItems());
        addFilters(model);
        return "shop";
    }


    @RequestMapping("/description/{id}")
    String getDescription(Model model, @PathVariable("id") Long id) {
        model.addAttribute("entity", service.read(id));
        model.addAttribute("brands", itemBrandService.repository.findAll());
        return "shop-single";
    }
}
