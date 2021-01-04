package com.yosep.spring.product.repository;

import com.yosep.spring.product.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {
    private final ItemRepositorySupport itemRepositorySupport;

    private final EntityManager em;

    @Autowired
    public ItemRepository(ItemRepositorySupport itemRepositorySupport, EntityManager em) {
        this.itemRepositorySupport = itemRepositorySupport;
        this.em = em;
    }

    public void save(Item item) {
        if(item.getId() == null) {
            em.persist(item);
        }else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return itemRepositorySupport.findAll();
    }
}
