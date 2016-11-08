package com.dominik.kowalik.Logic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * Created by dominik on 2016-11-07.
 */

@Component
@Qualifier("passwordGenerator")
public class PasswordGenerator{
    static final String chars = "0123456789zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP";
    static SecureRandom secureRandom = new SecureRandom();

   public String getRandomPassword(int length){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < length;i++){
           stringBuilder.append(chars.charAt(secureRandom.nextInt(chars.length())));
        }
       return stringBuilder.toString();
    }
}
