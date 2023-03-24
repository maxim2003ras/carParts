package com.carparts.carparts.controller;

import com.carparts.carparts.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class DashboardController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping(value={"", "/", "home"})
    public String displayDashboard() {
        return "home";
    }


}
