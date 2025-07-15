package com.accenture.challenge.app.reader;

import com.accenture.challenge.app.rest.SalesRaw;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class SalesReader {

    @Value("${batch.sales.source.folder}")
    private String sourceFolder;

    @Value("${batch.sales.source.file-name}")
    private String sourceFileName;

    @Value("${batch.sales.source.fields}")
    private String sourceFields;

    @Bean
    public FlatFileItemReader<SalesRaw> reader() {
        return new FlatFileItemReaderBuilder<SalesRaw>()
                .name("salesRawReader")
                .resource(new FileSystemResource(sourceFolder+"/"+sourceFileName))
                .delimited()
                .names(sourceFields.split(","))
                .targetType(SalesRaw.class)
                .build();
    }
}
