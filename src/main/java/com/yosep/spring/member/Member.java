package com.yosep.spring.member;

import com.yosep.spring.common.Address;
import com.yosep.spring.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany
    private List<Order> orders = new ArrayList<>();
}
