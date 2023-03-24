package com.carparts.carparts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private int sellerId;

    private String name;

    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "seller_parts",
    joinColumns = {
        @JoinColumn(name = "seller_id", referencedColumnName = "sellerId")},
    inverseJoinColumns = {
        @JoinColumn(name = "part_id", referencedColumnName = "partId")})
    private Set<Parts> parts;


}
