package com.gabrielspassos.poc.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Qualifier("transactionJobListener")
public class TransactionJobListener implements JobExecutionListener {

    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job {}, id {} is starting now",
                jobExecution.getJobInstance().getJobName(),
                jobExecution.getJobInstance().getId());
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        log.info("Job {}, id {} finished with status {}",
                jobExecution.getJobInstance().getJobName(),
                jobExecution.getJobId(),
                jobExecution.getStatus());
    }
}
