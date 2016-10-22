package com.dominik.kowalik.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dominik on 2016-10-22.
 */
@Transactional
@Service

public interface UserDao extends CrudRepository<User, Long>{
}

