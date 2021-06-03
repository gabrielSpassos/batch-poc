package com.gabrielspassos.poc.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionOutput {

    private String id;
    private LocalDate date;
    private String cardNumber;
    private BigDecimal amount;

}
