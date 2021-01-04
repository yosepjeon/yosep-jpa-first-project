package com.yosep.spring.product.repository;

import com.yosep.spring.product.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<Item, Long> {
}
