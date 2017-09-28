package com.matej.luxurycars.services;

/**
 * Created by Mateusz on 2017-09-27.
 */
import java.util.List;

import com.matej.luxurycars.model.Order;
import com.matej.luxurycars.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServices extends CrudRepository<User,Long>{

    User findById(Long id);
    List<User> findAll();
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(Long id);

//
//    public boolean addEntity(User user) throws Exception;
//    public User getEntityById(long id) throws Exception;
//    public List<User> getEntityList() throws Exception;
//    public boolean deleteEntity(long id) throws Exception;
}
