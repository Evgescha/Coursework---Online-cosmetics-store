package com.yauhe.online_cosmetics_store.service;

import com.yauhe.online_cosmetics_store.entity.Item;
import com.yauhe.online_cosmetics_store.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends CrudImpl<Item> {

    public ItemRepository repository;

    @Autowired
    public ItemService(ItemRepository repository) {
        super(repository);
        this.repository = repository;
    }


}
