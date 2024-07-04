package org.example.chapter05.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "S_ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORD_ID")
    private Order order;        // 주문 참조

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;    // 상품 참조

    private Long price;         // 주문 가격

    private Long quantity;      // 주문 수량

    // 주문(Order)과의 양방향 참조 설정
    public void setOrder(Order order) {
        this.order = order;

        order.getItemList().add(this);
    }


}
