package com.carparts.carparts.repository;

import com.carparts.carparts.model.PartsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<PartsCategory, Integer> {
    PartsCategory findByCategoryName(String category);
}
