package com.dominik.kowalik.web;

import com.dominik.kowalik.DAL.AccountDao;
import com.dominik.kowalik.DAL.FriendsNameDao;
import com.dominik.kowalik.DAL.LocationInfoDao;
import com.dominik.kowalik.DAL.UserDao;
import com.dominik.kowalik.Helpers.PasswordGenerator;
import com.dominik.kowalik.model.Account;
import com.dominik.kowalik.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by dominik on 2016-10-26.
 * do tego kontrolera mają dostęp niezlogowani użytkownicy
 */
@RestController
@RequestMapping("register")
public class RegistrationController {
    @Autowired
    AccountDao accountDao;

    @Autowired
    UserDao userDao;

    @Autowired
    LocationInfoDao locationInfoDao;

    @Autowired
    FriendsNameDao usersNameDao;

    @Autowired
    SMTPMailSender smtpMailSender;

    @Autowired
    PasswordGenerator passwordGenerator;

    Predicate checkIsAccountNull = a -> Objects.equals(null, a);

    /**
     * metoda wysyłająca przypomnienie hasła na adres email użytkownika kóry to on podaje przy rejestracji
     *
     * @param email adres email uzytkownika ktory zapomnial hasło
     * @return zwracany status OK - 200 w przpadku gdy hasło zostanie pomyślnie wysłane na podany adres status CONFLICT - 409 gdy ta operacja się nie uda
     */
    @PostMapping("reset_password/{email:.+}")
    public ResponseEntity<Void> sendmail(@PathVariable("email") String email) {
        Account account = accountDao.findByEmail(email);
        if (checkIsAccountNull.test(account))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        String randomPassword = passwordGenerator.getRandomPassword(15);
        account.setPassword(randomPassword);
        try {
            smtpMailSender.sendPasswordRemind(email, randomPassword);
            accountDao.save(account);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MessagingException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * rejestracja użytkownika ustawianie hasła, mejla, nazwy użtkownika, lokalizacji i tworzenie konta publicznego \n
     * hibernate działa w trybie MERGE czyli trzeba zapisywać wszytkie osobne obiekty w bazie
     *
     * @param account konto nowego użytkownika które jest zapisywane w bazie
     * @return status 409 - CONFLICT gdy podany użytkownik nie istnieje, status 200 - OK gdy użytkownik jeszcze nie istnieje i zostanie zapisany pomyślnie
     */

    @Autowired
    ApplicationContext applicationContext;

    @PostMapping()
    public ResponseEntity<User> registerUser(@RequestBody Account account) {
        Account account1 = (Account) applicationContext.getBean("account");
         if (Objects.equals(accountDao.findByUsername(account.getUsername()), null)) {
             account1.setEmail(account.getEmail());
            account1.setPassword(account.getPassword());
            account1.setUsername(account.getUsername());
            account1.getUser().setUsername(account.getUsername());
            account1.getUser().setUsername(account1.getUsername());
            locationInfoDao.save(account1.getUser().getLocationInfo());
            usersNameDao.save(account1.getUser().getFriends());
            userDao.save(account1.getUser());
            accountDao.save(account1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
         return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    /**
     * usuwanie użytkownika po id
     *
     * @param id klucz główny użytkownika w bazie
     * @return 200 - istnieje i usuniety w przeciwnym razie 404 - Not Found
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteAccount(@PathVariable("id") long id) {
        Account account = accountDao.findOne(id);
        if (checkIsAccountNull.test(account))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        accountDao.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * usuwanie wszystkich użytkowników
     *
     * @return
     */

    @DeleteMapping()
    public ResponseEntity<User> deleteAllAccounts() {
        accountDao.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

