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

    List<Parts> findAllByCarAndCategoryCategoryIdAndSellerSellerIdOrderByPriceAsc(Car currentCar, Integer categoryId, Integer sellerId);


    @Query(value = "SELECT * from parts p where p.part_name like %:searchQuery% or p.part_description like %:searchQuery%", nativeQuery = true)
    List<Parts> findAllBySearchQuery(String searchQuery);

    List<Parts> findAllByCarAndSellerSellerIdOrderByPriceAsc(Car currentCar, Integer sellerId);

    List<Parts> findAllByCarAndSellerSellerIdOrderByPriceDesc(Car currentCar, Integer sellerId);

    List<Parts> findAllByCarAndSellerSellerIdOrderByCreatedAtAsc(Car currentCar, Integer sellerId);

    List<Parts> findAllByCarAndSellerSellerIdOrderByCreatedAtDesc(Car currentCar, Integer sellerId);
}
