package com.carparts.carparts.controller;

import com.carparts.carparts.model.*;
import com.carparts.carparts.repository.CarPartsRepository;
import com.carparts.carparts.repository.CarRepository;
import com.carparts.carparts.repository.CategoryRepository;
import com.carparts.carparts.repository.SellerRepository;
import com.carparts.carparts.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;

@Controller
public class AdminController {

    @Autowired
    private CarPartsRepository carPartsRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private AdminService adminService;

    @GetMapping("/addNewItem")
    public String addNewItem(Model model) {
        Iterable<Car> allCars = carRepository.findAll();
        Set<String> makers = new HashSet<>();
        Set<String> models = new HashSet<>();
        Set<String> years = new HashSet<>();
        allCars.forEach(c -> {
            makers.add(c.getMaker());
            models.add(c.getModel());
            years.add(c.getYears());
        });

        Set<String> categories = new HashSet<>();
        Iterable<PartsCategory> allCategories = categoryRepository.findAll();
        allCategories.forEach(c -> categories.add(c.getCategoryName()));

        Set<String> sellers = new HashSet<>();
        Iterable<Seller> allSellers = sellerRepository.findAll();
        allSellers.forEach(s -> sellers.add(s.getName()));

        model.addAttribute("sellers", sellers);
        model.addAttribute("ctgs", categories);
        model.addAttribute("makers", makers);
        model.addAttribute("models", models);
        model.addAttribute("years", years);
        model.addAttribute("newPart", new NewPart());
        return "new-parts.html";
    }

    @PostMapping("/addNewItem")
    public String saveNewItem(@Valid @ModelAttribute("newPart") NewPart newPart, Errors errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("checkErr", "yes");
            return "new-parts.html";
        }
        Parts parts = new Parts();
        Optional<Car> car = carRepository.findByMakerAndModelAndYears(newPart.getCarMaker(), newPart.getCarModel(), newPart.getCarYears());
        String errorMessage = null;
        if(car.isEmpty()) {
            errorMessage = "Указаны неверный параметры автомобиля";
            model.addAttribute("errorMessage", errorMessage);
            return "new-parts.html";
        }

        parts.setCar(car.get());
        parts.setPartDescription(newPart.getPartDescription());
        parts.setPartName(newPart.getPartName());
        parts.setPrice(newPart.getPrice());
        parts.setPartNumber(newPart.getPartNumber());
        parts.setSeller(sellerRepository.findByName(newPart.getSellerName()));
        parts.setCategory(categoryRepository.findByCategoryName(newPart.getCategory()));

        Parts savedPart = carPartsRepository.save(parts);
        model.addAttribute("partId", savedPart.getPartId());
        return "save-image.html";
    }

    @PostMapping("/saveImage")
    public String loadImageForItem(@RequestParam("file") MultipartFile file) {
        adminService.saveImageForItem(file, 202);
        return "home.html";
    }
}
