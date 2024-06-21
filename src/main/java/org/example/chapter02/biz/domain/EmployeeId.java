package org.example.chapter02.biz.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class EmployeeId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String mailId;
}
