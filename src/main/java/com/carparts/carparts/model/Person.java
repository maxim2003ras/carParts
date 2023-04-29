package com.carparts.carparts.model;

import com.carparts.carparts.annotation.FieldsValueMatch;
import com.carparts.carparts.annotation.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Пароли не совпадают!"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Email не совпадают!"
        )
})
public class Person extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private int personId;

    @NotBlank(message="Имя не должно быть пустым!")
    @Size(min=3, message="Длина имени минимум 3 символа!")
    private String name;

    @NotBlank(message="Номер телефона не должен быть пустым!")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Номер телефона должен состоять из 10 цифр!")
    private String mobileNumber;

    @NotBlank(message="Email не должен быть пустым")
    @Email(message = "Укажите настоящий email!" )
    private String email;

    @NotBlank(message="Поле с подтверждением email не может быть пустым!")
    @Email(message = "Указанные email не совпадают!" )
    @Transient
    private String confirmEmail;

    @NotBlank(message="Пароль не может быть пустым!")
    @Size(min=5, message="Длина пароля минимум 5 символов!")
    @PasswordValidator
    private String pwd;

    @NotBlank(message="Поле с подтверждением пароля не может быть пустым!")
    @Size(min=5, message="Укажите правильный пароль!")
    @Transient
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId",nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId",nullable = true)
    private Address address;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "person_parts",
            joinColumns = {
                    @JoinColumn(name = "person_id", referencedColumnName = "personId")},
            inverseJoinColumns = {
                    @JoinColumn(name = "part_id", referencedColumnName = "partId")})
    private List<Parts> parts = new ArrayList<>();
}
