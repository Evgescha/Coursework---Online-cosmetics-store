package com.yauhe.online_cosmetics_store.repository;

import com.yauhe.online_cosmetics_store.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType,
        Long> {
    ItemType findByNameIgnoreCase(String name);
}
