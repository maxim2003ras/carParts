package com.carparts.carparts.service;

import com.carparts.carparts.model.Car;
import com.carparts.carparts.model.Parts;
import com.carparts.carparts.repository.CarPartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarPartsService {

    @Autowired
    private CarPartsRepository carPartsRepository;


    public List<Parts> getAllPartsForAutoAndSorting(final Car currentCar, final Integer sortingType) {
        if (sortingType == 1) {
            return carPartsRepository.findAllByCarOrderByPriceAsc(currentCar);
        }
        else if (sortingType == 2) {
            return carPartsRepository.findAllByCarOrderByPriceDesc(currentCar);
        }
        else if (sortingType == 3) {
            return carPartsRepository.findAllByCarOrderByCreatedAtAsc(currentCar);
        }
        else {
            return carPartsRepository.findAllByCarOrderByCreatedAtDesc(currentCar);
        }
    }

    public List<Parts> getAllPartsForAutoByCategoryAndSorting(final Car currentCar, final Integer categoryId, final Integer sortingType) {
        if (sortingType == 1) {
            return carPartsRepository.findAllByCarAndCategoryCategoryIdOrderByPriceAsc(currentCar, categoryId);
        }
        else if (sortingType == 2) {
            return carPartsRepository.findAllByCarAndCategoryCategoryIdOrderByPriceDesc(currentCar, categoryId);
        }
        else if (sortingType == 3) {
            return carPartsRepository.findAllByCarAndCategoryCategoryIdOrderByCreatedAtAsc(currentCar, categoryId);
        }
        else {
            return carPartsRepository.findAllByCarAndCategoryCategoryIdOrderByCreatedAtDesc(currentCar, categoryId);
        }

    }
}