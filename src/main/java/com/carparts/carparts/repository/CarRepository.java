package com.carparts.carparts.repository;

import com.carparts.carparts.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
    Optional<Car> findByMakerAndModelAndYears(String maker, String model, String years);
}
