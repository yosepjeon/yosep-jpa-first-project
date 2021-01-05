package com.yosep.spring.order.service;

import com.yosep.spring.delivery.entity.Delivery;
import com.yosep.spring.delivery.entity.DeliveryStatus;
import com.yosep.spring.member.entity.Member;
import com.yosep.spring.member.repository.MemberJpaRepository;
import com.yosep.spring.order.entity.Order;
import com.yosep.spring.order.entity.OrderItem;
import com.yosep.spring.order.repository.OrderJpaRepository;
import com.yosep.spring.product.entity.Item;
import com.yosep.spring.product.repository.ItemJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final MemberJpaRepository memberJpaRepository;
    private final OrderJpaRepository orderJpaRepository;
    private final ItemJpaRepository itemJpaRepository;

    public OrderService(MemberJpaRepository memberJpaRepository,
                        OrderJpaRepository orderJpaRepository,
                        ItemJpaRepository itemJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
        this.itemJpaRepository = itemJpaRepository;
    }

    @Transactional(readOnly = false)
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberJpaRepository.findById(memberId).get();
        Item item = itemJpaRepository.findById(itemId).get();

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(),count);

        Order order = Order.createOrder(member,delivery,orderItem);

        orderJpaRepository.save(order);

        return order.getId();
    }

    @Transactional(readOnly = false)
    public void cancelOrder(Long orderId) {
        Order order = orderJpaRepository.findById(orderId).get();

        order.cancelOrder();
    }
}
