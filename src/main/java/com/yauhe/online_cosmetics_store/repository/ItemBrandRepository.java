package com.yauhe.online_cosmetics_store.repository;

import com.yauhe.online_cosmetics_store.entity.ItemBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemBrandRepository extends JpaRepository<ItemBrand,
        Long> {
    ItemBrand findByNameIgnoreCase(String name);
}
