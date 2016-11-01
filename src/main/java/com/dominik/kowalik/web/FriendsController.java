package com.dominik.kowalik.web;

import com.dominik.kowalik.DAL.FriendsNameDao;
import com.dominik.kowalik.DAL.UserDao;
import com.dominik.kowalik.model.FriendsName;
import com.dominik.kowalik.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Created by dominik on 2016-11-01.
 */
@RequestMapping("friend")
@RestController
public class FriendsController {
    private final Logger logger = LoggerFactory.getLogger("**************INFO**************");
    @Autowired
    UserDao userDao;
    @Autowired
    FriendsNameDao friendsNameDao;

    /**
     * adding friend to friendlist
     *
     * @param username
     * @param friendsname
     * @return
     */
    @PostMapping("/addfriend/{friendsname}/{username}")
    public ResponseEntity<User> addFriend(@PathVariable("username") String username,
                                          @PathVariable("friendsname") String friendsname) {
        logger.info("dodawanie znajomego");
        FriendsName friend = new FriendsName(friendsname);
        User user = userDao.findByUsername(username);

        if (Objects.equals(user, null)) {
            logger.info("user names" + username + "doesnt exists");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        user.addFriend(friend);
        friendsNameDao.save(friend);
        userDao.save(user);
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    /**
     * returning user's friendlist
     */
    @Autowired
    @Qualifier("usersList")
    List<User> usersList;

    @GetMapping("{username}")
    public ResponseEntity<List<User>> listFriends(@PathVariable("username") String username
    ) {
        logger.info("first line listFriends method");
        User user = userDao.findByUsername(username);
        usersList.clear();

        if (!Objects.equals(user, null)) {
            logger.info("user exsits");
            for (FriendsName friend : user.getFriends()) {
                usersList.add(userDao.findByUsername(friend.getName()));
            }
            return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
        }
        logger.info("user doesnt exist");
        return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
    }
}