package com.matej.luxurycars.services;

import java.time.LocalDate;
import java.util.List;

import com.matej.luxurycars.model.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CarServices extends CrudRepository<Car,Long>{

    Car findById(Long id);
    List<Car> findAll();
    @Transactional
    Long deleteById(Long id);

}
