package com.matej.luxurycars.controller;


import com.matej.luxurycars.model.Reservation;
import com.matej.luxurycars.model.User;
import com.matej.luxurycars.services.ReservationServices;
import com.matej.luxurycars.services.UserServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationServices reservationServices;
    private UserServices userServices;


    @Autowired
    public ReservationController(ReservationServices reservationServices, UserServices userServices) {
        this.userServices = userServices;
        this.reservationServices = reservationServices;
    }

    /*
    Metoda zwraca wszystkie rezerwacje samochodów dla użytkownika z rolą admin i tylko
    zamówienia należące do aktualnie zalogowanego w przypadku innych ról.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Reservation> getAllReservation(Authentication authentication){
        Boolean isAdmin = false;

        if (authentication != null) {
            isAdmin = authentication.getAuthorities()
                    .stream()
                    .anyMatch(o -> o.getAuthority().equals("ROLE_ADMIN"));
        }

        if (isAdmin) {
            return new ResponseEntity(reservationServices.findAll(), HttpStatus.OK);
        } else {
            User user = userServices.findByUsername(authentication.getName());
            return new ResponseEntity(reservationServices.findByUser(user), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id){
        Reservation reservation = reservationServices.findById(id);
        return new ResponseEntity(reservation, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation){
        Reservation _reservation = reservationServices.save(reservation);
        return new ResponseEntity(_reservation, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        return new ResponseEntity(reservationServices.deleteById(id), HttpStatus.OK);
    }
}
