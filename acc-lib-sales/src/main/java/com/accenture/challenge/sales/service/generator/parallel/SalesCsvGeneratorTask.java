package com.accenture.challenge.sales.service.generator.parallel;

import com.accenture.challenge.utils.helpers.RandomHelper;
import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;

import java.io.*;
import java.util.concurrent.Callable;

@AllArgsConstructor
public class SalesCsvGeneratorTask implements Callable<File> {
    private final int partNumber;
    private final long rows;
    private final String outputDir;

    @Override
    public File call() throws Exception {
        File file = new File(outputDir, "part_" + partNumber + ".csv");
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            for (long i = 0; i < rows; i++) {
                String puntoVenta = String.valueOf(RandomHelper.generateLongRandom(1,23));
                String importe = String.valueOf(RandomHelper.generateLongRandom(0,1000000));
                String temperatura = String.valueOf(RandomHelper.generateLongRandom(-15,50));
                String cantidad = String.valueOf(RandomHelper.generateLongRandom(0, 10000));
                writer.writeNext(new String[]{puntoVenta, importe, cantidad, temperatura});
            }
        }
        return file;
    }
}
