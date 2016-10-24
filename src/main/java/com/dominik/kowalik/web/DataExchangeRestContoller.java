package com.dominik.kowalik.web;

import com.dominik.kowalik.model.User;
import com.dominik.kowalik.DAL.UserDao;
import com.sun.xml.internal.bind.v2.TODO;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by dominik on 2016-10-19.
 */

@RestController
@RequestMapping("locator")
public class DataExchangeRestContoller {
    private final Logger logger = LoggerFactory.getLogger("***********INFO***********");

    @Autowired
    UserDao userDao;


    @GetMapping(value  ="/user")
    public ResponseEntity<List<User>> listAllUsers(){
        List<User> users = (List<User>) userDao.findAll();
        logger.info(users.get(0).toString());

        if (users.isEmpty()) {
            logger.info("There is no users");
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    //     Retrive single user
    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        logger.info("Fetching user with id " + id);
        User user = userDao.findOne(id);
        if (Objects.equals(user, null)) {
            logger.info("User with id" + id + "not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @PostMapping(value = "user" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody User user) {
         logger.info("Creating user" + user.getName());

        //TODO
//       if(userDao.exists(user)) {
//            logger.info("A user with name " + user.getName() + " already exist");
//       }

        userDao.save(user);

       HttpHeaders headers = new HttpHeaders();
      //  headers.setLocation(uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
       return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //    Delete a User
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        logger.info("Fetching and deleting user with id " + id);
        User user = userDao.findOne(id);
        if (Objects.equals(user, null)) {
            logger.info("unable to delete. User with id" + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userDao.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    //    delete all users
    @DeleteMapping(value = "/user/")
    public ResponseEntity<User> deleteAllUsers() {
        logger.info("Deleting all users");
        userDao.deleteAll();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
