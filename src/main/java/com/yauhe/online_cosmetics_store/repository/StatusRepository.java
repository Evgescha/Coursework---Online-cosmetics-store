package com.yauhe.online_cosmetics_store.repository;

import com.yauhe.online_cosmetics_store.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByName(String name);
}
