package com.accenture.challenge.app.writer;

import com.accenture.challenge.sales.dao.SalesRepository;
import com.accenture.challenge.sales.dao.domains.Sales;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalesWriter {

    @Bean
    public ItemWriter<Sales> writer(SalesRepository repository) {
        return items -> repository.saveAll(items); // para persistir en lote.
    }
}