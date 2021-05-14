package com.yauhe.online_cosmetics_store.service;

import com.yauhe.online_cosmetics_store.entity.ItemBrand;
import com.yauhe.online_cosmetics_store.repository.ItemBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemBrandService extends CrudImpl<ItemBrand> {

    public ItemBrandRepository repository;

    @Autowired
    public ItemBrandService(ItemBrandRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public ItemBrand findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

}
