package com.carparts.carparts.controller;

import com.carparts.carparts.model.Parts;
import com.carparts.carparts.service.CarPartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemController {

    @Autowired
    private CarPartsService carPartsService;

    @GetMapping("/itemInfo/{id}")
    public ModelAndView showItem(@PathVariable final Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("info");
        Parts currentPart = carPartsService.getPartInfoById(id);
        modelAndView.addObject("partInfo", currentPart);
        return modelAndView;
    }
}
