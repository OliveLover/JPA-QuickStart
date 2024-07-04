package org.example.chapter05.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
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

    @ManyToMany
    @JoinTable(name = "S_ITEM"
            , joinColumns = @JoinColumn(name = "ORD_ID")
            , inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
            , uniqueConstraints = @UniqueConstraint(columnNames = {"ORD_ID", "PRODUCT_ID"})
    )
    private List<Product> productList = new ArrayList<>();
}
