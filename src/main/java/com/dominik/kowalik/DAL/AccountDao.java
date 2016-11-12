package com.dominik.kowalik.DAL;

import com.dominik.kowalik.model.Account;
import com.dominik.kowalik.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dominik on 2016-10-26.
 */

@Transactional
@Repository
public interface AccountDao extends CrudRepository<Account, Long>{
public Account findByUsername(String username);
public Account findByEmail(String email);
}
