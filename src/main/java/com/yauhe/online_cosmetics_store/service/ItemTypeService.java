package com.yauhe.online_cosmetics_store.service;

import com.yauhe.online_cosmetics_store.entity.ItemType;
import com.yauhe.online_cosmetics_store.repository.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemTypeService extends CrudImpl<ItemType> {

    public ItemTypeRepository repository;

    @Autowired
    public ItemTypeService(ItemTypeRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public ItemType findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

}
