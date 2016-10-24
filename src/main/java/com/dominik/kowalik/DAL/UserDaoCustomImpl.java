package com.dominik.kowalik.DAL;

import com.dominik.kowalik.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by dominik on 2016-10-24.
 */
public class UserDaoCustomImpl implements UserDaoCustom{

    @PersistenceContext
    private EntityManager em;

    public Iterable<User> findAll() {
        em.getTransaction();
        Iterable<User> users = em.createQuery("select e from user e").getResultList();
        em.getTransaction().commit();
        em.close();

        return users;
    }


    //TODO
//    public boolean exists(User user){
//        em.getTransaction();
//        return  true;
//    }


}
