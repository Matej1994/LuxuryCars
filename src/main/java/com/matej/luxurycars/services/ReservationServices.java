package com.matej.luxurycars.services;

import com.matej.luxurycars.model.Reservation;
import com.matej.luxurycars.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReservationServices extends CrudRepository<Reservation,Long> {

    Reservation findById(Long id);
    List<Reservation> findAll();
    List<Reservation> findByUser(User user);
    @Transactional
    Long deleteById(Long id);

}
