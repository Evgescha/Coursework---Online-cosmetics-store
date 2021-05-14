package com.yauhe.online_cosmetics_store.controller;

import com.yauhe.online_cosmetics_store.entity.ItemBrand;
import com.yauhe.online_cosmetics_store.service.ItemBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/itemBrand")
public class ItemBrandController {

    @Autowired
    ItemBrandService service;

    @GetMapping
    String getCategory(Model model) {
        List<ItemBrand> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "brand-list";
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
            ItemBrand entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new ItemBrand());
        }
        return "brand-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/itemBrand";
    }


    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(ItemBrand entity) {
        service.create(entity);
        return "redirect:/itemBrand";
    }
}
