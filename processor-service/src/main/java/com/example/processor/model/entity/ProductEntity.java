package com.example.processor.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
@Accessors(chain = true)
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title ;

    @Column(name = "CATEGORY")
    private String category ;

    @Column(name = "PRICE")
    private Long price ;

    @Column(name = "DESCRIPTION")
    private String description ;
}
