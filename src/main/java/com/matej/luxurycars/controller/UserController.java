package com.matej.luxurycars.controller;

/**
 * Created by Mateusz on 2017-09-27.
 */
import java.util.List;

import com.matej.luxurycars.services.UserServices;
import org.apache.log4j.Logger;
import com.matej.luxurycars.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.matej.luxurycars.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserServices userServices;

    static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getALlUser(){
        return new ResponseEntity(userServices.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userServices.findById(id);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> addUser(@RequestBody User user){
        User _user = userServices.save(user);
        return new ResponseEntity(_user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public void deleteById(@PathVariable Long id) {
        userServices.deleteById(id);
    }
}
