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

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by dominik on 2016-11-01.
 * kontroler obsługujący zapytania związane z dodawaniem/usuwaniem/listowaniem znajomych
 */
@RequestMapping("friend")
@RestController
public class FriendsController {
    @Autowired
    UserDao userDao;
    @Autowired
    FriendsNameDao friendsNameDao;

    Predicate<User> checkIsUserNull = u -> Objects.equals(u,null);

    /**
     * usuwanie znajomego z listy znajomych
     *
     * @param username    nazwa użytkownika
     * @param friendsname użytkownik do usunięcia z listy znajomych
     * @return
     */
    @DeleteMapping("/deletefriend/{friendsname}/{username}")
    public ResponseEntity<User> deleteFriend(@PathVariable("username")final String username,
                                             @PathVariable("friendsname")final String friendsname) {
        User user = userDao.findByUsername(username);

        if (checkIsUserNull.test(user)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.getFriends().removeIf( f -> Objects.equals(f.getName(), friendsname));

        friendsNameDao.save(user.getFriends());
        userDao.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * dodawanie do grona znajomych użytkownika innego użytkownika który się staje jego znajomym w rozumieniu aplikacji
     *
     * @param username nazwa użykwonika wywołującego metode
     * @param fname    nazwa osoby mającej być dodaną do grona znajomych
     * @return gdy użytkownik jest znaleziony i nie ma znajomego o podanej nazwie jest dodawany i zwracana jest odpowiedź Ok - kod 200/n
     * w przeciwnym razie zwracany jest kod 404 lub 409
     */
    @PostMapping("/addfriend/{friendsname}/{username}")
    public ResponseEntity<User> addFriend(@PathVariable("username") String username,
                                          @PathVariable("friendsname") String fname){
        FriendsName friend = new FriendsName(fname);
        User user = userDao.findByUsername(username);
        if (checkIsUserNull.test(user)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        int count = (int) user.getFriends().stream().filter(f -> Objects.equals(f.getName(), fname)).count();
        if(count != 0){

            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.addFriend(friend);
        friendsNameDao.save(friend);
        userDao.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Autowired
    @Qualifier("usersList")
    List<User> usersList;
    /**
     * dodawanie nazw znajomych do listy nazw znajomych danego użytkownika
     *
     * @param username nazwa użytkownika chcącego pobrać przyjaciół
     * @return kod 200 w przypadku pozytywnego zakonczenia wszystkich operacji, zaś 404 w przypadku nie odnalezienia użyytkownika o podanej nazwie
     */
    @GetMapping("{username}")
    public ResponseEntity<List<User>> listFriends(@PathVariable("username") String username) {

        User user = userDao.findByUsername(username);
        usersList.clear();

        if (checkIsUserNull.negate().test(user)) {

            user.getFriends().forEach(a -> usersList.add(userDao.findByUsername(a.getName())));
            usersList.sort((a,b) -> a.getUsername().compareTo(b.getUsername()));
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}