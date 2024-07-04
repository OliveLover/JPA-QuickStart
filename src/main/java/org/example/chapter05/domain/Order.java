package org.example.chapter05.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString(exclude = "itemList")
@Entity
@Table(name = "S_ORD")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // 주문 아이디

    @Column(name = "CUSTOMER_ID")
    private Long customerId;    // 고객 아이디

    @Column(name = "ORDER_DATE")
    private Date orderDate;     // 주문 날짜

    private Double total;       // 주문 금액

    @OneToMany(mappedBy = "order")
    private List<Item> itemList = new ArrayList<>();
}
