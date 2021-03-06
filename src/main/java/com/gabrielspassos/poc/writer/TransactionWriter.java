package com.gabrielspassos.poc.writer;

import com.gabrielspassos.poc.dto.output.TransactionOutput;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

import static com.gabrielspassos.poc.config.TransactionConfig.DELIMITER;
import static com.gabrielspassos.poc.config.TransactionConfig.FIELD_NAMES;
import static com.gabrielspassos.poc.config.TransactionConfig.OUTPUT_FILE_NAME;

@Component
public class TransactionWriter {

    @Qualifier("transactionsCsvWriter")
    @Bean
    @StepScope
    public FlatFileItemWriter<TransactionOutput> writeTransactionsCsv() {
        BeanWrapperFieldExtractor<TransactionOutput> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(FIELD_NAMES.toArray(String[]::new));

        DelimitedLineAggregator<TransactionOutput> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(DELIMITER);
        lineAggregator.setFieldExtractor(fieldExtractor);

        FlatFileHeaderCallback headerCallback = headerWriter -> headerWriter.write(getHeader());
        FlatFileItemWriter<TransactionOutput> writer = new FlatFileItemWriter<>();

        writer.setResource(new FileSystemResource(OUTPUT_FILE_NAME));
        writer.setHeaderCallback(headerCallback);
        writer.setLineAggregator(lineAggregator);
        return writer;
    }

    private String getHeader() {
        return FIELD_NAMES.stream()
                .map(StringUtils::capitalize)
                .collect(Collectors.joining(DELIMITER));
    }
}
