package com.dominik.kowalik.Helpers;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * Created by dominik on 2016-11-07.
 */


/**
 * Serwis używany do generowania haseł
 */
@Service
public class PasswordGenerator {

    /**
     * znaki zapisane w tym stringu są używane do generowania nowego hasła
     */
    static final String chars = "0123456789zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP";
    static SecureRandom secureRandom = new SecureRandom();


    /**
     * tworzenie nowego bezpiecznego i losowego hasła dla użyktownika który sobie chciał zresetować swoje
     * @param length długość hasła
     * @return wygenerowane hasło
     */
    public String getRandomPassword(int length){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(chars.charAt(secureRandom.nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }
}
