package com.matej.luxurycars.services;

import java.util.List;

import com.matej.luxurycars.model.Car;
import com.matej.luxurycars.model.Order;
import com.matej.luxurycars.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
public interface CarServices extends CrudRepository<Car,Long>{

    Car findById(Long id);
    List<Car> findAll();


//
//    public boolean addEntity(User user) throws Exception;
//    public User getEntityById(long id) throws Exception;
//    public List<User> getEntityList() throws Exception;
//    public boolean deleteEntity(long id) throws Exception;
}
