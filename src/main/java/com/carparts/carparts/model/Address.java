package com.carparts.carparts.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@Entity
public class Address extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    private int addressId;

    @NotBlank(message="Поле с адресом не может быть пустым!")
    @Size(min=5, message="Длина адреса как минимум 5 символов!")
    private String address1;

    private String address2;

    @NotBlank(message="Поле город не может быть пустым!")
    @Size(min=5, message="Длина города минимум 5 символов")
    private String city;

    @NotBlank(message="Область не должна быть пустой!")
    @Size(min=5, message="Длина поля область минимум 5 символов!")
    private String state;

    @NotBlank(message="Поле индекс не может быть пустым")
    @Pattern(regexp="(^$|[0-9]{5})",message = "Длина поля индекс минимум 5 символов!")
    private String zipCode;


}
