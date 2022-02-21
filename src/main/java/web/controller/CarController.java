package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.CarService;


@Controller
public class CarController {

    @RequestMapping(value = "/cars")
    public String printCarsWithCount(Model carModel, @RequestParam(value = "count", defaultValue = "-1") int count) {
        carModel.addAttribute("carMessage", "Hi, this is my cars:");
        carModel.addAttribute("carList", CarService.getSomeCars(count));
        return "cars";
    }
}
