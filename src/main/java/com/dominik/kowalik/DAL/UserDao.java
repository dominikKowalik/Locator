package com.dominik.kowalik.DAL;

import com.dominik.kowalik.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dominik on 2016-10-22.
 */
@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Long>{
    public User findByNameAndLastNameAndEmailAdress(String name, String lastName, String emailAdress);
}

