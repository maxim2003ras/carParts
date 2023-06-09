package com.carparts.carparts.controller;

import com.carparts.carparts.model.Car;
import com.carparts.carparts.model.Parts;
import com.carparts.carparts.model.Seller;
import com.carparts.carparts.service.CarPartsService;
import com.carparts.carparts.service.CarService;
import com.carparts.carparts.service.SellerService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class CarPartsController {

    @Autowired
    private CarPartsService carPartsService;

    @Autowired
    private CarService carService;

    @Autowired
    private SellerService sellerService;


    @GetMapping("/showParts")
    public ModelAndView showChosenParts(@RequestParam("car") String car,
                                        @RequestParam("carname") String carName,
                                        @RequestParam("caryear") String carYear,
                                        @RequestParam(name = "category", required = false) Optional<Integer> categoryId,
                                        @RequestParam(name = "sorting", required = false) Optional <Integer> sorting,
                                        @RequestParam(name = "seller", required = false) Optional <Integer> sellerId) {

        ModelAndView modelAndView = new ModelAndView("autoParts.html");
        Car currentCar = carService.getCarByParams(car, carName, carYear);
        Integer carId = currentCar.getCarId();
        Integer sortingType = sorting.orElse(1);
        modelAndView.addObject("sortingType", sortingType);

        List<Seller> allSellers = sellerService.loadAllSellers();
        modelAndView.addObject("sellers", allSellers);

        List<Parts> allPartsForAuto;
        if (categoryId.isPresent()) {
            if (sellerId.isPresent()) {
                if (categoryId.get().equals(999) && sellerId.get().equals(999)) {
                    allPartsForAuto = carPartsService.getAllPartsForAutoAndSorting(currentCar, sortingType);
                }
                else if (sellerId.get().equals(999)) {
                    allPartsForAuto = carPartsService.getAllPartsForAutoByCategoryAndSorting(currentCar, categoryId.get(), sortingType);
                }
                else if (categoryId.get().equals(999)){
                    allPartsForAuto = carPartsService.getAllPartsForAutoBySellerAndSorting(currentCar, sellerId.get(), sortingType);
                    modelAndView.addObject("currentSeller", sellerId.get());
                }
                else {
                    allPartsForAuto = carPartsService.getAllPartsForAutoBySortingAndCategoryAndSeller(currentCar, categoryId.get(), sortingType, sellerId.get());
                    modelAndView.addObject("currentSeller", sellerId.get());
                }
            }
            else {
                if (categoryId.get().equals(999)) {
                    allPartsForAuto = carPartsService.getAllPartsForAutoBySellerAndSorting(currentCar, categoryId.get(), sortingType);
                    modelAndView.addObject("currentSeller", sellerId.get());
                }
                else {
                    allPartsForAuto = carPartsService.getAllPartsForAutoByCategoryAndSorting(currentCar, categoryId.get(), sortingType);
                }
            }
            modelAndView.addObject("currentCategory", categoryId.get());
        }
        else {
            allPartsForAuto = carPartsService.getAllPartsForAutoAndSorting(currentCar, sortingType);
        }

        modelAndView.addObject("carPartsForAuto", allPartsForAuto);
        modelAndView.addObject("currentCar", currentCar);
        return modelAndView;
    }

    @GetMapping("/showResults")
    public ModelAndView showSearchResults(@RequestParam("search") String searchQuery) {
        ModelAndView modelAndView = new ModelAndView("searchParts.html");
        List<Parts> allPartsForAuto = carPartsService.getAllPartsBySearch(searchQuery);

        modelAndView.addObject("searchParts", allPartsForAuto);
        return modelAndView;
    }

}
