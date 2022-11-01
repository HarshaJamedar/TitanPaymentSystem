package edu.csuf;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Utils {
    static HashMap<Card, Double> cardMap = new HashMap<>();
    static SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

    static {
        cardMap.put(Card.VISA, 0.01);
        cardMap.put(Card.AMEX, 0.008);
        cardMap.put(Card.DISCOVER, 0.005);
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static String getBillingCycle(Date date) {
        return convertToLocalDateViaInstant(date).withDayOfMonth(1) + "," + convertToLocalDateViaInstant(date).plusMonths(1).withDayOfMonth(1).minusDays(1);
    }
}
