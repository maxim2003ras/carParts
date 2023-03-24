package com.carparts.carparts.service;

import com.carparts.carparts.model.Seller;
import com.carparts.carparts.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService{
    private final SellerRepository sellerRepository;

    @Override
    public List<Seller> loadAllSellers() {
        return sellerRepository.findAll();
    }
}
