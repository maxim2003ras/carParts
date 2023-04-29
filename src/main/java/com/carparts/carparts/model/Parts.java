package com.carparts.carparts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Parts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private int partId;

    private int partNumber;

    private int price;

    private String partName;

    private String partDescription;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "carId")
    private Car car;

    @OneToOne(cascade = CascadeType.PERSIST, targetEntity = PartsCategory.class)
    @JoinColumn(name = "category_id", referencedColumnName = "categoryId")
    private PartsCategory category;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "sellerId")
    private Seller seller;

    @ManyToMany(mappedBy = "parts", fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private List<Person> persons = new ArrayList<>();
}
