package com.gabrielspassos.poc.config;

import com.gabrielspassos.poc.dto.input.TransactionInput;
import com.gabrielspassos.poc.dto.output.TransactionOutput;
import com.gabrielspassos.poc.processor.TransactionItemProcessor;
import com.gabrielspassos.poc.reader.TransactionReader;
import com.gabrielspassos.poc.service.ReportService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public BatchConfig(DataSource dataSource,
                       JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory) {
        super(dataSource);
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Qualifier("transactionJob")
    @Bean
    protected Job transactionJob(@Qualifier("transactionFileStep") Step transactionFileStep,
                                 @Qualifier("transactionJobListener") JobExecutionListener transactionJobListener,
                                 @Qualifier("reportStep") Step reportStep) {
        return jobBuilderFactory
                .get("transactionJob")
                .start(transactionFileStep)
                .next(reportStep)
                .incrementer(new RunIdIncrementer())
                .listener(transactionJobListener)
                .build();
    }

    @Qualifier("transactionFileStep")
    @Bean
    protected Step transactionFileStep(
            @Qualifier("transactionReader") TransactionReader transactionReader,
            @Qualifier("transactionItemProcessor") TransactionItemProcessor transactionItemProcessor,
            @Qualifier("transactionsCsvWriter") ItemWriter<TransactionOutput> transactionConciliationFileWriter) {
        return stepBuilderFactory.get("transactionFileStep")
                .<TransactionInput, TransactionOutput>chunk(2)
                .reader(transactionReader.reader())
                .processor(transactionItemProcessor)
                .writer(transactionConciliationFileWriter)
                .allowStartIfComplete(Boolean.TRUE)
                .build();
    }

    @Qualifier("reportStep")
    @Bean
    protected Step reportStep(ReportService reportService) {
        return stepBuilderFactory.get("reportStep")
                .tasklet(reportTasklet(reportService))
                .allowStartIfComplete(Boolean.TRUE)
                .build();
    }

    @Qualifier("reportTasklet")
    @Bean
    protected MethodInvokingTaskletAdapter reportTasklet(ReportService reportService) {
        MethodInvokingTaskletAdapter methodInvokingTaskletAdapter = new MethodInvokingTaskletAdapter();
        methodInvokingTaskletAdapter.setTargetObject(reportService);
        methodInvokingTaskletAdapter.setTargetMethod("sendReport");
        methodInvokingTaskletAdapter.setArguments(new String[] {"argument"});
        return methodInvokingTaskletAdapter;
    }

}
