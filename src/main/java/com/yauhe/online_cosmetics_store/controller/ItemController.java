package com.yauhe.online_cosmetics_store.controller;


import com.yauhe.online_cosmetics_store.entity.Item;
import com.yauhe.online_cosmetics_store.entity.ItemType;
import com.yauhe.online_cosmetics_store.service.ItemBrandService;
import com.yauhe.online_cosmetics_store.service.ItemService;
import com.yauhe.online_cosmetics_store.service.ItemTypeService;
import com.yauhe.online_cosmetics_store.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService service;

    @Autowired
    ItemTypeService itemTypeService;

    @Autowired
    ItemBrandService itemBrandService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    String getService(Model model) {
        model.addAttribute("list", service.repository.findAll());
        model.addAttribute("types", itemTypeService.repository.findAll());
        model.addAttribute("brands", itemBrandService.repository.findAll());
        return "item-list";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/item";
    }

    @RequestMapping(path = "/id/{id}")
    public String description(Model model, @PathVariable("id") Long id,
                              Principal principal) {
        Item item = service.read(id);
        model.addAttribute("entity", item);
        return "itemDescription";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id, Principal principal) {


        if (id != null) {
            Item entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new Item());
        }
        model.addAttribute("types", itemTypeService.repository.findAll());
        model.addAttribute("brands", itemBrandService.repository.findAll());
        return "item-add-edit";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Item entity, @Param("catId") Long catId,
                                 @Param("brandId") Long brandId) {

        entity.setType(itemTypeService.read(catId));
        entity.setBrand(itemBrandService.read(brandId));
        service.create(entity);
        return "redirect:/item";
    }


}
