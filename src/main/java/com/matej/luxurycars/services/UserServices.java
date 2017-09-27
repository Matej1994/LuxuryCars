package com.matej.luxurycars.services;

/**
 * Created by Mateusz on 2017-09-27.
 */
import java.util.List;

import com.matej.luxurycars.model.User;

public interface UserServices {
    public boolean addEntity(User user) throws Exception;
    public User getEntityById(long id) throws Exception;
    public List<User> getEntityList() throws Exception;
    public boolean deleteEntity(long id) throws Exception;
}
