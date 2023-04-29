package com.carparts.carparts.controller;

import com.carparts.carparts.model.Parts;
import com.carparts.carparts.model.Person;
import com.carparts.carparts.repository.CarPartsRepository;
import com.carparts.carparts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class DashboardController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CarPartsRepository carPartsRepository;

    @GetMapping("/home")
    public ModelAndView displayHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        String searchQuery = "";
        modelAndView.addObject("search", searchQuery);
        return modelAndView;
    }


    @GetMapping(value = {"/myCart", "/addToCart/myCart"})
    public ModelAndView displayDashboard(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop_cart");
        String personEmail = authentication.getName();
        Person person = personRepository.readByEmail(personEmail);
        List<Parts> personParts = person.getParts();
        int totalSum = 0;
        for (final Parts personPart : personParts) {
            totalSum += personPart.getPrice();
        }
        modelAndView.addObject("totalSum", totalSum);
        modelAndView.addObject("allParts", person.getParts());
        return modelAndView;
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Integer id, Authentication authentication) {
        String personEmail = authentication.getName();
        Person person = personRepository.readByEmail(personEmail);
        Parts currentPart = carPartsRepository.findById(id).get();
        person.getParts().add(currentPart);
        personRepository.save(person);
        currentPart.getPersons().add(person);
        carPartsRepository.save(currentPart);
        return "redirect:/myCart";
    }

    @GetMapping("/deleteFromCart/{id}")
    public String deleteFromCart(@PathVariable Integer id, Authentication authentication) {
        String personEmail = authentication.getName();
        Parts currentPart = carPartsRepository.findById(id).get();
        Person person = personRepository.readByEmail(personEmail);
        person.getParts().remove(currentPart);
        personRepository.save(person);
        currentPart.getPersons().remove(person);
        carPartsRepository.save(currentPart);
        return "redirect:/myCart";
    }

}
