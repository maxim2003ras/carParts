package com.carparts.carparts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NewPart {


        @NotNull(message = "Номер запчасти не может быть пустым")
        private int partNumber;

        @NotNull(message = "Укажите цену запчасти")
        @Min(value = 1, message = "Минимальная цена 1")
        private int price;

        @NotBlank(message="Название не может быть пустым")
        @Size(min=3, max = 100, message="Длина названия от 3 до 100 символов")
        private String partName;

        @NotBlank(message="Описание не может быть пустым")
        @Size(min=3, max = 500, message="Длина описания от 3 до 500 символов!")
        private String partDescription;

        private String carMaker;

        private String carModel;

        private String carYears;

        private String category;

        private String sellerName;



}
