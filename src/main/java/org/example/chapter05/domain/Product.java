package org.example.chapter05.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
//@Entity
//@Table(name = "S_PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // 상품 아이디

    private String name;    // 상품 이름

    @Column(name = "SHORT_DESC")
    private String shortDesc;   // 상품 설명

    private String category;    // 카테고리
}
