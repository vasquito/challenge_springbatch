package com.accenture.challenge.app.scheduler;

import com.accenture.challenge.utils.exceptions.BatchException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SalesJobScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job salesImportJob;

    @Value("${batch.sales.source.folder}")
    private String sourceFolder;

    @Scheduled(fixedDelayString = "${batch.sales.fixed-delay-job}")
    public void runJobIfFileExists() throws Exception {
        boolean folderSourceExists = new File(sourceFolder).exists();
        if (folderSourceExists) {
            File folder = new File(sourceFolder);
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));

            if (files != null && files.length > 0) {
                JobParameters params = new JobParametersBuilder()
                        .addLong("timestamp", System.currentTimeMillis()) // Important !!!
                        .toJobParameters();

                jobLauncher.run(salesImportJob, params);
            }
        } else {
            throw new BatchException(String.format("Folder Not exists [%s]", sourceFolder));
        }
    }
}