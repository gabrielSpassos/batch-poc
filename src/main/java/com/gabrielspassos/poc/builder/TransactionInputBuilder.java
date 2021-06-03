package com.gabrielspassos.poc.builder;

import com.gabrielspassos.poc.dto.input.TransactionInput;

public class TransactionInputBuilder {

    public static TransactionInput build(String id, String date, String cardNumber, String amount) {
        return TransactionInput.builder()
                .id(id)
                .date(date)
                .cardNumber(cardNumber)
                .amount(amount)
                .build();
    }
}
