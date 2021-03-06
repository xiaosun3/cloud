package com.cloud.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sunhaidi on 2019-03-05.
 */
@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "age")
    private Integer age;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "channel")
    private Integer channel;

    @Column(name = "card")
    private String card;

    @Column(name = "card_type")
    private Integer cardtype;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public Customer() {

    }

    public Customer( String name, String card) {
        this.name = name;
        this.card = card;
        this.createTime = new Date();
    }

}
