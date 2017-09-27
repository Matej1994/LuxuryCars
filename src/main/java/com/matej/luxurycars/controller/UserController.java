package com.matej.luxurycars.controller;

/**
 * Created by Mateusz on 2017-09-27.
 */
import java.util.List;

import com.matej.luxurycars.services.UserServices;
import org.apache.log4j.Logger;
import com.matej.luxurycars.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @Autowired
    UserServices userServices;

    static final Logger logger = Logger.getLogger(UserController.class);

    /* Submit form in Spring Restful Services */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Status addUser(@RequestBody User user) {
        try {
            userServices.addEntity(user);
            return new Status(1, "Użytkownik został dodany pomyślnie");
        } catch (Exception e) {
            // e.printStackTrace();
            return new Status(0, e.toString());
        }

    }

    /* Ger a single objct in Json form in Spring Rest Services */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    User getEmployee(@PathVariable("id") long id) {
        User user = null;
        try {
            user = userServices.getEntityById(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /* Getting List of objects in Json format in Spring Restful Services */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getEmployee() {

        List<User> userList = null;
        try {
            userList = userServices.getEntityList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    /* Delete an object from DB in Spring Restful Services */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Status deleteUser(@PathVariable("id") long id) {

        try {
            userServices.deleteEntity(id);
            return new Status(1, "Użytkownik usunięty pomyślnie!");
        } catch (Exception e) {
            return new Status(0, e.toString());
        }

    }
}
