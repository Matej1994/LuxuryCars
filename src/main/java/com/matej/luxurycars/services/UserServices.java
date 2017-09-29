package com.matej.luxurycars.services;

/**
 * Created by Mateusz on 2017-09-27.
 */
import java.util.List;

import com.matej.luxurycars.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserServices extends CrudRepository<User,Long>{

    User findById(Long id);
    List<User> findAll();
    Long deleteById(Long id);
    User findByUsername(String username);

}
