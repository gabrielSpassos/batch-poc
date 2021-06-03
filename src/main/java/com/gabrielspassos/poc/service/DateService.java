package com.gabrielspassos.poc.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateService {

    private static final String DATE_PATTERN = "dd/MM/yyyy";

    public static LocalDate convertToLocalDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
