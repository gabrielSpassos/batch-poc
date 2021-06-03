package com.gabrielspassos.poc.reader;

import com.gabrielspassos.poc.dto.input.TransactionInput;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import static com.gabrielspassos.poc.config.TransactionConfig.AMOUNT_END_INDEX;
import static com.gabrielspassos.poc.config.TransactionConfig.AMOUNT_START_INDEX;
import static com.gabrielspassos.poc.config.TransactionConfig.CARD_NUMBER_END_INDEX;
import static com.gabrielspassos.poc.config.TransactionConfig.CARD_NUMBER_START_INDEX;
import static com.gabrielspassos.poc.config.TransactionConfig.DATE_END_INDEX;
import static com.gabrielspassos.poc.config.TransactionConfig.DATE_START_INDEX;
import static com.gabrielspassos.poc.config.TransactionConfig.ID_END_INDEX;
import static com.gabrielspassos.poc.config.TransactionConfig.ID_START_INDEX;

@Service
@Qualifier("transactionReader")
public class TransactionReader {

    private static final String FILE_NAME = "transaction.txt";

    public ItemReader<TransactionInput> reader() {
        FlatFileItemReader<TransactionInput> csvFileReader = new FlatFileItemReader<>();
        csvFileReader.setResource(new ClassPathResource(FILE_NAME));

        LineMapper<TransactionInput> studentLineMapper = createTransactionLineMapper();
        csvFileReader.setLineMapper(studentLineMapper);

        return csvFileReader;
    }

    private LineMapper<TransactionInput> createTransactionLineMapper() {
        DefaultLineMapper<TransactionInput> studentLineMapper = new DefaultLineMapper<>();

        LineTokenizer transactionLineTokenizer = createTransactionLineTokenizer();
        studentLineMapper.setLineTokenizer(transactionLineTokenizer);

        FieldSetMapper<TransactionInput> transactionInformationMapper = createTransactionInformationMapper();
        studentLineMapper.setFieldSetMapper(transactionInformationMapper);

        return studentLineMapper;
    }

    private FixedLengthTokenizer createTransactionLineTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

        tokenizer.setNames("id", "date", "cardNumber", "amount");
        tokenizer.setColumns(
                new Range(ID_START_INDEX, ID_END_INDEX),
                new Range(DATE_START_INDEX, DATE_END_INDEX),
                new Range(CARD_NUMBER_START_INDEX, CARD_NUMBER_END_INDEX),
                new Range(AMOUNT_START_INDEX, AMOUNT_END_INDEX)
        );
        return tokenizer;
    }

    private FieldSetMapper<TransactionInput> createTransactionInformationMapper() {
        BeanWrapperFieldSetMapper<TransactionInput> studentInformationMapper = new BeanWrapperFieldSetMapper<>();
        studentInformationMapper.setTargetType(TransactionInput.class);
        return studentInformationMapper;
    }
}
