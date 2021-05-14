package com.yauhe.online_cosmetics_store.repository;

import com.yauhe.online_cosmetics_store.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameIgnoreCase(String login);
}
