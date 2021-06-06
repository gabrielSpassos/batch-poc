package com.gabrielspassos.poc.config;

import java.util.Arrays;
import java.util.List;

public class TransactionConfig {

    public static final int ID_START_INDEX = 1;
    public static final int ID_END_INDEX = 4;
    public static final int DATE_START_INDEX = 5;
    public static final int DATE_END_INDEX = 12;
    public static final int CARD_NUMBER_START_INDEX = 13;
    public static final int CARD_NUMBER_END_INDEX = 28;
    public static final int AMOUNT_START_INDEX = 29;
    public static final int AMOUNT_END_INDEX = 39;
    public static final int DECIMAL_AMOUNT_LENGTH = 2;
    public static final String INPUT_FILE_NAME = "transaction.txt";
    public static final String OUTPUT_FILE_NAME = "src/main/resources/transaction.csv";
    public static final String DELIMITER = ";";
    public static final List<String> FIELD_NAMES = Arrays.asList("id", "date", "cardNumber", "amount");
}
