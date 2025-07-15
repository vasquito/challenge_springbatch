package com.accenture.challenge.app.config.batch;

import com.accenture.challenge.app.rest.SalesRaw;
import com.accenture.challenge.sales.dao.domains.Sales;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SalesBatchConfig {

    @Value("${batch.sales.chunk-size}")
    private Integer chunkSize;

    @Value("${batch.sales.retry-limit}")
    private Integer retryLimit;

    @Value("${batch.sales.skip-limit}")
    private Integer skipLimit;

    @Bean
    public Job salesImportJob(JobRepository salesJobRepository,
                                 Step salesProcessStep,
                                 Step salesMoveStep) {
        return new JobBuilder("salesImportJob", salesJobRepository)
                .start(salesProcessStep)
                .next(salesMoveStep)
                .build();
    }

    @Bean
    public Step salesProcessStep(JobRepository salesJobRepository,
                            PlatformTransactionManager salesTransactionManager,
                            FlatFileItemReader<SalesRaw> salesReader,
                            ItemProcessor<SalesRaw, Sales> salesProcessor,
                            ItemWriter<Sales> salesWriter) {
        return new StepBuilder("salesProcessStep", salesJobRepository)
                .<SalesRaw, Sales>chunk(chunkSize, salesTransactionManager)
                .reader(salesReader)
                .processor(salesProcessor)
                .writer(salesWriter)
                .faultTolerant()
                .retryLimit(retryLimit).retry(Exception.class)
                .skipLimit(skipLimit).skip(Exception.class)
                .build();
    }

    @Bean
    public Step salesMoveStep(JobRepository salesJobRepository,
                         PlatformTransactionManager salesTransactionManager,
                         Tasklet salesFileMoveTasklet) {
        return new StepBuilder("salesMoveStep", salesJobRepository)
                .tasklet(salesFileMoveTasklet, salesTransactionManager)
                .build();
    }
}