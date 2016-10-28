package com.dominik.kowalik.DAL;

import com.dominik.kowalik.model.User;
import com.dominik.kowalik.model.UsersName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dominik on 2016-10-28.
 */
@Transactional
@Repository
public interface UsersNameDao extends CrudRepository<UsersName, Long> {
}
