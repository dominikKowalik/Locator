package com.dominik.kowalik.Helpers;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.DateFormatSymbols;

/**
 * Created by dominik on 2016-11-17.
 */

@Service("timeProvider")
public class TimeProvider {

    @Resource(name = "upToDateDateTime")
    LocalDateTime localDateTime;

    @Resource(name = "formatter HH:mm dd")
    DateTimeFormatter dateTimeFormatter;

    @Resource(name = "localTime")
    LocalTime localTime;

    public String getUpToDateTime(){
        return localTime.toString();
    }

    public String getUpToDateTimeDate() {
        String monthInPolish;
        int month = localDateTime.getMonthValue();
        switch (month) {
            case 1:
                monthInPolish = "styczen";
                break;
            case 2:
                monthInPolish = "luty";
                break;
            case 3:
                monthInPolish = "marzec";
                break;
            case 4:
                monthInPolish = "kwiecien";
                break;
            case 5:
                monthInPolish = "maj";
                break;
            case 6:
                monthInPolish = "czerwiec";
                break;
            case 7:
                monthInPolish = "lipec";
                break;
            case 8:
                monthInPolish = "sierpien";
                break;
            case 9:
                monthInPolish = "wrzesien";
                break;
            case 10:
                monthInPolish = "pazdziernik";
                break;
            case 11:
                monthInPolish = "listopad";
                break;
            case 12:
                monthInPolish = "grudzien";
                break;
            default:
                monthInPolish = "error";
                break;
        }

        return new StringBuilder().append(localDateTime.format(dateTimeFormatter)).
                append(monthInPolish).toString();
    }

}
