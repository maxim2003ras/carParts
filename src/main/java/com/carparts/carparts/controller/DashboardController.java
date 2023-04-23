package com.carparts.carparts.controller;

import com.carparts.carparts.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {


    @GetMapping(value={ "/dashboard"})
    public String displayDashboard() {
        return "shop_cart";
    }


}
