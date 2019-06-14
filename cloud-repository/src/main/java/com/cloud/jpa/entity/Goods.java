package com.cloud.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by sunhaidi on 2019-03-05.
 */
@Data
@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Goods() {
    }

    public Goods(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Goods(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
