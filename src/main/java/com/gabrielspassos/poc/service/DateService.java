package com.gabrielspassos.poc.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
    private static final DateTimeFormatter BR_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formatDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DATE_FORMATTER);
        return BR_DATE_FORMATTER.format(localDate);
    }
}
