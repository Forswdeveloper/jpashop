package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.comn.BaseEntity;

@Entity
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;
}
