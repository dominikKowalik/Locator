package com.dominik.kowalik.web;

import com.dominik.kowalik.model.User;
import com.dominik.kowalik.DAL.UserDao;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Objects;

/**
 * Created by dominik on 2016-10-19.
 */

@RestController
@RequestMapping("user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger("**************INFO**************");

    @Autowired
    UserDao userDao;


    //    CRUD
    @GetMapping()
    public ResponseEntity<List<User>> listAllUsers(){
        List<User> users = (List<User>) userDao.findAll();
        logger.info(users.get(0).toString());

        if (users.isEmpty()) {
            logger.info("There is no users");
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

//    //     Retrives single user
//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> getUser(@PathVariable("id") long id){
//        logger.info("Fetching user with id " + id);
//        User user = userDao.findOne(id);
//        if (Objects.equals(user, null)) {
//            logger.info("User with id" + id + "not found");
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//    }

    //     Retrives single user
    @GetMapping(value = "/byname/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        logger.info("Fetching user with name" + username);
        User user = userDao.findByUsername(username);
        if (Objects.equals(user, null)) {
            logger.info("User with username" + username + "not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    /**
     * creates single user and persitance to the database
     * @param userPassed
     * @return
     */

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@RequestBody User userPassed){
        logger.info("Creating user" + userPassed.getUsername());
        User user = userDao.findByUsername(userPassed.getUsername());
        /**
         * the way to update user if exists they will be auto-perist by hibernate
         */
        if (!Objects.equals(user, null)) {
            logger.info("A user with name " + userPassed.getUsername() + " already exist they will be updated");
        //    user.updateUser(userPassed);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }
}
