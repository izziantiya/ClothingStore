package com.store.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "image")
    private String image;

    @Column(length = 3000, name = "description")
    private String description;

    @Column(name = "country")
    private String country;

    @Column(name = "material")
    private String material;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @ManyToMany(mappedBy = "productList", fetch = FetchType.EAGER)
    private List<User> userList;

    public Product() {
    }
}
