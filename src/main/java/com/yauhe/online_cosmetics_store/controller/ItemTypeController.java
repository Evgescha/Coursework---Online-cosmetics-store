package com.yauhe.online_cosmetics_store.controller;

import java.util.List;

import com.yauhe.online_cosmetics_store.entity.ItemType;
import com.yauhe.online_cosmetics_store.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/itemType")
public class ItemTypeController {

    @Autowired
    ItemTypeService service;

    @GetMapping
    String getCategory(Model model) {
        List<ItemType> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "type-list";
    }

    @RequestMapping("/items/{id}")
    public String items(Model model, @PathVariable("id") Long id) {
        model.addAttribute("list",service.read(id).getItems());
        return "item-list";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {
        if (id != null) {
            ItemType entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new ItemType());
        }
        return "type-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/itemType";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(ItemType entity) {
        service.create(entity);
        return "redirect:/itemType";
    }
}
