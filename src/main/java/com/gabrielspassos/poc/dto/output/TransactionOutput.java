package com.gabrielspassos.poc.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionOutput {

    private String id;
    private String date;
    private String cardNumber;
    private BigDecimal amount;

}
