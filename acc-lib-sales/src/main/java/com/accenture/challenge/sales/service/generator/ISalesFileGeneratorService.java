package com.accenture.challenge.sales.service.generator;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface ISalesFileGeneratorService {
    File generateFile(String fileName, long totalRows) throws IOException, InterruptedException, ExecutionException;
}
