package org.example.chapter05.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString(exclude = "employee")
//@Entity
//@Table(name = "S_EMP_CARD")
public class EmployeeCard {

    @Id
    @Column(name = "CARD_ID")
    private Long cardId;    // 사원증 아이디

    @Column(name = "EXPIRE_DATE")
    private Date expireDate;    // 사원증 만료 기간

    private String role;   // 권한

    @MapsId
    @OneToOne
    @JoinColumn(name = "EMP_ID")
    private Employee employee;
}
