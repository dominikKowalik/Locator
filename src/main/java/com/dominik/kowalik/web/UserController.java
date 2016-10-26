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
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = (List<User>) userDao.findAll();
        logger.info(users.get(0).toString());

        if (users.isEmpty()) {
            logger.info("There is no users");
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    //     Retrives single user
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        logger.info("Fetching user with id " + id);
        User user = userDao.findOne(id);
        if (Objects.equals(user, null)) {
            logger.info("User with id" + id + "not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

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
     * @param user
     * @return
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody User user) {

        logger.info("Creating user" + user.getUsername());
        User user1 = userDao.findByUsername(user.getUsername());


        /**
         * the way to update user if exists they will be auto-perist by hibernate
         */
        if (!Objects.equals(user1, null)) {
            logger.info("A user with name " + user.getUsername() + " already exist they will be updated");
            user1.updateUser(user);
        }

        userDao.save(user);
        HttpHeaders headers = new HttpHeaders();
        //TODO
        //   headers.setLocation(uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());


        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }




    @PostMapping("{username}/{email}")
    public ResponseEntity<Void> createUser(@PathVariable("username") String username, @PathVariable ("email") String email) {
        logger.info("Creating user" + username);
        User user1 = userDao.findByUsername(username);
        /**
         * the way to update user if exists they will be auto-perist by hibernate
         */
        if (!Objects.equals(user1, null)) {
            logger.info("A user with name " + username + " already exist they will be updated");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        user1 = new User(username,email);
        userDao.save(user1);
        HttpHeaders headers = new HttpHeaders();
        //TODO
        //   headers.setLocation(uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**   Delete a User
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
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

    /**
     * delete all users
     * @return
     */
    @DeleteMapping()
    public ResponseEntity<User> deleteAllUsers() {
        logger.info("Deleting all users");
        userDao.deleteAll();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
