package com.dominik.kowalik.web;

import com.dominik.kowalik.DAL.FriendsNameDao;
import com.dominik.kowalik.DAL.LocationInfoDao;
import com.dominik.kowalik.Helpers.TimeProvider;
import com.dominik.kowalik.model.FriendsName;
import com.dominik.kowalik.model.LocationInfo;
import com.dominik.kowalik.model.User;
import com.dominik.kowalik.DAL.UserDao;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by dominik on 2016-10-19.
 */

/**
 * dostęp mają tylko zalogowani użytkownicy
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    LocationInfoDao locationInfoDao;

    @Autowired
    UserDao userDao;

    @Autowired
    FriendsNameDao friendsNameDao;

    @Autowired
    TimeProvider timeProvider;

    Predicate<User> checkIsUserNull = u -> Objects.equals(null, u);

    /**
     * aktualizowanie lokalizacji konkretnego użyktownika
     *
     * @param username     nazwa użytkownika
     * @param locationInfo obiekt przechowujacy nową lokalizacje użytkownika
     * @return
     */
    @PostMapping(value = "updatecoordinates/{username}")
    public ResponseEntity<Void> updateCoordinates(@PathVariable("username") String username, @RequestBody LocationInfo locationInfo) {
        User user = userDao.findByUsername(username);
        if (checkIsUserNull.test(user))
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        user.setLocationInfo(locationInfo);
        locationInfoDao.save(locationInfo);
        userDao.save(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * aktualizowanie statusu użykwonika
     *
     * @param username nazwa uzyktownika
     * @param status   status
     * @return
     */
    @PostMapping(value = "updatestatus/{username}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateStatus(@PathVariable("username") String username,
                                             @PathVariable("status") String status) {
        User user = userDao.findByUsername(username);
        if (checkIsUserNull.test(user))
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        user.setStatement(status);
        userDao.save(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * wylistowanie wszystkich użytkownikó
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = (List<User>) userDao.findAll();
        if (users.isEmpty())
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        users.sort((a, b) -> a.getUsername().compareTo(b.getUsername()));
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    /**
     * pobranie konkretnego uzykwonika z bazy
     *
     * @param username nazwa uzytkownika
     * @return
     */
    @GetMapping(value = "/byname/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("username") String username){
        User user = userDao.findByUsername(username);
        if (checkIsUserNull.test(user))
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * zapis uzyktownika do bazy
     *
     * @param userPassed uzytkwonik do zapisania w bazie danych
     * @return
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@RequestBody User userPassed) {
        User user = userDao.findByUsername(userPassed.getUsername());
        if (checkIsUserNull.test(user))
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }
}
