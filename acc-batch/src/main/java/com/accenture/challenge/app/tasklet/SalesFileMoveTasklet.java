package com.accenture.challenge.app.tasklet;

import com.accenture.challenge.utils.helpers.DateHelper;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Component
public class SalesFileMoveTasklet implements Tasklet {

    @Value("${batch.sales.source.folder}")
    private String sourceFolder;

    @Value("${batch.sales.source.file-name}")
    private String sourceFileName;

    @Value("${batch.sales.target.folder}")
    private String targetFolder;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext context) throws Exception {
        String currentDate = DateHelper.formatDateToString(new Date());
        Path source = Paths.get(sourceFolder+"/"+sourceFileName);
        Path target = Paths.get(targetFolder+ "/sales-process-"+currentDate+".csv");
        Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        return RepeatStatus.FINISHED;
    }
}