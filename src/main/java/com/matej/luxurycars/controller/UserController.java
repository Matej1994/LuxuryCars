package com.matej.luxurycars.controller;

/**
 * Created by Mateusz on 2017-09-27.
 */

import com.matej.luxurycars.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserController(UserServices userServices, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userServices = userServices;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getAllUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User _user = userServices.save(user);
        return new ResponseEntity(_user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        return new ResponseEntity(userServices.deleteById(id), HttpStatus.OK);
    }
}
