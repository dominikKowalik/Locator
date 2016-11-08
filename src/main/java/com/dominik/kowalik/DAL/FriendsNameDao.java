package com.dominik.kowalik.DAL;

import com.dominik.kowalik.model.FriendsName;
import com.dominik.kowalik.model.User;
 import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dominik on 2016-10-28.
 */
@Transactional
@Repository
public interface FriendsNameDao extends CrudRepository<FriendsName, Long> {
 public FriendsName deleteByName(String friendName);
}
