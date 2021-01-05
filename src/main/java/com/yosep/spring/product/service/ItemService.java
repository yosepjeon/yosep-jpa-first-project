package com.yosep.spring.product.service;

import com.yosep.spring.product.entity.Item;
import com.yosep.spring.product.repository.ItemJpaRepository;
import com.yosep.spring.product.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {
    private final ItemJpaRepository itemRepository;

    public ItemService(ItemJpaRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findById(itemId).get();
    }
}
