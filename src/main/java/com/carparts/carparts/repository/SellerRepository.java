package com.carparts.carparts.repository;

import com.carparts.carparts.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {

    Seller findByName(String sellerName);
}
