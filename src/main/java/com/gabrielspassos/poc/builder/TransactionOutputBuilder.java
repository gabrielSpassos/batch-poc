package com.gabrielspassos.poc.builder;

import com.gabrielspassos.poc.dto.output.TransactionOutput;

import java.math.BigDecimal;

public class TransactionOutputBuilder {

    public static TransactionOutput build(String id, String date, String cardNumber, BigDecimal amount) {
        return TransactionOutput.builder()
                .id(id)
                .date(date)
                .cardNumber(cardNumber)
                .amount(amount)
                .build();
    }
}
