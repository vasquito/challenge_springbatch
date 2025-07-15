package com.accenture.challenge.sales.service.generator.impl;

import com.accenture.challenge.sales.service.generator.ISalesFileGeneratorService;
import com.accenture.challenge.sales.service.generator.parallel.SalesCsvGeneratorTask;
import com.accenture.challenge.utils.exceptions.BatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service("salesCsvGeneratorService")
public class SalesCsvGeneratorServiceImpl implements ISalesFileGeneratorService {
    private static final String OUTPUT_TMP = "tmp";
    private static final int THREADS = Runtime.getRuntime().availableProcessors();

    @Value("${batch.sales.source.fields}")
    private String sourceFields;

    @Override
    public File generateFile(String fileName, long totalRows) throws
            IOException, InterruptedException, ExecutionException {

        // Clean folders and files
        boolean folderTmpExists = new File(OUTPUT_TMP).exists();
        if (folderTmpExists) {
            new File(OUTPUT_TMP).delete();
        }
        boolean folderTmpCreated = new File(OUTPUT_TMP).mkdirs();
        long itemsXThread = totalRows / THREADS;

        // Multithread
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        List<Future<File>> futures = new ArrayList<>();
        for (int i = 0; i < THREADS; i++) {
            futures.add(executor.submit(new SalesCsvGeneratorTask(i, itemsXThread, OUTPUT_TMP)));
        }
        executor.shutdown();
        //executor.awaitTermination(2, TimeUnit.HOURS);


        File file = new File(OUTPUT_TMP+"/"+fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(sourceFields + "\n");

            for (Future<File> future : futures) {
                File fileResult = future.get();
                try (BufferedReader reader = new BufferedReader(new FileReader(fileResult))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
                fileResult.delete();
            }
        }
        return file;
    }
}