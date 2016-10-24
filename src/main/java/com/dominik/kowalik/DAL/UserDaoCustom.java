package com.dominik.kowalik.DAL;

import com.dominik.kowalik.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dominik on 2016-10-24.
 */
public interface UserDaoCustom{
    public Iterable<User> findAll();
 //   public boolean exists(User user);
}
