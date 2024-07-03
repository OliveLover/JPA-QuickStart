package org.example.chapter05.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "S_EMP")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false)
    private String name;

    @OneToOne(optional = false , fetch = FetchType.LAZY)
    @JoinColumn(name = "EMP_CARD_ID")
    private EmployeeCard card;

    public void setEmployeeCard(EmployeeCard card) {
        this.card = card;
        card.setEmployee(this);
    }
}
