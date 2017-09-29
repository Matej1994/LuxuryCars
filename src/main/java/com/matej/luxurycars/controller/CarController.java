package com.matej.luxurycars.controller;

import com.matej.luxurycars.model.Car;
import com.matej.luxurycars.services.CarServices;
import com.matej.luxurycars.services.UserServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarServices carServices;
    private UserServices userServices;


    @Autowired
    public CarController(CarServices carServices) {
        this.carServices = carServices;
        this.userServices = userServices;
    }

    // Metoda zwraca listę dostępnych aut dla użytkownika z rolą admin
    // oraz listę wyłącznie aut dostępnych aktualnie dla użytkownika z inną rolą.
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Car> getAllCar(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(o -> o.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return new ResponseEntity(carServices.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity(carServices.findExpirated(LocalDate.now()), HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Car> getCar(@PathVariable Long id){
        Car car = carServices.findById(id);
        return new ResponseEntity(car, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        Car _car = carServices.save(car);
        return new ResponseEntity(_car, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        return new ResponseEntity(carServices.deleteById(id), HttpStatus.OK);
    }
}
