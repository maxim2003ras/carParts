package com.carparts.carparts.service;

import com.carparts.carparts.model.Car;
import com.carparts.carparts.model.Parts;
import com.carparts.carparts.repository.CarRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;


    public Car getCarByParams(final String car, final String carName, final String carYear) {
        //TODO add exception if car doesn't exist

        Car currentCar = carRepository.findByMakerAndModelAndYears(car, carName, carYear).get();

        return currentCar;
    }
}
