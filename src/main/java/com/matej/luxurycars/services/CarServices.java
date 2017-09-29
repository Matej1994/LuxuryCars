package com.matej.luxurycars.services;

import java.time.LocalDate;
import java.util.List;

import com.matej.luxurycars.model.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarServices extends CrudRepository<Car,Long>{

    Car findById(Long id);
    List<Car> findAll();
    @Query("SELECT c FROM Reservation res INNER JOIN res.car c ON c.id = res.car WHERE expiration_date < ?1")
    List<Car> findExpirated(LocalDate date);
    Long deleteById(Long id);

}
