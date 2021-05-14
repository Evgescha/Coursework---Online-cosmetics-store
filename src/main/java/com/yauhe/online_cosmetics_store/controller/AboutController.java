package com.yauhe.online_cosmetics_store.controller;

import com.yauhe.online_cosmetics_store.service.ItemBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController {
    @Autowired
    ItemBrandService serviceItemBrand;

    @GetMapping
    String getPage(Model model) {
        model.addAttribute("brands", serviceItemBrand.repository.findAll());
        return "about";
    }
}
