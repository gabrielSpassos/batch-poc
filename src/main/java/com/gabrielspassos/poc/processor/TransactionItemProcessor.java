package com.gabrielspassos.poc.processor;


import com.gabrielspassos.poc.builder.TransactionOutputBuilder;
import com.gabrielspassos.poc.dto.input.TransactionInput;
import com.gabrielspassos.poc.dto.output.TransactionOutput;
import com.gabrielspassos.poc.service.DateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.gabrielspassos.poc.config.TransactionConfig.DECIMAL_AMOUNT_LENGTH;

@Slf4j
@Qualifier("transactionItemProcessor")
@StepScope
@Service
public class TransactionItemProcessor implements ItemProcessor<TransactionInput, TransactionOutput> {

    @Override
    public TransactionOutput process(TransactionInput transactionInput) {
        log.info("Recebido transações {}", transactionInput);
        TransactionOutput transactionOutput = buildTransactionOutput(transactionInput);
        log.info("Retornado transacao {}", transactionOutput);
        return transactionOutput;
    }

    private TransactionOutput buildTransactionOutput(TransactionInput transactionInput) {
        return TransactionOutputBuilder.build(
                transactionInput.getId(),
                DateService.formatDate(transactionInput.getDate()),
                transactionInput.getCardNumber(),
                formatAmount(transactionInput.getAmount())
        );
    }

    private BigDecimal formatAmount(String amount) {
        int centsAmountStartIndex = amount.length() - DECIMAL_AMOUNT_LENGTH;
        String decimalAmount = amount.substring(0, centsAmountStartIndex);
        String centsAmount = amount.substring(centsAmountStartIndex);
        String formattedAmount = decimalAmount.concat(".").concat(centsAmount);
        BigDecimal bigDecimal = new BigDecimal(formattedAmount);
        return bigDecimal.setScale(2, RoundingMode.CEILING);
    }
}
