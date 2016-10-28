package com.dominik.kowalik.DAL;

import com.dominik.kowalik.model.Account;
import com.dominik.kowalik.model.LocationInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dominik on 2016-10-28.
 */

@Transactional
@Repository
public interface LocationInfoDao extends CrudRepository<LocationInfo, Long> {
}