package com.gabrielspassos.poc.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInput {

    private String id;
    private String date;
    private String cardNumber;
    private String amount;

}
