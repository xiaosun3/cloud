package com.cloud.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sunhaidi on 2019-03-05.
 */
@Data
@Entity
@Table(name = "index")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "card")
    private String card;

    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    public Customer() {

    }

    public Customer(String name) {
        this.name = name;
        this.createTime = new Date();
    }

    public Customer( String name, String card) {
        this.name = name;
        this.card = card;
        this.createTime = new Date();
    }

}
