package com.carparts.carparts.repository;

import com.carparts.carparts.model.Car;
import com.carparts.carparts.model.Parts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarPartsRepository extends CrudRepository<Parts, Integer> {

    List<Parts> findAllByCarOrderByPriceAsc(Car car);
    List<Parts> findAllByCarAndCategoryCategoryIdOrderByPriceAsc(Car car, Integer id);
    List<Parts> findAllByCarAndCategoryCategoryIdOrderByPriceDesc(Car currentCar, Integer categoryId);
    List<Parts> findAllByCarAndCategoryCategoryIdOrderByCreatedAtAsc(Car currentCar, Integer categoryId);
    List<Parts> findAllByCarAndCategoryCategoryIdOrderByCreatedAtDesc(Car currentCar, Integer categoryId);

    List<Parts> findAllByCarOrderByPriceDesc(Car currentCar);

    List<Parts> findAllByCarOrderByCreatedAtAsc(Car currentCar);

    List<Parts> findAllByCarOrderByCreatedAtDesc(Car currentCar);
}
