package com.dominik.kowalik.web;


import com.dominik.kowalik.DAL.AccountDao;
import com.dominik.kowalik.DAL.FriendsNameDao;
import com.dominik.kowalik.DAL.LocationInfoDao;
import com.dominik.kowalik.DAL.UserDao;
  import com.dominik.kowalik.model.Account;
import com.dominik.kowalik.model.LocationInfo;
import com.dominik.kowalik.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Created by dominik on 2016-10-26.
 */

@RestController
@RequestMapping("register")
public class RegistrationController implements ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger("**************INFO**************");
    private ApplicationContext applicationContext;

    @Autowired
    AccountDao accountDao;

    @Autowired
    UserDao userDao;

    @Autowired
    LocationInfoDao locationInfoDao;

    @Autowired
    FriendsNameDao usersNameDao;

    @PostMapping()
    public ResponseEntity<User> registerUser(@RequestBody Account account) {
        Account account1 = (Account) applicationContext.getBean("account");
        if (Objects.equals(accountDao.findByUsername(account.getUsername()), null)) {
            logger.info("creating account" + account.toString());
            account1.setEmail(account.getEmail());
            account1.setPassword(account.getPassword());
            account1.setUsername(account.getUsername());
            account1.getUser().setUsername(account.getUsername());
            account1.getUser().setUsername(account1.getUsername());

            locationInfoDao.save(account1.getUser().getLocationInfo());
            usersNameDao.save(account1.getUser().getFriends());
            userDao.save(account1.getUser());
            accountDao.save(account1);

            HttpHeaders httpHeaders = (HttpHeaders) applicationContext.getBean("httpHeaders");
            return new ResponseEntity<User>(httpHeaders, HttpStatus.OK);
        }
        logger.info("account" + account.toString() + "already exists");
        return new ResponseEntity<User>(HttpStatus.CONFLICT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteAccount(@PathVariable("id") long id) {
        logger.info("Fetching and deleting user with id " + id);
        Account account = accountDao.findOne(id);
        if (Objects.equals(account, null)) {
            logger.info("unable to delete. User with id" + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        accountDao.delete(id);
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    /**
     * delete all users
     *
     * @return
     */

    @DeleteMapping()
    public ResponseEntity<User> deleteAllAccounts(){
        logger.info("Deleting all users");
        accountDao.deleteAll();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

