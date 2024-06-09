package com.scm.SmartContactManager.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myOrderId;

    private String orderId;

    private String amount;

    private String receipt;

    private String status;

    private String paymentId;

    @ManyToOne
    private User user;
}